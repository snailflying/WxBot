package io.merculet.wxbot

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun regexTest() {

        var contentStr: String? = """bravoon:
            |nihaohhhhhd你好
        """.trimMargin()
        if (contentStr?.matches(".*:\n.*".toRegex()) == true) {
            contentStr = ".*:\n(.*)".toRegex().find(contentStr)?.groups?.get(1)?.value
        }

        var contentStr1: String? = """bravoon:
            |@Xposed nihaohhhhhd你好
        """.trimMargin()
        if (contentStr1?.matches(".*:\n.*".toRegex()) == true) {
            contentStr1 = ".*:\n(.*)".toRegex().find(contentStr1)?.groups?.get(1)?.value
        }

        var contentStr2: String? = """bravoon111""".trimMargin()
        if (contentStr2?.matches(".*:\n.*".toRegex()) == true) {
            contentStr2 = ".*:\n(.*)".toRegex().find(contentStr2)?.groups?.get(1)?.value
        }
        assertEquals("nihaohhhhhd你好", contentStr)
        assertEquals("@Xposed nihaohhhhhd你好", contentStr1)
        assertEquals("bravoon111", contentStr2)
    }
}
