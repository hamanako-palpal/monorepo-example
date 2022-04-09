plugins {
    id("application")
}
application {
    mainClass.set("com.pal2hmnk.example.user.RootApplicationKt")
}
val jar by tasks.getting(Jar::class) {
    archiveFileName.set("app.jar")
    manifest {
        attributes["Main-Class"] = "com.pal2hmnk.example.user.RootApplicationKt"
    }
    from(
        configurations.compile.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
}
