package com.yjkj.service_recoder.library.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUriUtils {

    fun getFilePathFromUri(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val scheme = uri.scheme

        if (scheme == "content") {
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
                    if (columnIndex != -1) {
                        filePath = it.getString(columnIndex)
                    } else {
                        val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (displayNameIndex != -1) {
                            val fileName = it.getString(displayNameIndex)
                            val cacheDir = context.cacheDir
                            val file = File(cacheDir, fileName)
                            copyInputStreamToFile(context.contentResolver.openInputStream(uri), file)
                            filePath = file.absolutePath
                        }
                    }
                }
            }
        } else if (scheme == "file") {
            filePath = uri.path
        }

        return filePath
    }

    private fun copyInputStreamToFile(inputStream: InputStream?, file: File) {
        try {
            inputStream?.use { input ->
                FileOutputStream(file).use { output ->
                    val buffer = ByteArray(4 * 1024) // buffer size
                    var byteCount: Int
                    while (input.read(buffer).also { byteCount = it } != -1) {
                        output.write(buffer, 0, byteCount)
                    }
                    output.flush()
                }
            }
        } catch (e: Exception) {
            Log.e("FileUriUtils", "Error copying InputStream to File: $e")
        }
    }


    fun isUriExists(context: Context, uri: Uri): Boolean {
        var inputStream: InputStream? = null
        return try {
            inputStream = context.contentResolver.openInputStream(uri)
            inputStream != null
        } catch (e: IOException) {
            false
        } finally {
            inputStream?.close()
        }
    }

    fun uriToInputStream(context: Context, uri: Uri): InputStream? {
        return try {
            context.contentResolver.openInputStream(uri)
        } catch (e: Exception) {
            null
        }
    }

}