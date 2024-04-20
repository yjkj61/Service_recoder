package com.yjkj.service_recoder.library.utils.ext

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import com.google.gson.GsonBuilder
import com.yjkj.service_recoder.library.utils.ImageUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.math.RoundingMode
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random


/**
 * @author iec
 * create at 2023/11/9 20:04
 * 通用扩展方法
 */

fun List<Any>.toJson():String{
    val gson = GsonBuilder().disableHtmlEscaping().create()
    return gson.toJson(this)
}


fun String.log(tag : String = "body"):String{
    Log.d(tag,this)
    return this
}

/**
 * bitmap转file
 */
fun Bitmap.toFile(path : String,filename: String):File?{
    val file = File(path,filename)
    val bos = BufferedOutputStream(FileOutputStream(file))
    
    try {
        this.compress(Bitmap.CompressFormat.JPEG,100,bos)
        bos.flush()

        return file
    }catch (e : IOException){
        e.printStackTrace()
    }finally {
        bos.close()
    }

    return file
}

fun Bitmap.saveToFile(context : Context,storageDir : String,fileName: String):String{
    // 创建文件
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH,  storageDir)
    }

    val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    imageUri?.let { uri ->
        resolver.openOutputStream(uri)?.use { outputStream ->
            this.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        return uri.toString()
    } ?: throw Exception("Failed to create image file")
}

fun RequestBody.toMultipartBody(filename : String):MultipartBody.Part{
    return MultipartBody.Part.createFormData("file",filename,this)
}


fun Float.toHalfUp(pattern : String = "#.#"):Float{
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.roundingMode = RoundingMode.HALF_UP
    return decimalFormat.format(this).toFloat()
}

/**
 * 只表示当天的时间戳，不包含时分秒
 */
