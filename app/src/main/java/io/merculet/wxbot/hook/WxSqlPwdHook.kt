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

    private val TAG = "wxinfo"

    override fun provideStaticHookers(): List<Hooker>? = listOf(decryptImplHook())

    private fun decryptImplHook(): Hooker = Hooker {
        XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", WechatGlobal.wxLoader, "openDatabase", String::class.java,
                ByteArray::class.java, WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.database.SQLiteCipherSpec"),
                WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.database.SQLiteDatabase\$CursorFactory"), Int::class.javaPrimitiveType,
                WechatGlobal.wxLoader?.loadClass("com.tencent.wcdb.DatabaseErrorHandler"), Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        val path = param.args[0].toString()
                        Log.i(TAG, "Path: $path")    ///data/data/com.tencent.mm/MicroMsg/4b1264d9e181eb33ffc8f0af354757fc/EnMicroMsg.db
                        val uinEnc = path.split("/")[5]
                        Log.i(TAG, "uinEnc: $uinEnc")    //加密后的uin:4b1264d9e181eb33ffc8f0af354757fc
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

                        openDB(path, String(param.args[1] as ByteArray), uinEnc)
                    }
                })

    }

    /**
     * path: 数据库路径 /data/data/com.tencent.mm/MicroMsg/4b1264d9e181eb33ffc8f0af354757fc/***/EnMicroMsg.db
     * EnMicroMsg.db
     * CommonOneMicroMsg.db
     * EnResDown.db
     * enFavorite.db
     * WxExpt.db
     * WxFileIndex.db
     * SnsMicroMsg.db
     * AppBrandComm.db
     * FTS5IndexMicroMsg.db
     *
     * pwd: 数据库密码 - 0609f84
     * uinEnc: 加密后的uin - 4b1264d9e181eb33ffc8f0af354757fc
     *
     * 我们千万不可以直接通过net.sqlcipher.database.SQLiteDatabase这个类来连接我们上一步里面查找到的微信目录下的EnMicroMsg.db文件，可能是因为一个数据库文件不能被多次连接的情况，
     * 只要我们一成功连接上那个db文件，微信的客户端就会自动退出登录，并且会出现异常。所有我现在的做法是把这个db文件拷贝到我们自己的app目录下，再进行连接。
     */
    private fun openDB(path: String, pwd: String, uinEnc: String) {
        if (path.contains("EnMicroMsg")) {
            Log.e("wxinfo", "path: $path" + "pwd: $pwd" + "uinEnc: $uinEnc")
//            FileUtils.copyFile(path, COPY_FILE_PATH)
//            settings?.edit()
//                    ?.putString(Config.DB_PWD, pwd)
//                    ?.putString(Config.UIN_ENC, uinEnc)
//                    ?.apply()
        }
    }
}
