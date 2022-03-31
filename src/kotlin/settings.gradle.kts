rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
