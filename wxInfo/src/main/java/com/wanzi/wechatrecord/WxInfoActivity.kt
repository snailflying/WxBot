package com.wanzi.wechatrecord

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.chenenyu.router.annotation.Route
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wanzi.wechatrecord.db.DBHelper
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.base.BaseAdapter
import io.merculet.core.config.Config
import io.merculet.core.ext.loadCircle
import io.merculet.core.ext.toast
import io.merculet.wxinfo.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_wx_info.*
import kotlinx.android.synthetic.main.cell_chatroom.view.*
import java.io.File

@Route(value = [Config.ROUTER_ACTIVITY_WX_INFO])
class WxInfoActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wx_info)
        ShellCommand.shellCommand("chmod 777 $packageCodePath") // 申请Root权限
        initView()
        // 检查权限
        RxPermissions(this).request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECEIVE_BOOT_COMPLETED)
                .subscribe {
                    if (!it) {
                        Toast.makeText(this, "请打开相关权限", Toast.LENGTH_SHORT).show()
                    }
                }
        btn.setOnClickListener { checkRoot() }
    }

    private fun initView() {
        rv_wx.layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
    }

    @SuppressLint("CheckResult")
    private fun checkRoot() {
        // 检测是否拥有Root权限
        if (!ShellCommand.checkRoot(packageCodePath)) {
            Log.i("wxinfo", "检测到未拥有Root权限")
            ShellCommand.shellCommand("chmod 777 $packageCodePath") // 申请Root权限
        } else {
            DBHelper.readDb()
            Observable.create<File> { emitter ->
            }.subscribeOn(Schedulers.io()) //发送事件在io线程
                    .observeOn(AndroidSchedulers.mainThread())//最后切换主线程提示结果
                    .subscribe({ toast("图片保存成功").show() }, { toast("保存失败").show() })
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

class HelpAdapter : BaseAdapter<String>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.cell_chatroom

    override fun onBindViewHolderImpl(holder: BaseViewHolder, position: Int, t: String) {
        holder.itemView.iv_avatar.loadCircle("")
        holder.itemView.tv_nickname.text = t
    }
}
