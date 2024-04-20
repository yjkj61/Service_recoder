package com.yjkj.service_recoder.library.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint


/**
 *@Created by houxiaoyao on 2022/3/23 16:26
 *
 */
object ImageUtils {
    /**
     * Return bitmap.
     *
     * @param filePath The path of file.
     * @return bitmap
     */
    fun getBitmap(filePath: String?): Bitmap? {
        return if (isSpace(filePath)) {
            null
        } else BitmapFactory.decodeFile(filePath)
    }

    private fun isSpace(s: String?): Boolean {
        if (s == null) {
            return true
        }
        var i = 0
        val len = s.length
        while (i < len) {
            if (!Character.isWhitespace(s[i])) {
                return false
            }
            ++i
        }
        return true
    }

    /**
     * 设置水印图片在左上角
     */
    fun createWaterMaskLeftTop(context: Context, src: Bitmap, watermark: Bitmap, paddingLeft: Int, paddingTop: Int): Bitmap {
        return createWaterMaskBitmap(src, watermark, dpToPx(context, paddingLeft), dpToPx(context, paddingTop))
    }

    /**
     * 设置水印图片到右上角
     */
    fun createWaterMaskRightTop(context: Context, src: Bitmap, watermark: Bitmap, paddingRight: Int, paddingTop: Int): Bitmap {
        return createWaterMaskBitmap(src, watermark, src.width - watermark.width - dpToPx(context, paddingRight), dpToPx(context, paddingTop))
    }

    /**
     * 设置水印图片到中间
     */
    fun createWaterMaskCenter(src: Bitmap, watermark: Bitmap): Bitmap {
        return createWaterMaskBitmap(src, watermark, (src.width - watermark.width) / 2, (src.height - watermark.height) / 2)
    }

    /**
     * 设置水印图片到左下角
     */
    fun createWaterMaskLeftBottom(context: Context, src: Bitmap, watermark: Bitmap, paddingLeft: Int, paddingBottom: Int): Bitmap {
        return createWaterMaskBitmap(src, watermark, dpToPx(context, paddingLeft), src.height - watermark.height - dpToPx(context, paddingBottom))
    }

    /**
     * 设置水印图片在右下角
     */
    fun createWaterMaskRightBottom(context: Context, src: Bitmap, watermark: Bitmap, paddingRight: Int, paddingBottom: Int): Bitmap {
        return createWaterMaskBitmap(src, watermark, src.width - watermark.width - dpToPx(context, paddingRight), src.height - watermark.height - dpToPx(context, paddingBottom))
    }

    /**
     * 添加图片水印
     */
    private fun createWaterMaskBitmap(bitmap: Bitmap, watermark: Bitmap, paddingLeft: Int, paddingTop: Int): Bitmap {
        if (bitmap == null) {
            return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Return a 1x1 pixel bitmap if the source bitmap is null
        }
        val width = bitmap.width
        val height = bitmap.height
        val newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newb)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.drawBitmap(watermark, paddingLeft.toFloat(), paddingTop.toFloat(), null)
        canvas.save()
        canvas.restore()
        return newb
    }

    /**
     * 给图片添加文字到左上角
     */
    fun drawTextToLeftTop(context: Context, bitmap: Bitmap, text: String, size: Int, color: Int, paddingLeft: Int, paddingTop: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = dpToPx(context, size).toFloat()
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return drawTextToBitmap(context, bitmap, text, paint, bounds, dpToPx(context, paddingLeft), dpToPx(context, paddingTop) + bounds.height())
    }

    /**
     * 给图片添加文字到右上角
     */
    fun drawTextToRightTop(context: Context, bitmap: Bitmap, text: String, size: Int, color: Int, paddingRight: Int, paddingTop: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = dpToPx(context, size).toFloat()
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return drawTextToBitmap(context, bitmap, text, paint, bounds, bitmap.width - bounds.width() - dpToPx(context, paddingRight), dpToPx(context, paddingTop) + bounds.height())
    }

    /**
     * 给图片添加文字到中间
     */
    fun drawTextToCenter(context: Context, bitmap: Bitmap, text: String, size: Int, color: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = dpToPx(context, size).toFloat()
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return drawTextToBitmap(context, bitmap, text, paint, bounds, (bitmap.width - bounds.width()) / 2, (bitmap.height + bounds.height()) / 2)
    }

    /**
     * 给图片添加文字到左下角
     */
    fun drawTextToLeftBottom(context: Context, bitmap: Bitmap, text: String, size: Int, color: Int, paddingLeft: Int, paddingBottom: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = dpToPx(context, size).toFloat()
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return drawTextToBitmap(context, bitmap, text, paint, bounds, dpToPx(context, paddingLeft), bitmap.height - dpToPx(context, paddingBottom))
    }

    /**
     * 给图片添加文字到右下角
     */
    fun drawTextToRightBottom(context: Context, bitmap: Bitmap, text: String, size: Int, color: Int, paddingRight: Int, paddingBottom: Int): Bitmap {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = color
        paint.textSize = dpToPx(context, size).toFloat()
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return drawTextToBitmap(context, bitmap, text, paint, bounds, bitmap.width - bounds.width() - dpToPx(context, paddingRight), bitmap.height - dpToPx(context, paddingBottom))
    }


    fun drawTextToLeftBottom2(context: Context, bitmap: Bitmap, watermarkText: String, size: Int, color: Int, paddingRight: Int, paddingBottom: Int):Bitmap{
        // 创建一个新的Bitmap，防止修改原始Bitmap
        val newBitmap = bitmap.copy(bitmap.config, true)

        // 创建Canvas，以便在新的Bitmap上绘制文字
        val canvas = Canvas(newBitmap)

        // 创建TextPaint和设置相关属性
        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            this.textSize = size.toFloat()
            this.style = Paint.Style.FILL
        }

        // 创建StaticLayout
        val textWidth = newBitmap.width - 40 // 控制文字区域宽度，这里减去40是为了左右各留20的边距
        val staticLayout = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(watermarkText, 0, watermarkText.length, paint, textWidth)
                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                .setLineSpacing(0f, 1.2f) // 设置行间距
                .setIncludePad(false)
                .build()
        } else {
            StaticLayout(watermarkText, paint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0f, false)
        }

        // 在画布上绘制文字
        val startX = newBitmap.width - staticLayout.width - 20f // 右边留20的边距
        val startY = newBitmap.height - staticLayout.height - 20f // 底部留20的边距

        canvas.save()
        canvas.translate(startX, startY)
        staticLayout.draw(canvas)
        canvas.restore()

        return newBitmap
    }


    /**
     * 在图片上绘制文字
     */
    private fun drawTextToBitmap(context: Context, bitmap: Bitmap, text: String, paint: Paint, bounds: Rect, paddingLeft: Int, paddingTop: Int): Bitmap {
        val bitmapConfig = bitmap.config ?: Bitmap.Config.ARGB_8888
        paint.isDither = true
        paint.isFilterBitmap = true
        val newBitmap = bitmap.copy(bitmapConfig, true)
        val canvas = Canvas(newBitmap)
        canvas.drawText(text, paddingLeft.toFloat(), paddingTop.toFloat(), paint)
        return newBitmap
    }

    /**
     * dip转pix
     */
    fun dpToPx(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }


    enum class ColorType {
        LIGHT,
        DARK,
        UNKNOWN
    }


}