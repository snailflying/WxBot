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
import android.view.View
import android.widget.Toast
import com.chenenyu.router.annotation.Route
import com.gh0u1l5.wechatmagician.spellbook.util.Preferences
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wanzi.wechatrecord.db.DBHelper
import com.wanzi.wechatrecord.entry.ContactEntity
import com.wanzi.wechatrecord.util.ShellCommand
import io.merculet.core.base.App
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

    var settings: Preferences? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wx_info)
        ShellCommand.shellCommand("chmod 777 $packageCodePath") // 申请Root权限
        settings = Preferences.create(Config.PREFERENCE_NAME_SETTINGS)
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
        refreshLayout?.setOnRefreshListener { checkRoot() }
        refreshLayout?.setEnableLoadMore(false)
        refreshLayout?.autoRefresh()
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
            Observable.create<List<ContactEntity>> { emitter ->
                //                    DBHelper.readDb({
                val dbPwd = settings?.getString(Config.DB_PWD, "")
                val uinEnc = settings?.getString(Config.UIN_ENC, "")
                DBHelper.openWXDB(File(Config.COPY_FILE_PATH), dbPwd, uinEnc, App.instance, {
                    initData()
                    emitter.onNext(list)
                }, { it -> emitter.onError(it) })
            }.subscribeOn(Schedulers.io()) //发送事件在io线程
                    .observeOn(AndroidSchedulers.mainThread())//最后切换主线程提示结果
                    .subscribe({ helpAdapter.setData(it) }, {
                        refreshLayout?.finishRefresh()
                        toast(it.message.toString())
                    })
        }
    }

    val list = arrayListOf<ContactEntity>()
    private fun initData() {
        list.clear()
        refreshLayout?.finishRefresh()
        val chatRoomList = DBHelper.chatRoomList
        val contactList = DBHelper.contactList
        //好友
        list.add(ContactEntity("联系人列表", "", "0"))
        contactList.forEach { it -> if (it.username != "filehelper") list.add(ContactEntity(it.nickname, it.avatar)) }
        //群组
        list.add(ContactEntity("群组成员列表", "", "0"))
        chatRoomList.forEach { chatRoom ->
            run {
                list.add(ContactEntity("群名: " + chatRoom.name, "", "0"))
                chatRoom.displayname.split("、").forEachIndexed { index, s -> list.add(ContactEntity(s, chatRoom.avatarList[index])) }
            }
        }
    }
}

class HelpAdapter : BaseAdapter<ContactEntity>() {

    override fun getLayoutId(viewType: Int): Int = R.layout.cell_chatroom

    override fun onBindViewHolderImpl(holder: BaseViewHolder, position: Int, t: ContactEntity) {
        holder.itemView.iv_avatar.visibility = if (t.type == "0") View.GONE else View.VISIBLE
        holder.itemView.iv_avatar.loadCircle(t.avatar)
        holder.itemView.tv_nickname.text = t.nickname
    }
}
