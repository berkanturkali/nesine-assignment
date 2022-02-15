import BuildType.Companion.DEBUG
import BuildType.Companion.RELEASE

plugins {
    androidLibrary
    kotlinAndroid
    kotlin(kotlinKapt)
    daggerHilt
}

android {
    compileSdk = Config.Version.compileSdkVersion

    defaultConfig {
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named(DEBUG) {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        named(RELEASE) {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.View.material)
    implementation(Dependencies.View.constraintLayout)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.junitExt)
    androidTestImplementation(Dependencies.Test.espresso)

    //network
    implementation(Dependencies.Network.retrofit)


    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)

    implementation(Dependencies.Coroutines.androidCoroutines)

    //test
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Test.mockWebServer)
}