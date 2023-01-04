rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "webapps:gateway",
    "webapps:service-contracts",
    "webapps:service-customers",
    "webapps:service-permissions",
    "webapps:shared",
    "webapps:shared-test"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
