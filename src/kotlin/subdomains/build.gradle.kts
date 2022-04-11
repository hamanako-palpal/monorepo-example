configure(subprojects) {

}
project(":subdomains:gateway") {
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
}
project(":subdomains:service-user") {
    dependencies {
        val grpcKotlinVersion: String by project
        val grpcVersion: String by project
        val kodeinVersion: String by project
        val mybatisDynamicVersion: String by project

        implementation(project(":libs:generated:proto"))
        implementation(project(":libs:generated:orm"))
        implementation(project(":subdomains:shared"))

        implementation("com.github.salomonbrys.kodein:kodein:$kodeinVersion")
        implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
        implementation("io.grpc:grpc-protobuf:$grpcVersion")
        implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:$mybatisDynamicVersion")
        runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    }
}
project(":subdomains:service-shop") {
    dependencies {
        val grpcKotlinVersion: String by project
        val grpcVersion: String by project
        val kodeinVersion: String by project
        val mybatisDynamicVersion: String by project

        implementation(project(":libs:generated:proto"))
        implementation(project(":libs:generated:orm"))
        implementation(project(":subdomains:shared"))

        implementation("com.github.salomonbrys.kodein:kodein:$kodeinVersion")
        implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
        implementation("io.grpc:grpc-protobuf:$grpcVersion")
        implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:$mybatisDynamicVersion")
        runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    }
}
