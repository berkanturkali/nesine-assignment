import BuildType.Companion.DEBUG
import BuildType.Companion.RELEASE
import ProjectLib.remote

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
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += Pair("room.incremental", "true")
            }
        }
        buildConfigField("int", "databaseVersion", 1.toString())
        buildConfigField("String", "BASE_IMAGE_URL", "\"https://picsum.photos/\"")
        buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
    }

    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        val androidTest by getting
        val test by getting
        androidTest.java.srcDirs(sharedTestDir)
        test.java.srcDirs(sharedTestDir)
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
    implementation(project(remote))
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.View.material)
    implementation(Dependencies.View.constraintLayout)
    androidTestImplementation(Dependencies.Test.junitExt)
    androidTestImplementation(Dependencies.Test.espresso)

    //cache
    implementation(Dependencies.Room.room)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.AnnotationProcessor.room)

    //network
    implementation(Dependencies.Network.retrofit)


    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)

    //coroutines
    implementation(Dependencies.Coroutines.androidCoroutines)

    //test
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Test.mockWebServer)
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.junitExt)

    androidTestImplementation(Dependencies.Test.truth)
}