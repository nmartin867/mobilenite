import java.io.FileInputStream
import java.util.Properties

plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
}

val appProps = Properties()
appProps.load(
    FileInputStream(
        rootProject.file("apikey.properties")
    )
)

android {
    namespace = "com.nmartindev.fortnite"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nmartindev.fortnite"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", appProps.getProperty("apikey"))
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(platform(libs.squareup.retrofit2.bom))
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.moshi.converter)
    implementation(platform(libs.squareup.okhttp3.bom))
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logginginterceptor)
    implementation(libs.squareup.moshi)
    //ksp(libs.squareup.moshi.kotlin.codegen)
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.android.compiler)
    testImplementation(libs.squareup.okhttp3.mockwebserver)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}
