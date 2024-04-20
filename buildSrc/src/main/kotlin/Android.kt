/**
* @Author hxy
* Create at 2023/10/24
* @desc 管理android官方依赖
*/
object Android {
    const val appcompat         = "androidx.appcompat:appcompat:1.4.1"
    const val coreKtx           = "androidx.core:core-ktx:1.7.0"
    const val constraintlayout  = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val legacy            = "androidx.legacy:legacy-support-v4:1.0.0"
    const val paging            = "androidx.paging:paging-runtime:2.1.2"
    const val viewpager2        = "androidx.viewpager2:viewpager2:1.1.0-alpha01"
    const val multidex          = "androidx.multidex:multidex:2.0.1"
    const val material          = "com.google.android.material:material:1.5.0"
    const val recyclerview      = "androidx.recyclerview:recyclerview:1.2.1"
    const val guava             = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"

    val lifecycle = Lifecycle
    object Lifecycle {
        private const val lifecycle_version = "2.4.0"
        const val liveDataKtx   = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        const val commonJava8   = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
        const val viewModelKtx  = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    }

    val navigation = Navigation
    object Navigation {
        private const val navigation_version = "2.3.2"
        const val fragmentKtx   = "androidx.navigation:navigation-fragment-ktx:$navigation_version"
        const val uiKtx         = "androidx.navigation:navigation-ui-ktx:$navigation_version"
    }

    val room = Room
    object Room {
        const val room_version = "2.4.2"
        const val room_runtime =  "androidx.room:room-runtime:${room_version}"
        const val room_compiler = "androidx.room:room-compiler:${room_version}"
        const val room_ktx = "androidx.room:room-ktx:${room_version}"
    }

    const val workManager = "androidx.work:work-runtime-ktx:2.7.1"

    val compose = Compose
    object Compose{
        private const val compose_version = "1.4.1"
        const val compose_ui = "androidx.compose.ui:ui:$compose_version"
        const val compose_material = "androidx.compose.material:material:$compose_version"
        const val compose_tooling_preview = "androidx.compose.ui:ui-tooling-preview:$compose_version"
    }
}