# monorepo-example
#### やること
- sts改名、権限サービス的なもの
- stsのredis周り実装たりてない
  - earthlyにredis周り追加
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