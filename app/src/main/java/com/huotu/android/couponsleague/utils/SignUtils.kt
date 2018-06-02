package com.huotu.android.couponsleague.utils

import java.net.URLDecoder
import java.util.*

class SignUtils {

    fun sign( map:Map<String,String?> , appSecret : String ):String{
        if( map.size<1) return ""

        var result = removeNull(map)
        result = decode(result)

        val resultString = sort(result, appSecret)

        return md5(resultString)
    }

    /**
     * 移除参数中空的参数
     * @param map
     */
    private fun removeNull(map: Map<*, *>): Map<String, String> {
        val lowerMap = HashMap<String, String>()
        val lowerIt = map.entries.iterator()
        while (lowerIt.hasNext()) {
            val entry = lowerIt.next()
            val value = entry.value
            if (null != value && value!!.toString().isEmpty() == false) {
                lowerMap[entry.key.toString().toLowerCase()] = value.toString()
            }
        }
        return lowerMap
    }

    private fun decode( map:  Map<*, *>):Map<String,String> {
        val lowerIt = map.entries.iterator()

        var lowMap = HashMap<String,String>()

        while (lowerIt.hasNext()) {

            val entry = lowerIt.next()
            var key = entry.key.toString()
            key = key.toLowerCase()
            val value = entry.value.toString()
            try {
                val desCodeValue :String = URLDecoder.decode(value, "utf-8")

                //entry.value =desCodeValue

                lowMap.put( key , desCodeValue)

            } catch (ex: Exception) {
                lowMap.put(key , value)
            }
        }

        return lowMap
    }

    /**
     * 对参数按字母排序，组成key+value,最后加+appsecret返回字符串
     * @param map
     * @return
     */
    private fun sort(map: Map<String, String>?, appSecret: String): String {
        //map.put("appSecret", Constants.APP_SECRET);
        if (map == null) return ""
        val buffer = StringBuffer()
        var sortMap = map.toSortedMap()
//        val arrayList = ArrayList(map.entries)

//        Collections.sort<Any>(
//                arrayList, object : Comparator<Any> {
//            override fun compare(arg1: Any, arg2: Any): Int {
//                val obj1 = arg1
//                val obj2 = arg2
//                return obj1.key.toString().compareTo(
//                        obj2.key as String
//                )
//            }
//        }
//        )

        //
        val iter = sortMap.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            val key = entry.key as String
            buffer.append(key)
            buffer.append(map[key])
        }

        buffer.append(appSecret)

        return buffer.toString()
    }

    /**
     * 对字符串进行md5加密，转大写
     * @param result
     * @return
     */
    private fun md5(result: String): String {
        val md5String = DigestUtils.getInstance().md5(result)
        return md5String!!.toUpperCase()
    }

    companion object {
        private var instance = SignUtils()
        fun getInstance():SignUtils{
            return instance
        }
    }
}