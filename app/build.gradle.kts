plugins {
    alias(libs.plugins.android.application)
    // This project uses Kotlin build scripts and likely has Kotlin dependencies/code.
    // This plugin is necessary.
    id("org.jetbrains.kotlin.android")
    // We need the JAVA version of Safe Args because TourFragment.java is a Java file.
    id("androidx.navigation.safeargs")
}


android {
    namespace = "com.example.stitouringapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.stitouringapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        // Restoring to a valid and modern Java version.
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.jsibbold:zoomage:1.3.1")
}
