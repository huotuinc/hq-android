package com.huotu.android.couponsleague.utils

import android.util.Log
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {
    internal var TAG = DateUtils::class.java.name

    //标准时间
    val TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    //标准时间01
    val DATE_FORMAT = "yyyy-MM-dd"

    /**
     * @throws
     * @方法描述：格式化获取的时间
     * @方法名：formatDate yyyy-MM-dd HH:mm:ss
     * @参数：@param currentTime
     * @参数：@return
     * @返回：String
     */
    fun formatDate(currentTime: Long?): String {
        var format: DateFormat? = null
        try {
            format = SimpleDateFormat(TIME_FORMAT)
            val date = Date(currentTime!!)
            return format.format(date)
        } catch (e: Exception) {
            //发现异常时，返回当前时间
            Log.e(TAG, e.message)
            return format!!.format(Date())
        }

    }

    /**
     * 秒数 转为 天小时分秒格式
     *
     * @throws
     * @创建人：jinxiangdong
     * @修改时间：2015年6月18日 下午3:05:24
     * @方法描述：
     * @方法名：toTime
     * @参数：@param totalSecond
     * @参数：@return
     * @返回：String
     */
    fun toTime(totalSecond: Int): String {
        var timeStr = ""
        val days = totalSecond / 60 / 60 / 24
        var remain = totalSecond % (60 * 60 * 24)
        val hours = remain / (60 * 60)
        remain = remain % (60 * 60)
        val minute = remain / 60
        val second = remain % 60
        if (days > 0) {
            timeStr = days.toString() + "天"
        }
        if (hours > 0) {
            timeStr += hours.toString() + "小时"
        }
        if (minute > 0) {
            timeStr += minute.toString() + "分"
        }
        if (second > 0) {
            timeStr += second.toString() + "秒"
        }
        return timeStr
    }

    fun formatDate(currentTime: Long?, fromat: String): String {
        var format: DateFormat? = null
        try {
            format = SimpleDateFormat(fromat)
            val date = Date(currentTime!!)
            return format.format(date)
        } catch (e: Exception) {
            //发现异常时，返回当前时间
            Log.e(TAG, e.message)
            return format!!.format(Date())
        }

    }

    /**
     * 判断日期是否是今天
     *
     * @throws
     * @创建人：jinxiangdong
     * @修改时间：2015年6月13日 下午4:22:37
     * @方法描述：
     * @方法名：isToday
     * @参数：@param currentTime
     * @参数：@return
     * @返回：Boolean
     */
    fun isToday(currentTime: Long?): Boolean? {
        var b = false
        val currentDateStr = formatDate(currentTime, DATE_FORMAT)
        val today = System.currentTimeMillis()

        val nowDateStr = formatDate(today, DATE_FORMAT)

        if (nowDateStr == currentDateStr) {
            b = true
        }
        return b
    }

    /**
     * @throws
     * @方法描述：
     * @方法名：toDate
     * @参数：@param str
     * @参数：@return
     * @返回：long
     */
    fun toDate(str: String, formatStr: String): Long {
        var format: DateFormat? = null

        try {
            format = SimpleDateFormat(formatStr)
            return format.parse(str).time
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            return 0L
        }

    }
}
