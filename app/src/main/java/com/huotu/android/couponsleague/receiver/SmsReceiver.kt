package com.huotu.android.couponsleague.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.text.TextUtils
import com.huotu.android.couponsleague.utils.DateUtils

/**
 * Created by jinxiangdong on 2018/4/3.
 */

class SmsReceiver : BroadcastReceiver() {
    private var smsListener: SmsListener? = null

    fun setSmsListener(smsListener: SmsListener?) {
        this.smsListener = smsListener
    }

    interface SmsListener {
        fun smsCallback(message: String)
    }

    override fun onReceive(context: Context, intent: Intent) {

        val bd = intent.extras ?: return
        if (!bd.containsKey("pdus")) return
        val bytes = bd.get("pdus") as Array<Any>
        if (bytes == null || bytes.size < 1) return
        for (i in bytes.indices) {
            val smsMessage = getSmsMessage(bytes[i], bd)
            val phone = smsMessage.displayOriginatingAddress
            val content = smsMessage.displayMessageBody
            val time = DateUtils.formatDate(smsMessage.timestampMillis, DateUtils.TIME_FORMAT)

            if (!TextUtils.isEmpty(content)) {
                if (smsListener != null) {
                    smsListener!!.smsCallback(content)
                }
            }
        }
    }

    private fun getSmsMessage(`object`: Any, bd: Bundle): SmsMessage {
        val smsMessage: SmsMessage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bd.getString("format")
            smsMessage = SmsMessage.createFromPdu(`object` as ByteArray, format)
        } else {
            smsMessage = SmsMessage.createFromPdu(`object` as ByteArray)
        }
        return smsMessage

    }
}
