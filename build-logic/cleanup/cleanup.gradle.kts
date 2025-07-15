//// note: create custom gradle command to clean build dirs
//tasks.register("cleanCustomBuildDirs") {
//    doFirst {
//        val dirs = listOf(
//            File("build"),
//            File("composeApp/build")
//        )
//        dirs.forEach { dir ->
//            if (dir.exists()) {
//                println("Deleting ${dir.path}")
//                dir.deleteRecursively()
//            } else {
//                println("${dir.path} not found, skipping.")
//            }
//        }
//    }
//}