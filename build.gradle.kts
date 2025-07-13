plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

// note: create custom gradle command to clean the build dirs
tasks.register("cleanCustomBuildDirs") {
    doFirst {
        val dirs = listOf(
            File("build"),
            File("composeApp/build")
        )
        dirs.forEach { dir ->
            if (dir.exists()) {
                println("Deleting ${dir.path}")
                dir.deleteRecursively()
            } else {
                println("${dir.path} not found, skipping.")
            }
        }
    }
}