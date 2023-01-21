plugins {
    kotlin("plugin.spring") version "1.7.21"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.springframework.boot") version "2.7.6"
}
dependencies {
    val springBootVersion: String by project
    val grpcSpringBootVersion: String by project
    val grpcKotlinVersion: String by project
    val grpcVersion: String by project
    val postgresClientVersion: String by project
    val exposedVersion: String by project
    val junitJupiterVersion: String by project
    val jwtVersion: String by project
    val kotestRunnerJunit5Version: String by project
    val snakeYamlVersion: String by project

    implementation(project(":libs:generated:proto"))
    implementation(project(":webapps:shared"))
    testImplementation(project(":webapps:shared-test"))

    implementation("com.auth0:java-jwt:$jwtVersion")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcSpringBootVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("org.postgresql:postgresql:$postgresClientVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.jetbrains.exposed:spring-transaction:$exposedVersion")
    implementation("org.yaml:snakeyaml:$snakeYamlVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestRunnerJunit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