@SuppressLint("NewApi")
fun Long.convertToDayTimestamp():Long{
    val instant = Instant.ofEpochMilli(this)
    val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
    return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

/**
 * 表示某一天最后一秒的时间戳
 */
@SuppressLint("NewApi")
fun Long.convertToDayLastTimestamp():Long{
    val instant = Instant.ofEpochMilli(this)
    val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    val endOfDay = localDateTime.withHour(23).withMinute(59).withSecond(59).withNano(999999999)
    return endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

/**
 * 判断时间戳是否是今年
 */
@SuppressLint("NewApi")
fun Long.timestampThisYear():Boolean{
    val instant = Instant.ofEpochMilli(this)
    val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()

    val currentYear = LocalDate.now().year

    return localDate.year == currentYear
}

@SuppressLint("NewApi")
fun getCurrentYearLastMillis():Long{
    val lastSecondOfYear = LocalDateTime.of(LocalDateTime.now().year, Month.DECEMBER, 31, 23, 59, 59)
    return lastSecondOfYear.toEpochSecond(ZoneOffset.UTC)
}

/**
 * 根据文件path获取文件
 */
fun String.toFile():File{
    return File(this)
}






/**
 * 获取string MD5值
 */
fun String.md5():String{
    try {
        //获取md5加密对象
        val instance: MessageDigest = MessageDigest.getInstance("MD5")
        //对字符串加密，返回字节数组
        val digest:ByteArray = instance.digest(this.toByteArray())
        var sb : StringBuffer = StringBuffer()
        for (b in digest) {
            //获取低八位有效值
            var i :Int = b.toInt() and 0xff
            //将整数转化为16进制
            var hexString = Integer.toHexString(i)
            if (hexString.length < 2) {
                //如果是一位的话，补0
                hexString = "0" + hexString
            }
            sb.append(hexString)
        }
        return sb.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

fun File.getBitmap():Bitmap?{
    return ImageUtils.getBitmap(this.absolutePath)
}

/**
 * android 10 uri转file
 */
@SuppressLint("NewApi")
fun Context.uriToFileQ(uri: Uri): File? =
    if (uri.scheme == ContentResolver.SCHEME_FILE)
        File(requireNotNull(uri.path))
    else if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
        //把文件保存到沙盒
        val contentResolver = this.contentResolver
        val displayName = "${System.currentTimeMillis()}${Random.nextInt(0, 9999)}.${
            MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(contentResolver.getType(uri))}"
        val ios = contentResolver.openInputStream(uri)
        if (ios != null) {
            File("${this.cacheDir.absolutePath}/$displayName")
                .apply {
                    val fos = FileOutputStream(this)
                    FileUtils.copy(ios, fos)
                    fos.close()
                    ios.close()
                }
        } else null
    } else null
/**
 * 根据文件地址创建文件
 */
fun Context.pathToFile(path : String) : File?{
    return File(path)
}

fun Uri.toFile(){

}

fun String?.let2(block : (it : String)->Unit){
    if(!this.isNullOrEmpty()){
        block.invoke(this)
    }
}

fun String.ifNull(block: () -> String):String{
    return if(this.isNullOrEmpty()){
        block.invoke()
    }else{
        this
    }
}

fun Int?.ifNull(defaultValue : ()->Int?):Int?{
    return this ?: defaultValue.invoke()
}

fun Int.ifNullOrZero(defaultValue : () -> String = { "" }):String{
    return if(this == null || this == 0){
        defaultValue.invoke()
    }else{
        "$this"
    }
}

fun Float.ifNullOrZero(defaultValue : () -> String = { "" }):String{
    return if(this == null || this == 0f){
        defaultValue.invoke()
    }else{
        "$this"
    }
}

fun Any?.ifNull(defaultValue : ()->Any?):Any?{
    return this ?: defaultValue.invoke()
}

/**
 * m转千米
 */
fun Double.metersToKilometers():String{
    val kilometers = this / 1000.0
    return "%.1f".format(kilometers).toDouble().toString()
}

fun Double.metersFormatKilometers():String{
    if(this < 1000.0){
        return "${String.format("%.1",this)}米"
    }
    return "${String.format("%.1",this.metersToKilometers())}公里"
}

fun Int.metersToKilometers():String{
    return if (this < 1000) {
        "${this}米"
    } else {
        val kilometers = this / 1000
        val remainingMeters = this % 1000
        if (remainingMeters == 0) {
            "${kilometers}公里"
        } else {
            val formattedKilometers = String.format("%.1f", kilometers + remainingMeters / 1000.0)
            "${formattedKilometers}公里"
        }
    }
}

/**
 * 将距离/m转换为km（无单位）
 */
fun Int.metersToKilometersNoUnit():String{
    return if (this < 1000) {
        "$this"
    } else {
        val kilometers = this / 1000
        val remainingMeters = this % 1000
        if (remainingMeters == 0) {
            "$kilometers"
        } else {
            val formattedKilometers = String.format("%.1f", kilometers + remainingMeters / 1000.0)
            formattedKilometers
        }
    }
}

fun Int.metersToKilometersUnit():String{
    return if (this < 1000) {
        "米"
    } else {
        "公里"
    }
}

/**
 * 根据时间/S计算预计到达时间
 */
fun Int.calculateArrivalTime(): String {

    val now = Calendar.getInstance()
    val arrivalTime = Calendar.getInstance()
    arrivalTime.timeInMillis = now.timeInMillis + this * 1000

    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateFormatter = SimpleDateFormat("MM月dd日HH:mm", Locale.getDefault())

    return if (now.get(Calendar.DATE) == arrivalTime.get(Calendar.DATE)) {
        val timeFormat = if (arrivalTime.get(Calendar.HOUR_OF_DAY) < 12) "上午" else if (arrivalTime.get(Calendar.HOUR_OF_DAY) < 18) "下午" else "晚上"
        "$timeFormat${timeFormatter.format(arrivalTime.time)}到达"
    } else {
        "${dateFormatter.format(arrivalTime.time)}到达"
    }
}

/**
 * 将时间/s转换为HH:MM
 */
fun Int.secondsToHoursMinutes():String{
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    return when {
        hours > 0 && minutes > 0 -> "${hours}小时${minutes}分钟"
        hours > 0 -> "${hours}小时"
        minutes > 0 -> "${minutes}分钟"
        else -> ""
    }
}


fun MutableList<String>.add2(value : String){
    if(!this.contains(value)){
        this.add(value)
    }
}


/**
 * map转requestBody
 */
fun Map<String,Any>.toRequestBody(): RequestBody {
    val jsonObject = JSONObject(this)
    return jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
}

/**
 * 判断字符串是否保留了三位小数
 */
fun String.hasThreeDecimalPlaces(): Boolean {
    val regex = Regex("\\.\\d{3}$")
    return regex.containsMatchIn(this)
}



