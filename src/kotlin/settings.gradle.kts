rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:shared",
    "libs:shared-test",
    "webapps:gateway",
    "webapps:service-contracts",
    "webapps:service-customers",
    "webapps:service-permissions"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
