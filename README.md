# monorepo-example
#### やること
- トークン周りのrpc整理
- 特にgraphqlでvalueクラスしても徳がないのでやめよう
- rpcClientの実装をレシーバで受け取ると楽できそう
- asGRpc推進しよう
- リソースをもう少し整備しないとね
  - 一旦参照系を整えよう
    - 管理者がスタッフ一覧を表示
    - オーダー一覧とか
  - 更新系少しだけ、、、
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