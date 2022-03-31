import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
}

val kotlinVersion: String by extra("1.5.31")
val jacksonKotlinVersion: String by extra("2.13.2")

val javaxAnnotationVersion: String by extra("1.3.2")

val grpcVersion: String by extra("1.39.0")
val grpcKotlinVersion: String by extra("1.2.0")
val protobufVersion: String by extra("3.19.1")
val coroutinesVersion: String by extra("1.5.2")

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
