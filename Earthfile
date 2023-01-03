VERSION 0.6
FROM gradle:6.9-jdk11

test-customers:
  COPY . .
# VOLUME /var/run/docker.sock:/var/run/docker.sock
  RUN cp -R schemas/db/customers/ddl/ddl.sql src/kotlin/webapps/service-customers/applications/src/test/resources/ddl.sql
  WORKDIR src/kotlin
  RUN gradle clean withSchemaChange libs:generated:proto:generateProto
  WITH DOCKER
    RUN gradle webapps:service-customers:applications:test --stacktrace
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

redis:
  FROM redis:6.2
  EXPOSE 6379

middlewares-run:
  LOCALLY
  WITH DOCKER \
      --load db-base:latest=+db-base \
      --load redis:latest=+redis
    RUN docker network create example-network && \
      docker run -d --net=example-network --net-alias=db \
        --name example-postgres -p 5432:5432 --rm db-base:latest && \
      docker run -d --net=example-network --net-alias=redis \
        --name example-redis -p 6379:6379 --rm redis:latest
  END

# migrate-contracts:
#   FROM mybatis/migrations
#   COPY ./schemas/db/contracts/migration /migration

# migrate-customers:
#   FROM mybatis/migrations
#   COPY ./schemas/db/customers/migration /migration

migrate-permissions:
  FROM mybatis/migrations
  COPY ./schemas/db/permissions/migration /migration

db-migrate:
  LOCALLY
  WITH DOCKER \
    # --load migrate-contracts:latest=+migrate-contracts \
    # --load migrate-tocustomersol:latest=+migrate-customers \
    --load migrate-permissions:latest=+migrate-permissions
    RUN \
      # docker run --net=example-network --rm migrate-contracts:latest up && \
      # docker run --net=example-network --rm migrate-customers:latest up && \
      docker run --net=example-network --rm migrate-permissions:latest up
  END
