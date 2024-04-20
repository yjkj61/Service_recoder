/**
* @Author hxy
* Create at 2023/10/24
* @desc 管理第三方依赖
*/
object ThirdParty {
    /**
     * Jetpack-MVVM-Scaffold
     */
    const val unpeek_livedata = "com.kunminx.arch:unpeek-livedata:7.2.0-beta1"
    const val smooth_navigation = "com.kunminx.arch:smooth-navigation:4.0.0-beta1"
    const val strict_databinding = "com.kunminx.arch:strict-databinding:4.0.0-beta1"

    const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
    const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.14"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val cookieJar = "com.github.franmontiel:PersistentCookieJar:v1.0.1"
    const val eventBus = "org.greenrobot:eventbus:3.2.0"

    const val aviUi = "com.wang.avi:library:2.1.3"
    const val banner = "io.github.youth5201314:banner:2.2.2"
    const val magic = "com.github.hackware1993:MagicIndicator:1.6.0"
    const val easypermissions = "pub.devrel:easypermissions:3.0.0"
    const val labelsView = "com.github.donkingliang:LabelsView:1.6.1"
    const val shadow = "com.github.lihangleo2:ShadowLayout:3.1.3"
    const val autoSize = "me.jessyan:autosize:1.2.1"

    val retrofit = Retrofit
    object Retrofit {
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val scalars = "com.squareup.retrofit2:converter-scalars:$retrofit_version"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val adapter = "com.squareup.retrofit2:adapter-rxjava2:2.3.0"
    }

    val glide = Glide
    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.13.0"
        const val transformations = "jp.wasabeef:glide-transformations:4.3.0"
        const val kapt = "com.github.bumptech.glide:compiler:4.13.0"
    }

    val pictureselector = PictureSelector
    object PictureSelector{
        private const val selector_version = "v3.0.9"
        const val common = "io.github.lucksiege:pictureselector:$selector_version"
        const val compress = "io.github.lucksiege:compress:$selector_version"
        const val ucrop = "io.github.lucksiege:ucrop:$selector_version"
    }

    const val hotfix = "com.aliyun.ams:alicloud-android-hotfix:3.3.5"

    val smart = Smart
    object Smart {
        private const val smart_version = "2.0.5"
        const val smart_core = "io.github.scwang90:refresh-layout-kernel:$smart_version"
        const val classics_head = "io.github.scwang90:refresh-header-classics:$smart_version"
        const val radar_head = "io.github.scwang90:refresh-header-radar:$smart_version"
        const val falsify_head = "io.github.scwang90:refresh-header-falsify:$smart_version"
        const val material_head = "io.github.scwang90:refresh-header-material:$smart_version"
        const val two_level_head = "io.github.scwang90:refresh-header-two-level:$smart_version"
        const val footer_ball = "io.github.scwang90:refresh-footer-ball:$smart_version"
        const val footer_classics = "io.github.scwang90:refresh-footer-classics:$smart_version"
    }

    val blockCanary = BlockCanary
    object BlockCanary {
        private const val blockCanary_version = "1.5.0"
        const val blockCanary = "com.github.markzhai:blockcanary-android:$blockCanary_version"
    }

    const val utilcode = "com.blankj:utilcodex:1.31.0"

    val zxing = Zxing
    object Zxing{
        const val zxing_core = "com.google.zxing:core:3.2.1"
        const val zxing_embedded = "com.journeyapps:zxing-android-embedded:3.3.0"
        const val yipianfengye = "cn.yipianfengye.android:zxing-library:2.2"
    }

    val dialog = MaterialDialog
    object MaterialDialog{
        const val material_dialog ="com.afollestad.material-dialogs:core:3.3.0"
        const val material_dialog_input ="com.afollestad.material-dialogs:input:3.3.0"
    }

    val netty = Netty
    object Netty{
        const val netty = "io.netty:netty-all:4.1.43.Final"
    }

    val bindingCollectionAdapter = BindingCollectionAdapter
    object BindingCollectionAdapter{
        const val bindingCollectionAdapter = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:4.0.0"
        const val bindinghCollectionAdapter_rv = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0"
        const val bindinghCollectionAdapter_vp = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-viewpager2:4.0.0"

    }

    val downloadFile = Aria
    object Aria{
        const val aria_core = "me.laoyuyu.aria:core:3.8.16"
        const val aria_compiler = "me.laoyuyu.aria:compiler:3.8.16"
    }

    val umeng = Umeng
    object Umeng{
        const val common = "com.umeng.umsdk:common:9.4.2"
        const val asms = "com.umeng.umsdk:asms:1.4.1"
        const val apm = "com.umeng.umsdk:apm:1.4.2"
    }

    //手势密码
    val patternlock = Patternlock
    object Patternlock{
        const val patternlockview = "com.andrognito.pat ternlockview:patternlockview:1.0.0"
        const val patternlockview_reactive = "com.andrognito.patternlockview:patternlockview-reactive:1.0.0"
    }

    //滚动选择器
    val pickView = PickView
    object PickView{
        const val pick_view_version = "4.1.9"
        const val wheelView = "com.github.gzu-liyujiang.AndroidPicker:WheelView:$pick_view_version"
    }

    //flexbox布局
    const val flexbox = "com.google.android.flexbox:flexbox:3.0.0"

    //litePal数据库
