import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(compose.materialIconsExtended)
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta03")
            // DataStore library
            implementation("androidx.datastore:datastore:1.1.7")
            // The Preferences DataStore library
            implementation("androidx.datastore:datastore-preferences:1.1.7")
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.compose.desktop)
            implementation("androidx.collection:collection:1.4.0") // 👈 ADD THIS
//            implementation("androidx.navigation:navigation-compose:2.9.1")// 👈 ADD THIS
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta03")
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.aim.nova.hub.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.aim.nova.hub"
            packageVersion = "1.0.0"

            windows {
                shortcut = true
                menu = true
                iconFile.set(project.file("src/desktopMain/resources/icon.ico"))
                menuGroup = "Nova Tools"
                upgradeUuid = "b9d6ee7a-3322-4ab3-a543-f66d330defd2" // generate new UUID
            }

            macOS {
                iconFile.set(project.file("src/desktopMain/resources/icon.icns"))
            }

            linux {
                iconFile.set(project.file("src/desktopMain/resources/icon.png"))
            }
        }
    }
}
//compose.resources {
//    publicResClass = false
//    packageOfResClass = "com.example.nova.hub.resources"
//    generateResClass = auto
//}