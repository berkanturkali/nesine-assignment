import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
buildscript {
    repositories.applyDefault()
}
subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs + ("-Xuse-experimental=kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.ObsoleteCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview,"
                    )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}