/**
* @Author hxy
* Create at 2023/10/24
* @desc 管理kotlin相关依赖
*/
object Kotlin {
    /**
     * kotlin标准库
     */
    const val kotlinVersion = "1.7.0"
    const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    /**
     * 协程依赖
     */
    val coroutines = Coroutines
    object Coroutines {
        private const val coroutines_version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }
}