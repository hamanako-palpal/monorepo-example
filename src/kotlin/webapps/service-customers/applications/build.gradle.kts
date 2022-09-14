plugins {
    id("application")
    id("com.google.cloud.tools.jib") version "3.2.1"
}
dependencies {
    val grpcKotlinVersion: String by project
    val grpcVersion: String by project
    val kodeinVersion: String by project
    val mybatisDynamicVersion: String by project
    val mybatisVersion: String by project
    val postgresClientVersion: String by project
    val exposedVersion: String by project
    val junitJupiterVersion: String by project
    val kotestRunnerJunit5Version: String by project

    implementation(project(":libs:generated:proto"))
    implementation(project(":libs:generated:orm"))
    implementation(project(":webapps:service-customers:domains"))
    implementation(project(":webapps:shared"))
    testImplementation(project(":webapps:shared-test"))

    implementation("com.github.salomonbrys.kodein:kodein:$kodeinVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:$postgresClientVersion")
    implementation("org.mybatis:mybatis:$mybatisVersion")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:$mybatisDynamicVersion")
    runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestRunnerJunit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}
val mainClassNm = "com.pal2hmnk.example.customers.RootApplicationKt"
application {
    mainClass.set(mainClassNm)
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
jib {
    to {
        image = "pal2hmnk/service-customers"
    }
    container {
        mainClass = mainClassNm
    }
}
