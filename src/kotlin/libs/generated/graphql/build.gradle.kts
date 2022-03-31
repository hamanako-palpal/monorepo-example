import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
    id("com.netflix.dgs.codegen") version "5.1.2"
}

val javaxAnnotationVersion: String by project

dependencies {
    implementation("javax.annotation:javax.annotation-api:$javaxAnnotationVersion")
}

tasks.withType<GenerateJavaTask> {
    schemaPaths = mutableListOf("../../../../../schemas/graphql")
    generateClient = false
    packageName = "com.pal2hmnk.example.generated.graphql"
}
