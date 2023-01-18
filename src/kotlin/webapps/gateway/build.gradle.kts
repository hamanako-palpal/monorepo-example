plugins {
    kotlin("plugin.spring") version "1.7.21"
    id("org.springframework.boot") version "2.7.6"
}
dependencies {
    val coroutinesVersion: String by project
    val graphqlDgsVersion = "5.5.0"
    val jacksonKotlinVersion: String by project
    val springBootVersion: String by project

    val protobufVersion: String by project
    val grpcVersion: String by project
    val grpcKotlinVersion: String by project

    val snakeYamlVersion: String by project

    implementation(project(":libs:generated:proto"))
    implementation(project(":libs:generated:graphql"))
    implementation(project(":webapps:shared"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")

    implementation("com.google.protobuf:protobuf-java:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-java-util:${protobufVersion}")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-netty-shaded:${grpcVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("io.netty:netty-codec:4.1.86.Final")

    implementation("com.github.javafaker:javafaker:1.+") {
        exclude(module = "org.yaml")
    }
    implementation("org.yaml:snakeyaml:$snakeYamlVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")
    implementation("com.netflix.graphql.dgs:graphql-dgs-webflux-starter:$graphqlDgsVersion")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars:$graphqlDgsVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}
