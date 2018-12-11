package io.merculet.wxbot.hook

import de.robv.android.xposed.XposedBridge

/**
 * Created by blanke on 2017/10/3.
 */

object LogUtil {
    fun log(msg: String) {
        XposedBridge.log("Bot:\t" + msg + "\tts=" + System.currentTimeMillis())
    }

    fun log(e: Throwable) {
        XposedBridge.log(e)
    }

    fun logStackTraces(methodCount: Int = 15, methodOffset: Int = 3) {
        val trace = Thread.currentThread().stackTrace
        var level = ""
        log("---------logStackTraces start----------")
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
            log(builder.toString())
        }
        log("---------logStackTraces end----------")
    }

    fun printMsgObj(msg: Any) {
        val fieldNames = msg::class.java.fields
        fieldNames.forEach {
            val field = it.get(msg)
            if (field is Array<*>) {
                val s = StringBuffer()
                field.forEach {
                    s.append(it.toString() + " , ")
                }
                log("aaron1 printMsgObj $it = $s")
            } else {
                log("aaron1 printMsgObj $it = $field ,${it.type}")
            }
        }
    }
}
