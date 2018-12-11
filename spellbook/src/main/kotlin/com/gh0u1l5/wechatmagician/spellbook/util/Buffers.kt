package com.gh0u1l5.wechatmagician.spellbook.util

import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.experimental.and

object Buffers {

    /**
     * get one unsigned byte as short type
     */
    fun readUByte(buffer: ByteBuffer): Short {
        val b = buffer.get()
        return b.toShort() and 0xff
    }

    /**
     * get one unsigned short as int type
     */
    fun readUShort(buffer: ByteBuffer): Int {
        val s = buffer.short
        return s.toInt() and 0xffff
    }

    /**
     * get one unsigned int as long type
     */
    fun readUInt(buffer: ByteBuffer): Long {
        val i = buffer.int
        return i.toLong() and 0xffffffffL
    }

    /**
     * get bytes
     */
    @JvmOverloads
    fun readBytes(buffer: ByteBuffer, size: Int = buffer.remaining()): ByteArray {
        val bytes = ByteArray(size)
        buffer.get(bytes)
        return bytes
    }

    /**
     * Read ascii string ,by len
     */
    fun readAsciiString(buffer: ByteBuffer, strLen: Int): String {
        val bytes = ByteArray(strLen)
        buffer.get(bytes)
        return String(bytes)
    }

    /**
     * read utf16 strings, use strLen, not ending 0 char.
     */
    fun readString(buffer: ByteBuffer, strLen: Int): String {
        val sb = StringBuilder(strLen)
        for (i in 0 until strLen) {
            sb.append(buffer.char)
        }
        return sb.toString()
    }

    /**
     * read utf16 strings, ending with 0 char.
     */
    fun readZeroTerminatedString(buffer: ByteBuffer, strLen: Int): String {
        val sb = StringBuilder(strLen)
        for (i in 0 until strLen) {
            val c = buffer.char
            if (c == '\u0000') {
                skip(buffer, (strLen - i - 1) * 2)
                break
            }
            sb.append(c)
        }
        return sb.toString()
    }

    /**
     * skip count bytes
     */
    fun skip(buffer: ByteBuffer, count: Int) {
        position(buffer, buffer.position() + count)
    }

    // Cast java.nio.ByteBuffer instances where necessary to java.nio.Buffer to avoid NoSuchMethodError
    // when running on Java 6 to Java 8.
    // The Java 9 ByteBuffer classes introduces overloaded methods with covariant return types the following methods:
    // position, limit, flip, clear, mark, reset, rewind, etc.


    /**
     * set position
     */
    fun position(buffer: ByteBuffer, position: Int) {
        (buffer as Buffer).position(position)
    }

    /**
     * set position
     */
    fun position(buffer: ByteBuffer, position: Long) {
        position(buffer, Unsigned.ensureUInt(position))
    }


    /**
     * Return one new ByteBuffer from current position, with size, the byte order of new buffer will be set to little endian;
     * And advance the original buffer with size.
     */
    fun sliceAndSkip(buffer: ByteBuffer, size: Int): ByteBuffer {
        val buf = buffer.slice().order(ByteOrder.LITTLE_ENDIAN)
        val slice = (buf as Buffer).limit(buf.position() + size) as ByteBuffer
        skip(buffer, size)
        return slice
    }
}
/**
 * get all bytes remains
 */