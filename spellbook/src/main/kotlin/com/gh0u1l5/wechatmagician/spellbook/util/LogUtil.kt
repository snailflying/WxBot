package com.gh0u1l5.wechatmagician.spellbook.util

import android.util.Log
import de.robv.android.xposed.XposedBridge

/**
 * @Author Aaron
 * @Email aaron@merculet.io
 * @Date 2018/12/12
 * @Description printObjectFields只能用在插件代码类，不能用在APP相关类内，因为XposedBridge用到compileOnly引入的
 */
object LogUtil {

    private val MIN_STACK_OFFSET = 3
    private val SINGLE_DIVIDER = "- - - - - - - - - - - - - - - - - -"
    private val BR = System.getProperty("line.separator")
    private const val TAG = "LogUtil"
    // 打印信息 by aaron 2018/12/12
    fun log(msg: String) {
        Log.w(TAG, getMethodName() + msg + SINGLE_DIVIDER)
    }

    // 打印Exception by aaron 2018/12/12
    fun log(e: Throwable) {
        Log.e(TAG, "msg:$e")
    }

    // 打印堆栈 by aaron 2018/12/12
    fun logStackTraces(methodCount: Int = 15, methodOffset: Int = 3) {
        val trace = Thread.currentThread().stackTrace
        var level = ""
        XposedBridge.log("---------logStackTraces start----------")
        for (i in methodCount downTo 1) {
            val stackIndex = i + methodOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = StringBuilder()
            builder.append("|")
                    .append(' ')
                    .append(level)
                    .append(trace[stackIndex].className)
                    .append(".")
                    .append(trace[stackIndex].methodName)
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].fileName)
                    .append(":")
                    .append(trace[stackIndex].lineNumber)
                    .append(")")
            level += "   "
            XposedBridge.log(builder.toString())
        }
        XposedBridge.log("---------logStackTraces end----------")
    }

    // 打印Class内的Fields by aaron 2018/12/12
    fun printObjectFields(msg: Any) {
        val fieldNames = msg::class.java.fields
        fieldNames.forEach {
            val field = it.get(msg)
            if (field is Array<*>) {
                val s = StringBuffer()
                field.forEach {
                    s.append(it.toString() + " , ")
                }
                XposedBridge.log("printObjectFields Array $it = $s, type:${it.type}")
            } else {
                XposedBridge.log("printObjectFields $it = $field ,type:${it.type}")
            }
        }
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LogUtil::class.java.name) {
                return i
            }
            i++
        }
        return -1
    }

    private fun getMethodName(): String {
        val trace = Thread.currentThread().stackTrace
        val level = ""
        val stackIndex = getStackOffset(trace)

        val builder = StringBuilder()
        return builder.append("|")
                .append(' ')
                .append(level)
                .append(trace[stackIndex].className)
                .append(".")
                .append(trace[stackIndex].methodName)
                .append(" ")
                .append(" (")
                .append(trace[stackIndex].fileName)
                .append(":")
                .append(trace[stackIndex].lineNumber)
                .append(")")
                .append(BR)
                .toString()
    }


}

