import ProjectLib.common
import ProjectLib.core
import ProjectLib.home
import ProjectLib.remote

plugins {
    androidApplication
    kotlinAndroid
    daggerHilt
    kotlin(kotlinKapt)
}

android {
    compileSdk = Config.Version.compileSdkVersion

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        named(BuildType.RELEASE) {
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
    buildFeatures{
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}
hilt {
    enableAggregatingTask = true
}

dependencies {
    //project libs
    implementation(project(remote))
    implementation(project(common))
    implementation(project(core))
    implementation(project(home))


    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.View.material)
    implementation(Dependencies.View.constraintLayout)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.junitExt)
    androidTestImplementation(Dependencies.Test.espresso)

    //navigation
    implementation(Dependencies.Navigation.navigationUiKtx)
    implementation(Dependencies.Navigation.navigationFragmentKtx)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)
}