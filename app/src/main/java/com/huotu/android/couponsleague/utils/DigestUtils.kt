package com.huotu.android.couponsleague.utils

import java.security.MessageDigest

class DigestUtils {

    /**
     * Used to build output as Hex
     */
    private val DIGITS_LOWER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    fun md5(str: String?): String? {
        if (str == null || "" == str.trim { it <= ' ' }) {
            return null
        }
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.update(str.toByteArray(charset("utf-8")))
            return String(encodeHex(messageDigest.digest()))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun encodeHex(data: ByteArray): CharArray {
        val l = data.size
        val out = CharArray(l shl 1)
        // two characters form the hex value.
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = DIGITS_LOWER[(0xF0 and data[i].toInt()).ushr(4)]
            out[j++] = DIGITS_LOWER[0x0F and data[i].toInt()]
            i++
        }
        return out
    }


    companion object {
        private val instance:DigestUtils = DigestUtils()
        fun getInstance():DigestUtils{
            return instance
        }
    }

}