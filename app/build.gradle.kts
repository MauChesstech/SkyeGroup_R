plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)  // Añadiendo Hilt Inyecciones de dependencias
    kotlin("kapt")  // Añade esta línea
}

android {
    namespace = "com.chesstech.skyegroup"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.chesstech.skyegroup"
        minSdk = 23
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
    // Habilitando ViewBinding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Activity
    implementation (libs.androidx.activity.ktx)
    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    // Corrutinas
    implementation (libs.kotlinx.coroutines.android)
    // Fragment
    implementation (libs.androidx.fragment.ktx)
    // Dagger Hilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)  // Para procesamiento de anotaciones
    kapt(libs.hilt.compiler)

}