import BuildType.Companion.DEBUG
import BuildType.Companion.RELEASE
import ProjectLib.common
import ProjectLib.core

plugins {
    androidLibrary
    kotlinAndroid
    kotlin(kotlinKapt)
    daggerHilt
    safeArgs
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //project lib
    implementation(project(core))
    implementation(project(common))

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.View.material)
    implementation(Dependencies.View.constraintLayout)
    androidTestImplementation(Dependencies.Test.junitExt)
    androidTestImplementation(Dependencies.Test.espresso)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)

    //lifecycle
    implementation(Dependencies.AndroidX.liveDataKtx)

    //view
    implementation(Dependencies.View.recyclerView)
    implementation(Dependencies.View.swipeRefresh)

    //glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.AnnotationProcessor.glideAnnotation)

    //navigation
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation(Dependencies.Navigation.navigationUiKtx)
    implementation(Dependencies.Navigation.navigationFragmentKtx)


    /* test */
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.archCore)

}