rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:shared",
    "webapps:gateway",
    "webapps:service-contracts",
    "webapps:service-customers",
    "webapps:service-permissions",
    "webapps:shared-test"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
