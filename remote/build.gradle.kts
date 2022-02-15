plugins {
    kotlinLibrary
    kotlinJvm
    kotlin(kotlinKapt)
}

dependencies {
    implementAll(Dependencies.Network.components)
    implementation(Dependencies.DI.hiltCore)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)
}