//    const val litePal = "org.litepal.guolindev:core:3.2.3"

    //persmission
    const val easypermission = "pub.devrel:easypermissions:3.0.0"

    const val htmlSpanner = "com.github.NightWhistler:HtmlSpanner:0.4"

    //日历控件
    const val calendarview = "com.haibin:calendarview:3.7.1"

    //dsltablayout
    const val dslTablayout = "com.github.angcyo.DslTablayout:TabLayout:2.0.0"
    const val dslVP2Delegate  = "com.github.angcyo.DslTablayout:ViewPager2Delegate:1.4.2"
    const val dslVP1Delegate  = "com.github.angcyo.DslTablayout:ViewPager1Delegate:1.4.2"

    const val swipeLayout = "com.github.mcxtzhang:SwipeDelMenuLayout:V1.3.0"

    const val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7"

    const val mmkv = "com.tencent:mmkv:1.2.13"

    //danmu
    const val danmu_version = "0.9.25"
    const val danmu = "com.github.CarGuo.DanmakuFlameMaster:DanmakuFlameMaster:${danmu_version}"
    const val ndkbitmap_armv7a = "com.github.CarGuo.DanmakuFlameMaster:ndkbitmap-armv7a:${danmu_version}"
    const val ndkbitmao_x86 = "com.github.CarGuo.DanmakuFlameMaster:ndkbitmap-x86:${danmu_version}"
    const val ndkbitmap_armv5  = "com.github.CarGuo.DanmakuFlameMaster:ndkbitmap-armv5:${danmu_version}"

    //aria
    const val aria_version = "3.8.16"
    const val aria_core = "me.laoyuyu.aria:core:${aria_version}"
    const val aria_kapt = "me.laoyuyu.aria:compiler:${aria_version}"

    //状态栏
    const val immersionbar  = "com.geyifeng.immersionbar:immersionbar:3.2.2"
    const val immersionbar_ktx = "com.geyifeng.immersionbar:immersionbar-ktx:3.2.2"

    const val ratingBar = "com.github.wdsqjq:AndRatingBar:1.0.6"

    const val tencent_im_sdk = "com.tencent.imsdk:imsdk-plus:5.4.666"

    const val layoutManagerGroup = "com.github.DingMouRen:LayoutManagerGroup:1e6f4f96eb"

    //高德地图
    val aMap = AMap
    object AMap{
        const val location = "com.amap.api:location:5.4.0"
        const val search = "com.amap.api:search:7.9.0"
        const val amap3d = "com.amap.api:3dmap:8.0.0"
        const val navi = "com.amap.api:navi-3dmap:latest.integration"
    }

    //Net
    const val net = "com.github.liangjingkanji:Net:3.5.2"

    const val apache = "org.apache.commons:commons-lang3:3.8.1"

    const val bugly = "com.tencent.bugly:crashreport:latest.release"

    const val nice_spinner = "com.github.arcadefire:nice-spinner:1.4.4"

    //阿里热修复
    const val hot_fix = "com.aliyun.ams:alicloud-android-hotfix:3.3.5"

    val butterknife = Butterknife
    object Butterknife{
        const val butterknife_version = "10.0.0"
        const val butterknife = "com.jakewharton:butterknife:${butterknife_version}"
        const val compiler = "com.jakewharton:butterknife-compiler:${butterknife_version}"
    }

    val rxlifecycle = RXlifecycle
    object RXlifecycle{
        const val rxlifecycle_version = "0.3.0"
        const val rxlifecycle = "com.trello:rxlifecycle:${rxlifecycle_version}"
        const val components = "com.trello:rxlifecycle-components:${rxlifecycle_version}"
    }

    const val rxPermissions = "com.github.tbruyelle:rxpermissions:0.10.2"

    const val ion = "com.koushikdutta.ion:ion:2.2.1"
    const val avi = "com.wang.avi:library:2.1.3"
    const val logutils = "com.apkfuns.logutils:library:1.7.5"
    const val lightRefresh = "com.github.BLCodes:LightRefresh:1.0.3-beta-6"
    const val aspectjrt = "org.aspectj:aspectjrt:1.8.9"

    const val amqp_client = "com.rabbitmq:amqp-client:5.7.0"
    const val usb_serial = "com.github.mik3y:usb-serial-for-android:3.4.2"
    const val AndroidPicker = "com.github.gzu-liyujiang.AndroidPicker:Common:4.1.6"
    const val AndroidPicker_WheelPicker = "com.github.gzu-liyujiang.AndroidPicker:WheelPicker:4.1.6"
    //新手引导层
    const val NewbieGuide = "com.github.huburt-Hu:NewbieGuide:v2.4.0"

    const val wechat_share = "com.tencent.mm.opensdk:wechat-sdk-android:+"

    const val xpopup = "com.github.li-xiaojun:XPopup:2.8.9"

    //UI 一揽子方案
    const val xui = "com.github.xuexiangjys:XUI:1.1.9"

    //滚动布局
    const val parallaxscroll = "com.github.nirhart:parallaxscroll:1.0"

    const val circularprogressbar = "com.mikhaellopez:circularprogressbar:3.1.0"
}