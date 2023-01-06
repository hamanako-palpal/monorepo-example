VERSION 0.6
FROM gradle:6.9-jdk11

assemble:
  BUILD +middlewares-run
  BUILD +db-migrate
  BUILD +customers-run
  BUILD +contracts-run
  BUILD +permissions-run

build-kotlin-example-base:
  RUN mkdir webapps
  COPY src/kotlin/build.gradle.kts src/kotlin/settings.gradle.kts .
  COPY --dir src/kotlin/libs .
  COPY --dir src/kotlin/webapps/shared webapps
  COPY schemas/proto libs/generated/proto/src/main/proto/services
  COPY schemas/graphql libs/generated/graphql/src/main/resources/schema
  RUN gradle clean libs:generated:proto:generateProto libs:generated:graphql:generateJava
  SAVE IMAGE --cache-hint

customers:
  FROM +build-kotlin-example-base
  COPY --dir src/kotlin/webapps/service-customers webapps
  EXPOSE 50051
  ENTRYPOINT ["gradle", "webapps:service-customers:bootRun"]

contracts:
  FROM +build-kotlin-example-base
  COPY --dir src/kotlin/webapps/service-contracts webapps
  EXPOSE 50052
  ENTRYPOINT ["gradle", "webapps:service-contracts:bootRun"]

permissions:
  FROM +build-kotlin-example-base
  COPY --dir src/kotlin/webapps/service-permissions webapps
  EXPOSE 50053
  ENTRYPOINT ["gradle", "webapps:service-permissions:bootRun"]

customers-run:
  LOCALLY
  WITH DOCKER \
    --load customers:latest=+customers
    RUN docker run --net=example-network --net-alias=app \
      -p 50051:50051 \
      --env SERVER_PORT=50051 \
      --env SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/db_customers \
      --rm customers:latest
  END

contracts-run:
  LOCALLY
  WITH DOCKER \
    --load contracts:latest=+contracts
    RUN docker run --net=example-network --net-alias=app \
      -p 50052:50052 \
      --env SERVER_PORT=50052 \
      --env SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/db_contracts \
      --rm contracts:latest
  END

permissions-run:
  LOCALLY
  WITH DOCKER \
    --load permissions:latest=+permissions
    RUN docker run --net=example-network --net-alias=app \
      -p 50053:50053 \
      --env SERVER_PORT=50053 \
      --env SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/db_permissions \
      --rm permissions:latest
  END

test-customers:
  COPY . .
  RUN cp -R schemas/db/customers/ddl/ddl.sql src/kotlin/webapps/service-customers/src/test/resources/ddl.sql
  WORKDIR src/kotlin
  RUN gradle clean withSchemaChange libs:generated:proto:generateProto
  WITH DOCKER
    RUN gradle webapps:service-customers:test --stacktrace
  END

db-base:
  FROM postgres:12.13-alpine
  COPY docker/create-database /docker-entrypoint-initdb.d/
  ENV POSTGRES_HOST_AUTH_METHOD=trust
  ENV POSTGRES_MULTIPLE_DATABASES=db_contracts,db_customers,db_permissions
  ENV POSTGRES_USER=app_user
  ENV POSTGRES_PASSWORD=password
  EXPOSE 5432
  RUN chmod 777 /docker-entrypoint-initdb.d/

redis-base:
  FROM redis:6.2
  EXPOSE 6379

middlewares-run:
  LOCALLY
  WITH DOCKER \
      --load db-base:latest=+db-base \
      --load redis-base:latest=+redis-base \
      --load migrate-customers:latest=+migrate-customers \
      --load migrate-contracts:latest=+migrate-contracts \
      --load migrate-permissions:latest=+migrate-permissions
    RUN docker network inspect example-network >/dev/null 2>&1 || \
      docker network create example-network && \
      docker volume create example-db && \
      docker run -d --net=example-network --net-alias=db \
        --name example-postgres -p 5432:5432 \
        -v example-db:/var/lib/postgresql/data \
        --rm db-base:latest && \
      docker run -d --net=example-network --net-alias=redis \
        --name example-redis -p 6379:6379 --rm redis-base:latest \
      docker run --net=example-network --rm migrate-customers:latest up && \
      docker run --net=example-network --rm migrate-contracts:latest up && \
      docker run --net=example-network --rm migrate-permissions:latest up
  END

migrate-base:
  FROM mybatis/migrations
  COPY --dir schemas/db/customers/migration/drivers /migration

migrate-customers:
  FROM +migrate-base
  COPY --dir schemas/db/customers/migration/environments /migration
  COPY --dir schemas/db/customers/migration/scripts /migration

migrate-contracts:
  FROM +migrate-base
  COPY --dir schemas/db/contracts/migration/environments /migration
  COPY --dir schemas/db/contracts/migration/scripts /migration

migrate-permissions:
  FROM +migrate-base
  COPY --dir schemas/db/permissions/migration/environments /migration
  COPY --dir schemas/db/permissions/migration/scripts /migration

assemble-db-migrate:
  LOCALLY
  WITH DOCKER \
    --load migrate-customers:latest=+migrate-customers \
    --load migrate-contracts:latest=+migrate-contracts \
    --load migrate-permissions:latest=+migrate-permissions
    RUN docker run --net=example-network --rm migrate-customers:latest up && \
      docker run --net=example-network --rm migrate-contracts:latest up && \
      docker run --net=example-network --rm migrate-permissions:latest up
  END
