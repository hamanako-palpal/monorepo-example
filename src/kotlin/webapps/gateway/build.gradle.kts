plugins {
    kotlin("plugin.spring") version "1.5.31"
    id("org.springframework.boot") version "2.4.11"
    id("com.google.cloud.tools.jib") version "3.2.1"
}
dependencies {
    val coroutinesVersion: String by project
    val graphqlDgsVersion: String by project
    val jacksonKotlinVersion: String by project
    val springBootVersion: String by project

    val protobufVersion: String by project
    val grpcVersion: String by project
    val grpcKotlinVersion: String by project

    implementation(project(":libs:util"))
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

    implementation("com.github.javafaker:javafaker:1.+")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:$graphqlDgsVersion")
    implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars:$graphqlDgsVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}
jib {
    to {
        image = "pal2hmnk/gateway"
    }
    container {
        ports = listOf("8080")
        mainClass = "com.pal2hmnk.example.gateway.RootApplicationKt"
    }
}
