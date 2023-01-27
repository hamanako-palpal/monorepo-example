dependencies {
    val exposedVersion: String by project
    val testcontainersVersion: String by project
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    implementation("org.testcontainers:postgresql:$testcontainersVersion")
}
