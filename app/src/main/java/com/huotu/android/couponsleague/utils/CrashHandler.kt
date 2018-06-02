package com.huotu.android.couponsleague.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.util.Date
import java.util.HashMap

/**
 * Created by Administrator on 2015/10/9.
 */
class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {
    //系统默认的UncaughtException处理类
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    //程序的Context对象
    private var mContext: Context? = null
    //
    //用来存储设备信息和异常信息
    private val infos = HashMap<String, String>()

    object Holder {
        val instance = CrashHandler()
    }


    /**
     * 初始化
     *
     * @param context
     */
    fun init(context: Context) {
        mContext = context
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }


    override fun uncaughtException(thread: Thread, ex: Throwable) {

        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler!!.uncaughtException(thread, ex)
        } else {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                Log.e(TAG, e.message)
            }

            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }


        //使用Toast来显示异常信息
        object : Thread() {
            override fun run() {
                Looper.prepare()
                ToastUtils.getInstance().showShortToast("很抱歉,程序出现异常,即将退出.")
                Looper.loop()
            }
        }.start()
        //收集设备参数信息
        collectDeviceInfo(mContext)
        saveCrashInfo2File(ex)

        return true
    }


    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    fun collectDeviceInfo(ctx: Context?) {
        try {
            val pm = ctx!!.packageManager
            val pi = pm.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
            if (pi != null) {
                val versionName = if (pi.versionName == null) "null" else pi.versionName
                val versionCode = pi.versionCode.toString() + ""
                infos["versionName"] = versionName
                infos["versionCode"] = versionCode
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "an error occured when collect package info")
        }

        val fields = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                infos[field.name] = field.get(null).toString()
                Log.d(TAG, field.name + " : " + field.get(null))
            } catch (e: Exception) {
                Log.e(TAG, "an error occured when collect crash info", e)
            }

        }
    }

    private fun saveCrashInfo2File(ex: Throwable): String? {

        val sb = StringBuffer()
        for ((key, value) in infos) {
            sb.append("$key=$value\n")
        }

        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause: Throwable? = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result = writer.toString()
        Log.d(TAG, result)
        sb.append(result)
        try {
            val timestamp = System.currentTimeMillis()
            val time = DateUtils.formatDate(Date().time)
            val fileName = "$time-$timestamp.log"
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val path = Environment.getExternalStorageDirectory().toString() + "/coupons/crash"
                val dir = File(path)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val fos = FileOutputStream("$path/$fileName")
                fos.write(sb.toString().toByteArray())
                fos.close()
            }
            return fileName
        } catch (e: Exception) {
            Log.e(TAG, "an error occured while writing file...", e)
        }

        return null
    }

    companion object {
        val TAG = CrashHandler::class.java.name

        val instance = CrashHandler()

//        val instance: CrashHandler
//            get() = Holder.instance
    }

}
