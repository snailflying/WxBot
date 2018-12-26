package com.wanzi.wechatrecord.db

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.wanzi.wechatrecord.entry.*
import com.wanzi.wechatrecord.util.CipherUtil.decryptionWechatMd5
import com.wanzi.wechatrecord.util.CipherUtil.decryptionWechatSubString
import com.wanzi.wechatrecord.util.FileUtils
import com.wanzi.wechatrecord.util.MD5
import com.wanzi.wechatrecord.util.TimeUtils
import io.merculet.core.base.App
import io.merculet.core.config.Config
import io.merculet.core.config.Config.WX_FILE_PATH
import io.merculet.core.ext.toast
import io.merculet.core.utils.DeviceInfoUtils
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabaseHook
import org.jsoup.Jsoup
import org.litepal.LitePal
import org.litepal.LitePalDB
import org.litepal.crud.DataSupport
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


/**
 * @Description
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/24 4:44 PM
 * @Version
 */
object DBHelper {

    var uinEnc = ""                       // 加密后的uin
    var dbPwd = ""                        // 数据库密码
    var uin = ""
    var success: (() -> Unit?)? = null
    var fail: ((e: Exception) -> Unit?)? = null

    @SuppressLint("MissingPermission")
    fun readDb(success: () -> Unit, fail: (e: Exception) -> Unit) {
        this.success = success
        this.fail = fail
        // 获取数据库密码 数据库密码是IMEI和uin合并后计算MD5值取前7位
        val imei = DeviceInfoUtils.getDeviceId()    // 获取imei
        // 修改微信根目录读写权限
        try {
            // 获取uin
            val doc = Jsoup.parse(File(Config.WX_SP_UIN_PATH), "UTF-8")
            val elements = doc.select("int")
            elements.filter { it.attr("name") == "_auth_uin" }
                    .forEach { uin = it.attr("value") }
            if (uin.isEmpty()) {
                toast("当前没有登录微信，请登录后重试")
                return
            }
            // 获取数据库密码
            dbPwd = MD5.getMD5Str(imei + uin).substring(0, 7)
        } catch (e: Exception) {
            log("破解数据库失败：${e.message}")
            fail.invoke(e)
        }

        // 获取当前微信登录用户的数据库文件父级文件夹名（MD5("mm"+uin) ）
        uinEnc = MD5.getMD5Str("mm$uin")
        log("当前微信用户数据库文件父级文件名：$uinEnc")
        // 递归查询微信本地数据库文件
        val dbDir = File(Config.WX_DB_DIR_PATH + uinEnc)
        log("微信数据库文件目录：$dbDir")
        val list = FileUtils.searchFile(dbDir, Config.WX_DB_FILE_NAME)
        for (file in list) {
            log("微信数据库文件路径：${file.absolutePath}")
            try {
                // 将微信数据库拷贝出来，因为直接连接微信的db，会导致微信崩溃
                FileUtils.copyFile(file.absolutePath, Config.COPY_FILE_PATH)
                // 打开微信数据库
                DBHelper.openWXDB(File(Config.COPY_FILE_PATH), dbPwd, uinEnc, App.instance)
            } catch (e: Exception) {
                log("复制数据库失败：${e.message}")
                fail.invoke(e)
            }
        }
    }

    /**
     * 微信数据库操作
     */
    fun openWXDB(file: File, password: String?, uinEnc: String?, context: Context, success: (() -> Unit?)? = null, fail: ((e: Exception) -> Unit?)? = null) {
        this.success = success
        this.fail = fail
        log("数据库路径和密码：$file --- $password")
        this.uinEnc = uinEnc.toString()
        // 获取当前微信登录用户的数据库文件父级文件夹名（MD5("mm"+uin) ）
        toast("正在打开微信数据库，请稍候...")
        SQLiteDatabase.loadLibs(context)
        val hook = object : SQLiteDatabaseHook {
            override fun preKey(database: SQLiteDatabase) {}

            override fun postKey(database: SQLiteDatabase) {
                database.rawExecSQL("PRAGMA cipher_migrate;") // 兼容2.0的数据库
            }
        }
        try {
            // 打开数据库连接
            val db = SQLiteDatabase.openOrCreateDatabase(file, password, null, hook)
            openUserInfoTable(db, context)
            openContactTable(db)
            openMessageTable(db)
            openChatRoomTable(db)
            openAllContactTable(db)
            success?.invoke()
            db.close()
        } catch (e: Exception) {
            log("打开数据库失败：${e.message}")
            fail?.invoke(e)
        }
    }

