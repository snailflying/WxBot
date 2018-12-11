package com.gh0u1l5.wechatmagician.spellbook.util

import kotlin.experimental.and

object Unsigned {
    fun toLong(value: Int): Long {
        return value.toLong() and 0xffffffffL
    }

    fun toUInt(value: Long): Int {
        return value.toInt()
    }

    fun toInt(value: Short): Int {
        return value.toInt() and 0xffff
    }

    fun toUShort(value: Int): Short {
        return value.toShort()
    }

    fun ensureUInt(value: Long): Int {
        if (value < 0 || value > Integer.MAX_VALUE) {
            throw ArithmeticException("unsigned integer overflow")
        }
        return value.toInt()
    }


    fun ensureULong(value: Long): Long {
        if (value < 0) {
            throw ArithmeticException("unsigned long overflow")
        }
        return value
    }

    fun toShort(value: Byte): Short {
        return value.toShort() and 0xff
    }

    fun toUByte(value: Short): Byte {
        return value.toByte()
    }
}