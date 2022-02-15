import Dependencies.Glide.Version.glideVersion
import Dependencies.Room.Version.roomVersion

const val kotlinAndroid: String = "android"
const val kotlinKapt: String = "kapt"
const val kotlinVersion = "1.6.10"

object Config {
    object Version {
        const val minSdkVersion = 21
        const val compileSdkVersion = 31
        const val targetSdkVersion = 31
        const val versionName = "1.0"
        const val versionCode = 1
    }

    const val isMultiDexEnabled = true

    object Android {
        const val applicationId = "com.example.nesineassignment"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx = "1.7.0"
            const val appCompat = "1.4.1"
            const val lifecycleKtx = "2.4.0"
        }

        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleKtx}"
        const val lifecycleKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        override val components: List<String>
            get() = listOf(coreKtx, lifecycle, lifecycleKtx, appCompat)
    }


    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.13.0"
        }

        object AnnotationProcessor {
            const val moshi = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String>
            get() = listOf(okhttp, loggingInterceptor, retrofit, retrofitMoshi, moshi)
    }

    object DI {
        object Version {
            const val daggerHilt = "2.38.1"
            const val androidXHilt = "1.0.0-alpha02"
        }

        object AnnotationProcessor {
            const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
            const val androidxHiltCompiler: String =
                "androidx.hilt:hilt-compiler:${Version.androidXHilt}"
        }

        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
    }

    object Coroutines {
        object Version {
            const val coroutines: String = "1.6.0"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val androidCoroutines: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    }

    object Navigation {
        object Version {
            const val navVersion = "2.4.1"
            const val fragment = "1.4.1"
        }

        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
        const val fragmentKtx: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
    }

    object Logger {
        object Version {
            const val timber = "4.7.1"
        }

        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Glide {
        object Version {
            const val glideVersion = "4.13.0"
        }

        object AnnotationProcessor {
            const val glideAnnotation = "com.github.bumptech.glide:compiler:$glideVersion"
        }

        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }

    object Room {
        object Version {
            const val roomVersion = "2.4.1"
        }

        object AnnotationProcessor {
            const val room = "androidx.room:room-compiler:$roomVersion"
        }

        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
    }

    object View{
        object Version{
            const val material = "1.5.0"
            const val constraintLayout = "2.1.3"
        }
        const val material = "com.google.android.material:material:${Version.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    }

    object Test {
        object Version {
            const val junit = "4.13.2"
            const val junitExt = "1.1.3"
            const val espresso = "3.4.0"
            const val truth = "1.1.3"
            const val mockWebServer = "4.9.3"
            const val mockk = "1.12.2"
        }

        const val junit = "junit:junit:${Version.junit}"
        const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val truth = "com.google.truth:truth:${Version.truth}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.Version.coroutines}"
    }
}

object ProjectLib {
    const val app: String = ":app"
    const val remote:String = ":remote"
    const val common:String = ":common"
    const val core:String = ":core"
}