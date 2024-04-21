plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.firemind"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firemind"
        minSdk = 27
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Jetpack compose
    implementation ("androidx.compose.ui:ui:1.6.5")
    implementation ("androidx.compose.material:material:1.6.5")
    implementation ("androidx.compose.ui:ui-tooling:1.6.5")
    implementation ("androidx.compose.foundation:foundation:1.6.5")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-database")
    implementation ("com.google.firebase:firebase-analytics-ktx:20.0.0")
    implementation("com.google.firebase:firebase-auth")

    //implementation ("com.google.firebase:firebase-auth-ktx:21.0.0")
    //implementation ("com.google.firebase:firebase-firestore-ktx:23.0.0")
    //implementation ("com.google.firebase:firebase-storage-ktx:20.0.0")



}