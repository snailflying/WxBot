package com.gh0u1l5.wechatmagician.spellbook.parser

/**
 * Dex 格式的文件头
 *
 * Refer: https://source.android.com/devices/tech/dalvik/dex-format
 */
@ExperimentalUnsignedTypes
class DexHeader {
    var version: Int = 0

    var checksum: Int = 0

    var signature: ByteArray = ByteArray(kSHA1DigestLen)

    var fileSize: Long = 0L

    var headerSize: Long = 0L

    var endianTag: Long = 0L

    var linkSize: Long = 0L

    var linkOff: Long = 0L

    var mapOff: Long = 0L

    var stringIdsSize: Int = 0

    var stringIdsOff: Long = 0L

    var typeIdsSize: Int = 0

    var typeIdsOff: Long = 0L

    var protoIdsSize: Int = 0

    var protoIdsOff: Long = 0L

    var fieldIdsSize: Int = 0

    var fieldIdsOff: Long = 0L

    var methodIdsSize: Int = 0

    var methodIdsOff: Long = 0L

    var classDefsSize: Int = 0

    var classDefsOff: Long = 0L

    var dataSize: Int = 0

    var dataOff: Long = 0L

    /**
     * @suppress
     */
    companion object {
        const val kSHA1DigestLen = 20
        const val kSHA1DigestOutputLen = kSHA1DigestLen * 2 + 1
    }
}