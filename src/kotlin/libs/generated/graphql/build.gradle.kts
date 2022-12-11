import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
    id("com.netflix.dgs.codegen") version "5.6.3"
}

val javaxAnnotationVersion: String by project

dependencies {
    implementation("javax.annotation:javax.annotation-api:$javaxAnnotationVersion")
}

tasks.withType<GenerateJavaTask> {
    schemaPaths = mutableListOf("libs/generated/graphql/src/main/resources/schema")
    generateClient = false
    packageName = "com.pal2hmnk.example.generated.graphql"
}
