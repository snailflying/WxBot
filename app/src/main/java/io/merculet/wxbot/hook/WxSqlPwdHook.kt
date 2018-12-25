package io.merculet.wxbot.hook

import android.util.Log
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal
import com.gh0u1l5.wechatmagician.spellbook.base.Hooker
import com.gh0u1l5.wechatmagician.spellbook.base.HookerProvider
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.nio.charset.Charset
import java.util.*

/**
 * @Description 微信openDatabase方法提取解密参数
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/25 10:43 AM
 * @Version 1.0.0
 */
object WxSqlPwdHook : HookerProvider {

    private const val TAG = "wxSqlPwdHook"

    override fun provideStaticHookers(): List<Hooker>? = listOf(decryptImplHook())

    private fun decryptImplHook(): Hooker = Hooker {
        XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", WechatGlobal.wxLoader, "openDatabase", String::class.java,
                ByteArray::class.java, WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.database.SQLiteCipherSpec"),
                WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.database.SQLiteDatabase\$CursorFactory"), Int::class.javaPrimitiveType,
                WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.DatabaseErrorHandler"), Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                        Log.i(TAG, "Path: " + param!!.args[0])
                        Log.i(TAG, "Password: " + String(param.args[1] as ByteArray, Charset.forName("UTF-8")))
                        val formatter = Formatter()
                        for (b in param.args[1] as ByteArray) {
                            formatter.format("%02x", b)
                        }
                        Log.i(TAG, "Password (hex): 0x" + formatter.toString())
                        Log.i(TAG, "CipherSpec - Cipher: " + XposedHelpers.getObjectField(param.args[2], "cipher"))
                        Log.i(TAG, "CipherSpec - KdfIteration: " + XposedHelpers.getIntField(param.args[2], "kdfIteration"))
                        Log.i(TAG, "CipherSpec - Hmac Enabled: " + XposedHelpers.getBooleanField(param.args[2], "hmacEnabled"))
                        Log.i(TAG, "CipherSpec - Page Size: " + XposedHelpers.getIntField(param.args[2], "pageSize"))
                        Log.i(TAG, "Flags: " + param.args[4])
                        Log.i(TAG, "PoolSize: " + param.args[6])
                    }
                })

    }
}
