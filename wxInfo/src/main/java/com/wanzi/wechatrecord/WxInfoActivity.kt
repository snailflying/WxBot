package com.wanzi.wechatrecord

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Toast
import com.chenenyu.router.annotation.Route
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wanzi.wechatrecord.util.LogUtils
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.config.Config
import io.merculet.wxinfo.R
import kotlinx.android.synthetic.main.activity_wx_info.*

@Route(value = [Config.ROUTER_ACTIVITY_WX_INFO])
class WxInfoActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wx_info)

        // 检查权限
        RxPermissions(this).request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECEIVE_BOOT_COMPLETED)
                .subscribe {
                    if (!it) Toast.makeText(this, "请打开相关权限",Toast.LENGTH_SHORT).show()
                }

        btn.setOnClickListener { checkRoot() }
    }

    private fun checkRoot() {
        try {
            LogUtils.i(this, "准备检测Root权限")
            // 检测是否拥有Root权限
            if (!ShellCommand.checkRoot(packageCodePath)) {
                LogUtils.i(this, "检测到未拥有Root权限")
                // 申请Root权限（弹出申请root权限框）
                ShellCommand.shellCommand("chmod 777 $packageCodePath")
            } else {
                startService(Intent(this, CoreService::class.java))
            }
        } catch (e: Exception) {
            Toast.makeText(this, "检查Root权限失败：${e.message}",Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * 返回键只返回桌面
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
