rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:shared",
    "libs:shared-test",
    "services:gateway",
    "services:contracts",
    "services:customers",
    "services:permissions"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
