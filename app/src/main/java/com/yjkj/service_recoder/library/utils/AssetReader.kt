package com.yjkj.service_recoder.library.utils


import com.yjkj.service_recoder.MyApplication
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object AssetReader {

    fun readAssetFile(fileName : String):String{
        val context = MyApplication.context
        val stringBuilder = StringBuilder()
        try {
            // 打开assets文件夹下的文件
            val inputStream = context.assets.open(fileName)

            // 使用BufferedReader逐行读取文件内容
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }

            // 关闭流
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 返回文件内容字符串
        return stringBuilder.toString()
    }


    fun readAssetFileByIn(fileName: String): InputStream {
        return MyApplication.context.assets.open(fileName)
    }

}