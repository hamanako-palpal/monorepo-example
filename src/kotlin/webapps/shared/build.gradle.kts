dependencies {
    val grpcVersion: String by project
    val postgresClientVersion: String by project

    implementation(project(":libs:util"))

    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("org.postgresql:postgresql:$postgresClientVersion")
}
