import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.dreamsoftware.fudge"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = false
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.dreamsoftware.libraries"
                artifactId = "fudge-tv-compose"
                version = "0.0.42"
                artifact("$buildDir/outputs/aar/app-release.aar")
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sergio11/fudge_tv_compose_library")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
            }
        }
    }
}

dependencies {
    // Core Android libraries
    implementation(libs.appcompat) // Provides backward-compatible versions of Android framework APIs
    implementation(libs.activity.compose) // Support for integrating Jetpack Compose with Activity

    // Jetpack Compose libraries
    implementation(libs.compose.runtime) // Core runtime for Jetpack Compose
    implementation(libs.compose.foundation) // Foundational components for Jetpack Compose
    implementation(libs.androidx.navigation.compose)

    // TV-specific Material Design components
    implementation(libs.tv.material) // Material Design components tailored for TV interfaces
    implementation(libs.androidx.material3.android) // Latest Material 3 components for Android
    implementation(libs.androidx.material.icons.extended) // Extended Material icons for additional icon options

    // Image loading and media playback
    implementation(libs.coil.compose) // Image loading library for Jetpack Compose
    implementation(libs.androidx.media3.exoplayer) // Media playback library
    implementation(libs.androidx.media3.ui) // UI components for Media3

    // Debugging and preview tools
    implementation(libs.ui.tooling.preview) // Tools for previewing Compose UI elements
    implementation(libs.ui.tooling) // Debugging tools for Jetpack Compose UI
}