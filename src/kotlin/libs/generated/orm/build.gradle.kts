plugins {
    id("com.arenagod.gradle.MybatisGenerator") version "1.4"
}

val mybatisVersion: String by project
val mybatisDynamicVersion: String by project
val mybatisGeneratorVersion: String by project
val postgresClientVersion: String by project

dependencies {
    implementation(project(":libs:generated:orm-plugin"))
    implementation("org.mybatis:mybatis:$mybatisVersion")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:$mybatisDynamicVersion")
    implementation("org.mybatis.generator:mybatis-generator-core:$mybatisGeneratorVersion")
}

configurations {
    mybatisGenerator
}

mybatisGenerator {
    verbose = true
    configFile = "src/main/resources/generatorConfig.xml"
    dependencies {
        mybatisGenerator(project(":libs:generated:orm-plugin"))
        mybatisGenerator("org.mybatis.generator:mybatis-generator-core:$mybatisGeneratorVersion")
        mybatisGenerator("org.postgresql:postgresql:$postgresClientVersion")
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main/kotlin")
        }
    }
}