    /**
     * 查询所有联系人
     */
    private fun openAllContactTable(db: SQLiteDatabase) {
        val weChatDataList = ArrayList<WechatBean>()
        //查询所有联系人（verifyFlag!=0:公众号等类型，群里面非好友的类型为4，未知类型2）
        val cursor = db.rawQuery("select * from rcontact where verifyFlag = 0 and type != 4 and type != 2 and nickname != '' limit 20, 9999", null)
        while (cursor.moveToNext()) {
            val wechatBean = WechatBean()
            val userName = cursor.getString(cursor.getColumnIndex("username"))
            var alias = cursor.getString(cursor.getColumnIndex("alias"))
            val nickName = cursor.getString(cursor.getColumnIndex("nickname"))
            wechatBean.username = userName
            //微信用户头像解密
            val wechatUserAvatarImage = decryptionWechatUserAvatarImage(userName, uinEnc)
            if (TextUtils.isEmpty(alias)) {
                alias = userName
            }
            wechatBean.avatarImage = wechatUserAvatarImage
            wechatBean.alias = alias
            wechatBean.username = userName
            wechatBean.nickname = nickName
            weChatDataList.add(wechatBean)
        }
        log(("查询所有联系人 :" + Gson().toJson(weChatDataList)))
        cursor.close()
    }

    private fun decryptionWechatUserAvatarImage(userName: String, defile: String): String {
        //根据微信的 WechatBean的userName然后使用md5加密，成字符串，再截取前面两个字段的文件目录
        val decryptionWechatMd5 = decryptionWechatMd5(userName.toByteArray())
        //decryptionWechatMd5  5f39b18498a4107de947dc9b1e5d29b2
        val decryptionWechatSubString = decryptionWechatSubString(decryptionWechatMd5)
        //decryptionWechatSubString  5f/39/
        // /data/user/0/com.tencent.mm/MicroMsg/1306e8eb3f168108d6f138fd6dbc511e/avatar/5f/39/user_5f39b18498a4107de947dc9b1e5d29b2.png
        //这个就是当前用户的头像地址
        return "/data/user/0/com.tencent.mm/MicroMsg/" + defile + "/avatar/" + decryptionWechatSubString + "user_" + decryptionWechatMd5 + ".png"
    }

