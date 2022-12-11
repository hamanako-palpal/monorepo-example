import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.ben-manes.versions") version "0.42.0"
}

val kotlinVersion: String by extra("1.7.10")
val jacksonKotlinVersion: String by extra("2.13.4")

val javaxAnnotationVersion: String by extra("1.3.2")

val kodeinVersion: String by extra("4.1.0")

val graphqlDgsVersion: String by extra("5.1.0")
val springBootVersion: String by extra("2.6.10")
val grpcSpringBootVersion: String by extra("4.9.0")

val mybatisVersion: String by extra("3.5.7")
val mybatisDynamicVersion: String by extra("1.3.0")
val mybatisGeneratorVersion: String by extra("1.4.0")
val postgresClientVersion: String by extra("42.4.1")

val grpcVersion: String by extra("1.48.1")
val grpcKotlinVersion: String by extra("1.2.0")
val protobufVersion: String by extra("3.21.5")
val coroutinesVersion: String by extra("1.6.4")

val exposedVersion: String by extra("0.39.2")

val argon2Version: String by extra("2.11")
val jwtVersion: String by extra("4.1.0")

val testcontainersVersion: String by extra("1.17.3")
val junitJupiterVersion: String by extra("5.5.2")
val kotestRunnerJunit5Version: String by extra("5.4.2")
val grpcTestingVersion: String by extra("1.48.1")

allprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    group = "com.pal2hmnk.example"
    version = "0.0.1"
    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
            jvmTarget = "11"
        }
    }
}
tasks.register("copyProto", Copy::class) {
    from("../../schemas/proto")
    into("libs/generated/proto/src/main/proto/services")
    mustRunAfter("cleanDirs")
}
tasks.register("copyGraphql", Copy::class) {
    from("../../schemas/graphql")
    into("webapps/gateway/applications/src/main/resources/schema")
    mustRunAfter("cleanDirs")
}
tasks.register("cleanDirs", Delete::class) {
    delete("libs/generated/proto/src/main/proto/services")
    delete("webapps/gateway/applications/src/main/resources/schema")
}
tasks.register("withSchemaChange") {
    dependsOn(tasks.named("copyProto"))
    dependsOn(tasks.named("copyGraphql"))
    dependsOn(tasks.named("cleanDirs"))
}
