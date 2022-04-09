configure(subprojects) {

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
