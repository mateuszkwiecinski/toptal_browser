package pl.mkwiecinski.plugins.internal

import Kapt
import Libs
import TestLibs
import org.gradle.api.Project

internal fun Project.applyDagger() {
    dependencies.add("implementation", Libs.dagger)
    dependencies.add("kapt", Kapt.daggerCompiler)
}

internal fun Project.applyDaggerAndroid() {
    dependencies.add("implementation", Libs.daggerAndroid)
    dependencies.add("implementation", Libs.daggerAndroidSupport)
    dependencies.add("kapt", Kapt.daggerAndroidCompiler)
}

internal fun Project.configureUnitTest() {
    dependencies.add("testImplementation", TestLibs.junit4)
    dependencies.add("testImplementation", TestLibs.mockitoKotlin)
    dependencies.add("testImplementation", TestLibs.mockito)
    dependencies.add("testImplementation", TestLibs.assertJ)
    dependencies.add("testImplementation", TestLibs.coroutinesTest)
}
