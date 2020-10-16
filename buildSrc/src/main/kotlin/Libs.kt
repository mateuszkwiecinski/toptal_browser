private const val DAGGER_VERSION = "2.29.1"

object Libs {
    const val dagger = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val daggerAndroid = "com.google.dagger:dagger-android:$DAGGER_VERSION"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$DAGGER_VERSION"
}

object TestLibs {
    const val junit4 = "junit:junit:4.13.1"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    const val mockito = "org.mockito:mockito-core:3.5.13"
    const val assertJ = "org.assertj:assertj-core:3.17.2"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.0-M1"
}

object Kapt {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$DAGGER_VERSION"
    const val daggerAndroidCompiler = "com.google.dagger:dagger-android-processor:$DAGGER_VERSION"
}
