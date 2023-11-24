plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.katoklizm.myprojectsearchmoviecleanarchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.katoklizm.myprojectsearchmoviecleanarchitecture"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    val moxyVersion = ("2.2.2")
    implementation ("io.insert-koin:koin-android:3.3.0")

    implementation ("com.github.moxy-community:moxy:$moxyVersion")
    implementation ("com.github.moxy-community:moxy-android:$moxyVersion")
    implementation ("com.github.moxy-community:moxy-compiler:$moxyVersion")

    implementation("androidx.core:core-ktx:1.9.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("com.google.code.gson:gson:2.10")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Подключаем основную библиотеку
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.2")
    // Подключаем модуль для работы с ViewBinding
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2")

}