// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // Use the JAVA version of the plugin
    id("androidx.navigation.safeargs") version "2.7.7" apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}