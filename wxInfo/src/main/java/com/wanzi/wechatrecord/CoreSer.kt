package com.wanzi.wechatrecord

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.wanzi.wechatrecord.db.DBHelper.openWXDB
import com.wanzi.wechatrecord.entry.UserInfo
import com.wanzi.wechatrecord.util.FileUtils
import com.wanzi.wechatrecord.util.MD5
import com.wanzi.wechatrecord.util.ShellCommand
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.io.File
import java.util.concurrent.TimeUnit

class CoreSer : Service() {

    private val WX_ROOT_PATH = "/data/data/com.tencent.mm/"                               // 微信根目录
    private val WX_SP_UIN_PATH = "${WX_ROOT_PATH}shared_prefs/auth_info_key_prefs.xml"    // 微信保存uin的目录
    private val WX_DB_DIR_PATH = "${WX_ROOT_PATH}MicroMsg/"                               // 微信保存聊天记录数据库的目录
    private val WX_DB_FILE_NAME = "EnMicroMsg.db"                                         // 微信聊天记录数据库

    private val WX_FILE_PATH = "/storage/emulated/0/Tencent/micromsg/"                    // 微信保存聊天时语音、图片、视频文件的地址

    private val currApkPath = "/data/data/com.dfsc.wechatrecord/"
    private val COPY_WX_DATA_DB = "wx_data.db"

    private var uin = ""
    private var uinEnc = ""                       // 加密后的uin
    private lateinit var userInfo: UserInfo       // 用户
    private lateinit var jobNumber: String        // 工号

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    override fun onCreate() {
        super.onCreate()
        log("onCreate")

        val imeiObservable = Observable
                // 开启轮询（每两分钟一次，第一次立即执行）
                .interval(0, 2 * 60, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                // 获取IMEI
                .map {
                    val manager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    return@map manager.deviceId
                }

        val uinObservable = Observable
                // 开启轮询
                .interval(0, 2 * 60, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                // 修改微信根目录读写权限
                .doOnNext {
                    ShellCommand.shellCommand("chmod -R 777 $WX_ROOT_PATH")
                }
                // 获取uin
                .map {
                    val doc = Jsoup.parse(File(WX_SP_UIN_PATH), "UTF-8")
                    val elements = doc.select("int")
                    elements
                            .filter { it.attr("name") == "_auth_uin" }
                            .forEach { uin = it.attr("value") }
                    if (uin.isEmpty()) {
                        throw NullPointerException("当前没有登录微信，请登录后重试")
                    }
                    return@map uin
                }

        Observable
                // 获取数据库密码 数据库密码是IMEI和uin合并后计算MD5值取前7位
                .zip(imeiObservable, uinObservable, BiFunction<String, String, String> { imei, uin ->
                    log("数据库密码:${MD5.getMD5Str(imei + uin).substring(0, 7)}")
                    MD5.getMD5Str(imei + uin).substring(0, 7)
                })
                // 获取当前微信登录用户的数据库文件父级文件夹名（MD5("mm"+uin) ）
                .doOnNext {
                    uinEnc = MD5.getMD5Str("mm$uin")
                }
                .observeOn(Schedulers.io())
                // 递归查询微信本地数据库文件
                .doOnNext {
                    val dbDir = File(WX_DB_DIR_PATH + uinEnc)
                    val list = FileUtils.searchFile(dbDir, WX_DB_FILE_NAME)
                    for (file in list) {
                        val copyFilePath = currApkPath + COPY_WX_DATA_DB
                        // 将微信数据库拷贝出来，因为直接连接微信的db，会导致微信崩溃
                        FileUtils.copyFile(file.absolutePath, copyFilePath)
                        // 打开微信数据库
                        openWXDB(File(copyFilePath), it, uinEnc, this)
                    }
                }
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        log("onSubscribe:${d.isDisposed}")
                    }

                    override fun onComplete() {
                        log("onComplete")
                    }

                    override fun onNext(t: String) {
                        log("onNext:$t，当前线程:${Thread.currentThread().name}")

                    }

                    override fun onError(e: Throwable) {
                        log("onError:${e.message}")
                    }

                })
    }


    private fun Service.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        val handler = Handler(Looper.getMainLooper())
        handler.post { Toast.makeText(this, text, duration).show() }
    }

    fun log(msg: String) {
        Log.i("CoreSer: ", "$msg，当前线程:${Thread.currentThread().name}")
    }
}
