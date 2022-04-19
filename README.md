# monorepo-example

### useing library
- DGS Framework
- Kodein
- grpc-kotlin
- Mybatis Dynamic SQL
- Mybatis Migrate
- Jib

### init
#### prepare db driver
at first, download db driver.

#### prepare Mybatis Generator Plugins
create fat jar.

### migrate db
run images.
> docker compose -f docker-compose.yaml -f migrate.yaml up -d pgdb migrate

### code generate 
> cd src/kotlin

> ./gradlew withSchemaChange

> ./gradlew :libs:graphql:generateJava

> ./gradlew :libs:orm:mbGenerator

> ./gradlew :libs:proto:generateProto

### build image
> ./gradlew :subdomains:gateway:jibDockerBuild

> ./gradlew :subdomains:service-user:jibDockerBuild

> ./gradlew :subdomains:service-shop:jibDockerBuild

### run applications
> cd ../../

> docker compose up -d