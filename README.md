# monorepo-example
#### やること
- stsのredis周り実装たりてない
  - earthlyにredis周り追加
  - accessToken
    - 認証に関する情報
    - Redisに永続化
    - クライアントまで渡す
  - IdToken
    - ユーザ情報に関する情報
    - ユーザID、各店舗の権限について
    - ごちごちのJWT
    - permissionsにaccesstoken or RefreshTokenを渡すと受け取れる、Idトークンがないとリソースで弾かれる
  - RefreshToken
    - アクセストークンが期限切れた時に使う
      - DBに永続化
      - token, user_id, expired, 30日
      - クライアントまで渡す
  - beareにトークン埋め込んでリクエスト投げるといい感じに返ってくる
    - 期限切れかどうかはフロントで判断していただいて、ダメならリフレッシュしてもらう
  - ユースケース
    - トークンがないので認証エンドポイントにアクセス
    - 認証が成功したらトークン生成リクエストを投げる(customers -> permissions)
    - リクエストを受けつけたら各トークンを発行する
      - AccessToken
        - 各要素
          - jwtId
          - ユーザID
          - 期限30分
          - 発生時刻
          - client_id(現時点では固定)
        - 永続化の方法
          - 検証はシークレットを使って、リソースで確認してもらうのでしなくて良い
          - と思ったけど、こいつを使ってIdTokenを返したいので、jwtIdとユーザIDの検証だけしとくか
          - Redisにしまっておく
            - key: jwtKey: string
            - value: userId: int
          - IDトークンは余計な通信をなくすためキャッシュしておく
            - key userId: int
            - val: IdToken: Token
      - IdToken
        - 各要素
          - ユーザID
          - 発生時刻
          - ショップの権限など
        - 取得の方法
          - permissionsサービスに問い合わせる
          - アクセスキーで取得、できたらRSに問い合わせる
      - RefreshToken
        - 各要素
          - ユーザID
          - jwtId
          - client_id
        - テーブルに保存
          - 検証が確認できたら、またjwtIdを発行して生成する
          - IDトークンはキャッシュしたものをそのまま使う
    - 発行したAccessTokenとRefreshTokenをcustomersに返す
    - リソースが欲しそうなリクエストはaccessTokenの検証から行う

- レポジトリ構成の革命
  - applicationsとdomains統合
  - webapps廃止、libs廃止
- 認証→ConnectionId生成→リソースへアクセスを通す
- リソースをもう少し整備しないとね
  - 一旦参照系を整えよう
    - 特定のユーザの注文履歴を照会する
    - 管理者がスタッフ一覧を表示
  - 更新系少しだけ、、、
- アクセストークンとIDトークンに分けてみたい
- DBのマイグレーションをEarthfileに移行する
- データベース生成をEarthlyに移行
- 気が向いたらdocker-compose廃止
- protoファイル必要な分だけ生成するようにコピー制御
- SvelteKitいったろ

### using libs
- DGS Framework
- Spring Boot
- grpc-kotlin
- Exposed
- Kotest
- dbSetUp

### init
#### prepare db driver
at first, download db driver.

#### prepare Mybatis Generator Plugins
create fat jar.

### migrate db
run images.
> docker compose -f docker-compose.yaml -f docker-migrate.yaml up -d pgdb migrate-customers migrate-contracts

### code generate 
> cd src/kotlin  
./gradlew withSchemaChange  
./gradlew :libs:graphql:generateJava  
./gradlew :libs:orm:mbGenerator  
./gradlew :libs:proto:generateProto

### build image
> ./gradlew :subdomains:gateway:jibDockerBuild  
./gradlew :subdomains:service-user:jibDockerBuild  
./gradlew :subdomains:service-shop:jibDockerBuild

### run applications
> cd ../../  
docker compose up -d