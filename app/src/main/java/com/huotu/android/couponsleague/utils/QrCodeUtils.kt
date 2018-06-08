package com.huotu.android.couponsleague.utils

import android.graphics.Bitmap
import com.google.zxing.WriterException
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
//import Kotlin.collections.HashMap
import android.content.Context


object QrCodeUtils {

    fun createQrCode(context: Context, url:String , width :Int,height:Int):Bitmap?{
        val qrCodeWriter = QRCodeWriter()
        val hints = HashMap<EncodeHintType,String>()
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8")
        try {
            val encode = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints)
            val pixels = IntArray(width * height)
            for (i in 0 until height) {
                for (j in 0 until width) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000
                    } else {
                        pixels[i * width + j] = -0x1
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565)
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return null
    }
}