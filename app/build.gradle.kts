plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //imported
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.together"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.together"
        minSdk = 28
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack Compose navigation
    implementation(libs.androidx.navigation.compose)

    //Material icons
    implementation(libs.androidx.material.icons.extended)

    //coil image loading compose
    implementation(libs.coil.compose)

    //data store
    implementation(libs.androidx.datastore.preferences)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //lottie animations
    implementation("com.airbnb.android:lottie-compose:6.7.1")

    //status bar
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

    //Retrofit for apis
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.15.0")

    //GSON converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    //hilt navigation
    implementation("androidx.hilt:hilt-navigation:1.1.0-alpha02")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

}