    // 打开用户信息表
    private fun openUserInfoTable(db: SQLiteDatabase, context: Context) {
        // 这个数组是保存用户信息，第一次拿到的是账号，第二次是昵称
        val values = ArrayList<String>()
        // 用户信息表
        val cursor = db.rawQuery("select value from userinfo where id = ? or id = ?", arrayOf("2", "4"))
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val value = cursor.getString(cursor.getColumnIndex("value"))
                values.add(value)
            }
        }
        cursor.close()
        // 用户信息
        val userInfo = UserInfo(values[0], values[1])
        log("用户信息：$userInfo")
        // 切换数据库
        switchDBUser(userInfo.username)
    }

    /**
     * 切换数据库
     */
    private fun switchDBUser(dbName: String) {
        val db = LitePalDB.fromDefault(dbName)
        LitePal.use(db)
    }

    val contactList = arrayListOf<Contact>()
    // 打开联系人表
    private fun openContactTable(db: SQLiteDatabase) {
        contactList.clear()
        // verifyFlag!=0：公众号等类型 type=33：微信功能 type=2：未知 type=4：非好友
        // 一般公众号原始ID开头都是gh_
        // 群ID的结尾是@chatroom
        val cursor = db.rawQuery("select * from rcontact where " +
                "type != ? and " +
                "type != ? and " +
                "type != ? and " +
                "verifyFlag = ? and " +
                "username not like 'gh_%' and " +
                "username not like '%@chatroom' ", arrayOf("2", "33", "4", "0"))
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val username = cursor.getString(cursor.getColumnIndex("username"))
                val nickname = cursor.getString(cursor.getColumnIndex("nickname"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val conRemark = cursor.getString(cursor.getColumnIndex("conRemark"))
//                val list = DataSupport.where("username = ?", username).find(Contact::class.java)  // 避免保存重复数据
                val contact = Contact()
                contact.username = username
                contact.nickname = nickname
                contact.type = type
                contact.conRemark = conRemark
                contact.avatar = decryptionWechatUserAvatarImage(username, uinEnc)
                contactList.add(contact)
            }
            log("微信联系人列表:  " + Gson().toJson(contactList))
        }
        cursor.close()
    }

    // 获取最后一条消息ID
    private fun getLastMsgId(db: SQLiteDatabase): String {
        // 查询本地数据库中的最后一条
        var lastMsgId = "0"
        val last = DataSupport.findLast(Message::class.java)
        if (last != null) {
            log("本地数据库中存在最后一条记录，msgSvrid：${last.msgSvrId}")
            val msgCu = db.rawQuery(" select * from message where msgsvrid = ? ", arrayOf(last.msgSvrId))
            if (msgCu.count > 0) {
                while (msgCu.moveToNext()) {
                    lastMsgId = msgCu.getString(msgCu.getColumnIndex("msgId"))
                    log("微信数据库中存在 msgSvrid 为：${last.msgSvrId} 的记录，msgid 为：$lastMsgId")
                }
            }
            msgCu.close()
        }
        log("聊天记录从 msgid 为：$lastMsgId 处开始查询")
        return lastMsgId
    }

    // 打开聊天记录表
    private fun openMessageTable(db: SQLiteDatabase) {
        // 一般公众号原始ID开头都是gh_
        val cursor = db.rawQuery("select * from message where talker not like 'gh_%' and msgid > ? ", arrayOf(getLastMsgId(db)))
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val msgSvrId = cursor.getString(cursor.getColumnIndex("msgSvrId"))
                val type = cursor.getString(cursor.getColumnIndex("type"))
                val isSend = cursor.getString(cursor.getColumnIndex("isSend"))
                val createTime = cursor.getLong(cursor.getColumnIndex("createTime"))
                val talker = cursor.getString(cursor.getColumnIndex("talker"))
                var content = cursor.getString(cursor.getColumnIndex("content"))
                if (content == null) content = ""
                var imgPath = cursor.getString(cursor.getColumnIndex("imgPath"))
                if (imgPath == null) imgPath = ""
                // 根据“msgSvrId”来判断聊天记录唯一性
                if (msgSvrId == null) {
                    log("该次记录 msgSvrId 为空，跳过")
                    continue
                }
                val list = DataSupport.where("msgSvrId = ?", msgSvrId).find(Message::class.java)
                if (list.isEmpty()) {
                    val message = Message()
                    message.msgSvrId = msgSvrId
                    message.type = type
                    // 内容不做处理，直接上传
                    message.content = content
                    /*message.content = when (message.type) {
                        "1" -> content
                        "3" -> "[图片]"
                        "34" -> "[语音]"
                        "47" -> "[表情]"
                        "50" -> "[语音/视频通话]"
                        "43" -> "[小视频]"
                        "49" -> "[分享]"
                        "48" -> content          // 位置信息
                        "10000" -> content       // 系统提示信息
                        else -> content          // 其他信息，包含红包、转账等
                    }*/
                    message.isSend = isSend
                    message.createTime = TimeUtils.timeFormat(createTime, TimeUtils.TIME_STYLE)
                    message.talker = talker
                    // 保存图片、语音、小视频文件信息
                    if (type == "3" || type == "34" || type == "43") {
                        val weChatFile = WeChatFile()
                        weChatFile.msgSvrId = msgSvrId
                        weChatFile.type = type
                        weChatFile.date = Date().time
                        when (type) {
                            "3" -> {
                                // 图片文件需要根据msgSvrId在ImgInfo2表中查找
                                val imgInfoCu = db.rawQuery("select bigImgPath from ImgInfo2 where msgSvrId = ? ", arrayOf(msgSvrId))
                                if (imgInfoCu.count > 0) {
                                    while (imgInfoCu.moveToNext()) {
                                        val bigImgPath = imgInfoCu.getString(imgInfoCu.getColumnIndex("bigImgPath"))
                                        weChatFile.name = bigImgPath
                                    }
                                }
                                imgInfoCu.close()
                                weChatFile.path = WX_FILE_PATH + uinEnc + "/image2/" + weChatFile.name.substring(0, 2) + "/" + weChatFile.name.substring(2, 4) + "/" + weChatFile.name
                                // 接收的图片在ImgInfo2表中会有两种bigImgPath，一种是原图，一种是缓存图，缓存图的格式是文件.temp.jpg
                                // 如果有原图，则上传原图，否则上传缓存图
                                if (weChatFile.path.contains(".temp")) {
                                    val originalImgPath = weChatFile.path.replace(".temp", "")
                                    if (File(originalImgPath).exists()) {
                                        weChatFile.name = weChatFile.name.replace(".temp", "")
                                        weChatFile.path = originalImgPath
                                    }
                                }
                                // 过滤一些不是jpg的文件
                                if (weChatFile.name.endsWith(".jpg")) {
                                    message.imgPath = weChatFile.name
                                    weChatFile.save()
                                }
                            }
                            "34" -> {
                                weChatFile.name = "msg_$imgPath.amr"
                                val nameEnc = MD5.getMD5Str(imgPath)
                                weChatFile.path = WX_FILE_PATH + uinEnc + "/voice2/" + nameEnc.substring(0, 2) + "/" + nameEnc.substring(2, 4) + "/" + weChatFile.name
                                message.imgPath = weChatFile.name
                                weChatFile.save()
                            }
                            "43" -> {
                                weChatFile.name = "$imgPath.mp4"
                                weChatFile.path = WX_FILE_PATH + uinEnc + "/video/" + weChatFile.name
                                message.imgPath = weChatFile.name
                                weChatFile.save()
                            }
                        }
                    }
                    log("聊天信息：" + Gson().toJson(message))
                    message.save()
                }
            }
        }
        cursor.close()
    }

    val chatRoomList = arrayListOf<ChatRoom>()
    // 打开微信群表
    private fun openChatRoomTable(db: SQLiteDatabase) {
        chatRoomList.clear()
        val cursor = db.rawQuery("select * from chatroom ", arrayOf())
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex("chatroomname"))
                val memberList = cursor.getString(cursor.getColumnIndex("memberlist"))
                val displayname = cursor.getString(cursor.getColumnIndex("displayname"))
                val roomOwner = cursor.getString(cursor.getColumnIndex("roomowner"))
                val selfDisplayName = cursor.getString(cursor.getColumnIndex("selfDisplayName"))
                        ?: ""
                val modifyTime = cursor.getLong(cursor.getColumnIndex("modifytime"))
//                val list = DataSupport.where("name = ?", name).find(ChatRoom::class.java)
                val chatRoom = ChatRoom()
                memberList.split(";").forEach {
                    chatRoom.avatarList.add(decryptionWechatUserAvatarImage(it, uinEnc))
                }
                chatRoom.name = name
                chatRoom.memberList = memberList
                chatRoom.displayname = displayname
                chatRoom.roomOwner = roomOwner
                chatRoom.selfDisplayName = selfDisplayName
                chatRoom.modifyTime = modifyTime
                chatRoomList.add(chatRoom)
            }
            log("微信群信息 :" + Gson().toJson(chatRoomList))
        }
        cursor.close()
    }

    fun log(msg: String) {
        Log.i("wxinfo: ", msg)
    }

}
