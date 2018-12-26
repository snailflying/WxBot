package com.wanzi.wechatrecord

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.chenenyu.router.annotation.Route
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wanzi.wechatrecord.db.DBHelper
import com.wanzi.wechatrecord.entry.ContactEntity
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.base.BaseAdapter
import io.merculet.core.config.Config
import io.merculet.core.ext.loadCircle
import io.merculet.wxinfo.R
import kotlinx.android.synthetic.main.activity_wx_info.*
import kotlinx.android.synthetic.main.cell_chatroom.view.*

@Route(value = [Config.ROUTER_ACTIVITY_WX_INFO])
class WxInfoActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
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
    }

    private val helpAdapter = HelpAdapter()

    private fun initView() {
        refreshLayout?.setEnableLoadMore(false)
        refreshLayout?.autoRefresh()
        refreshLayout?.setOnRefreshListener { checkRoot() }
        rv_contact.layoutManager = LinearLayoutManager(this, OrientationHelper.VERTICAL, false)
        rv_contact.adapter = helpAdapter
    }

    @SuppressLint("CheckResult")
    private fun checkRoot() {
        // 检测是否拥有Root权限
        if (!ShellCommand.checkRoot(packageCodePath)) {
            Log.i("wxinfo", "检测到未拥有Root权限")
            ShellCommand.shellCommand("chmod 777 $packageCodePath") // 申请Root权限
        } else {
            DBHelper.readDb {
                refreshLayout?.finishRefresh()
                val list = arrayListOf<ContactEntity>()
                val chatRoomList = DBHelper.chatRoomList
                val contactList = DBHelper.contactList
                //好友
                list.add(ContactEntity("联系人列表", "", "0"))
                contactList.forEach { it -> list.add(ContactEntity(it.nickname)) }

                //群组
                list.add(ContactEntity("群组成员列表", "", "0"))
                chatRoomList.forEach { it ->
                    run {
                        list.add(ContactEntity("群名: " + it.name, "", "0"))
                        it.displayname.split("、").forEach { it -> list.add(ContactEntity(it)) }
                    }
                }
                helpAdapter.setData(list)
            }
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

class HelpAdapter : BaseAdapter<ContactEntity>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.cell_chatroom

    override fun onBindViewHolderImpl(holder: BaseViewHolder, position: Int, t: ContactEntity) {
        holder.itemView.iv_avatar.visibility = if (t.type == "0") View.GONE else View.VISIBLE
        holder.itemView.iv_avatar.loadCircle(R.drawable.shape_default_circle_bg)
        holder.itemView.tv_nickname.text = t.nickname
    }
}
