plugins {
    kotlin("plugin.spring") version "1.7.10"
    id("com.google.cloud.tools.jib") version "3.2.1"
    id("org.springframework.boot") version "2.6.10"
}
dependencies {
    val springBootVersion: String by project
    val grpcSpringBootVersion: String by project
    val grpcKotlinVersion: String by project
    val grpcVersion: String by project
    val postgresClientVersion: String by project
    val exposedVersion: String by project
    val junitJupiterVersion: String by project
    val kotestRunnerJunit5Version: String by project

    implementation(project(":libs:util"))
    implementation(project(":libs:generated:proto"))
    implementation(project(":webapps:service-contracts:domains"))
    implementation(project(":webapps:shared"))
    testImplementation(project(":webapps:shared-test"))

    implementation("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcSpringBootVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("org.postgresql:postgresql:$postgresClientVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.jetbrains.exposed:spring-transaction:$exposedVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestRunnerJunit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}
val mainClassNm = "com.pal2hmnk.example.contracts.RootApplicationKt"
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
jib {
    to {
        image = "pal2hmnk/service-contracts"
    }
    container {
        mainClass = mainClassNm
    }
}
