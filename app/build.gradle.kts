plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.relay")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.bonnjalal.wikiindaba"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bonnjalal.wikiindaba"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//kotlin {
//    sourceSets.main {
//        kotlin.srcDir("build/generated/ksp/main/kotlin")
//    }
//    sourceSets.test {
//        kotlin.srcDir("build/generated/ksp/test/kotlin")
//    }
//}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Room database implementation

    val roomVersion = "2.5.2"

    implementation("androidx.room:room-runtime:$roomVersion")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    // hilt DI
    implementation("com.google.dagger:hilt-android:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    val nav_version = "2.7.4"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // percentage instead of db
    implementation ("com.github.slaviboy:JetpackComposePercentageUnits:1.0.0")

    // tracing
    implementation ("androidx.tracing:tracing-ktx:1.1.0")


//    implementation ("com.google.api-client:google-api-client:2.0.0")
//    implementation ("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
//    implementation ("com.google.apis:google-api-services-sheets:v4-rev20220927-2.0.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
//    implementation("androidx.compose.runtime:runtime-livedata:1.5.3")

    implementation ("com.google.code.gson:gson:2.10.1")

    // quickie scan qr code
    implementation("io.github.g00fy2.quickie:quickie-bundled:1.8.0")
//    implementation ("com.google.android.gms:play-services-base:18.2.0")
//    implementation ("com.google.android.gms:play-services-tflite-java:16.1.0")

//    implementation ("com.google.android.gms:play-services-code-scanner:16.1.0")

    implementation("androidx.compose.material:material-icons-extended-android:1.5.4")

    // Image loader for compose
    implementation("io.coil-kt:coil-compose:2.4.0")
//    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
