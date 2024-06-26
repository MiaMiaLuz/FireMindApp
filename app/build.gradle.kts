plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("com.google.gms.google-services")
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-firestore:25.0.0")
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Jetpack compose
    implementation ("androidx.compose.ui:ui:1.6.6")
    implementation ("androidx.compose.material:material:1.6.6")
    implementation ("androidx.compose.ui:ui-tooling:1.6.6")
    implementation ("androidx.compose.foundation:foundation:1.6.6")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    //Firebase
    implementation ("com.google.gms:google-services:4.4.1")
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-database")
    implementation ("com.google.firebase:firebase-analytics-ktx:21.6.2")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.firebase:firebase-auth:21.0.0")
    //Biometric
    implementation("androidx.biometric:biometric:1.0.1")
    //QR
    implementation("com.google.zxing:core:3.4.1")
    implementation("com.journeyapps:zxing-android-embedded:4.2.0")
    //Calendar
    implementation("com.prolifiteractive:material-calendarview:2.0.0")

}