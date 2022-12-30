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
  FROM postgres:alpine
  COPY docker/create-database /docker-entrypoint-initdb.d/
  ENV POSTGRES_HOST_AUTH_METHOD=trust
  ENV POSTGRES_MULTIPLE_DATABASES=db_contracts,db_customers,db_permissions
  ENV POSTGRES_USER=app_user
  ENV POSTGRES_PASSWORD=password
  EXPOSE 5432
  RUN chmod 777 /docker-entrypoint-initdb.d/

run-db:
  LOCALLY
  WITH DOCKER --load db-base:latest=+db-base \
    RUN docker network create example-network && \
      docker run -d --net=example-network --net-alias=db \
      --name pgdb -p 5432:5432 --rm db-base:latest
  END

# migrate-contracts:
#   FROM mybatis/migrations
#   COPY ./schemas/db/contracts/migration /migration

# migrate-customers:
#   FROM mybatis/migrations
#   COPY ./schemas/db/customers/migration /migration

# migrate-securitytokens:
#   FROM mybatis/migrations
#   COPY ./schemas/db/securitytokens/migration /migration

# migrate-db:
#   LOCALLY
#   WITH DOCKER \
#     --load migrate-contracts:latest=+migrate-contracts \
#     --load migrate-tocustomersol:latest=+migrate-customers \
#     --load migrate-securitytokens:latest=+migrate-securitytokens \
#     RUN docker run --net=example-network --rm migrate-contracts:latest up && \
#       docker run --net=example-network --rm migrate-customers:latest up && \
#       docker run --net=example-network --rm migrate-securitytokens:latest up \
#   END
