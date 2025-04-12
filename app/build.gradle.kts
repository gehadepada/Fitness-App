plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.foodcalories"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodcalories"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX and Kotlin Dependencies
    implementation(libs.androidx.core.ktx.v1160)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Jetpack Compose Dependencies
    implementation(libs.ui)  // Replace with the actual Compose version
    implementation(libs.androidx.material)  // Replace with the actual Compose version
    implementation(libs.ui.tooling.preview)  // Replace with the actual Compose version
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose.v182)
    implementation(libs.material3)
    // Testing Dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.ui.test.junit4)  // Replace with the actual Compose version
    debugImplementation(libs.ui.tooling)  // Replace with the actual Compose version
}
