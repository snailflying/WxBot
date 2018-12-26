package com.wanzi.wechatrecord

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.wanzi.wechatrecord.db.DBHelper.openWXDB
import com.wanzi.wechatrecord.entry.UserInfo
import com.wanzi.wechatrecord.util.FileUtils
import com.wanzi.wechatrecord.util.MD5
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.config.Config.COPY_FILE_PATH
import io.merculet.core.config.Config.WX_DB_DIR_PATH
import io.merculet.core.config.Config.WX_DB_FILE_NAME
import io.merculet.core.config.Config.WX_ROOT_PATH
import io.merculet.core.config.Config.WX_SP_UIN_PATH
import io.merculet.core.utils.DeviceInfoUtils
import org.jsoup.Jsoup
import java.io.File

class CoreService : IntentService("CoreService") {

    private var uin = ""
    private var dbPwd = ""                        // 数据库密码
    private lateinit var userInfo: UserInfo       // 用户
    private var uinEnc = ""                       // 加密后的uin

    override fun onHandleIntent(intent: Intent?) {
        aa()
    }

    private fun aa() {
        // 获取数据库密码 数据库密码是IMEI和uin合并后计算MD5值取前7位
        // 获取imei
        val imei = DeviceInfoUtils.getDeviceId()
        // 修改微信根目录读写权限
        try {
            ShellCommand.shellCommand("chmod -R 777 $WX_ROOT_PATH")
            // 获取uin
            val doc = Jsoup.parse(File(WX_SP_UIN_PATH), "UTF-8")
            val elements = doc.select("int")
            elements.filter { it.attr("name") == "_auth_uin" }
                    .forEach { uin = it.attr("value") }
            if (uin.isEmpty()) {
                toast("当前没有登录微信，请登录后重试")
                return
            }
            // 获取数据库密码
            dbPwd = MD5.getMD5Str(imei + uin).substring(0, 7)
            FileUtils.writeLog(this, "数据库密码：$dbPwd\n")
        } catch (e: Exception) {
            log("破解数据库失败：${e.message}")
            FileUtils.writeLog(this, "破解数据库失败：${e.message}\n")
            toast("破解数据库失败：${e.message}")
        }

        // 获取当前微信登录用户的数据库文件父级文件夹名（MD5("mm"+uin) ）
        uinEnc = MD5.getMD5Str("mm$uin")
        log("当前微信用户数据库文件父级文件名：$uinEnc")
        // 递归查询微信本地数据库文件
        val dbDir = File(WX_DB_DIR_PATH + uinEnc)
        log("微信数据库文件目录：$dbDir")
        val list = FileUtils.searchFile(dbDir, WX_DB_FILE_NAME)
        for (file in list) {
            log("微信数据库文件路径：${file.absolutePath}")
            try {
                // 将微信数据库拷贝出来，因为直接连接微信的db，会导致微信崩溃
                FileUtils.copyFile(file.absolutePath, COPY_FILE_PATH)
                // 打开微信数据库
                openWXDB(File(COPY_FILE_PATH), dbPwd, uinEnc, this)
            } catch (e: Exception) {
                log("复制数据库失败：${e.message}")
                FileUtils.writeLog(this, "复制数据库失败：${e.message}\n")
                toast("复制数据库失败：${e.message}")
            }
        }
    }

    private fun IntentService.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(this, text, duration).show()
        }
    }

    private fun log(msg: String) {
        Log.i("CoreService: ", msg)
    }

}
