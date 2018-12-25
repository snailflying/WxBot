package io.merculet.wxbot.db

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.wanzi.wechatrecord.entry.*
import com.wanzi.wechatrecord.util.*
import com.wanzi.wechatrecord.util.CipherUtil.decryptionWechatMd5
import com.wanzi.wechatrecord.util.CipherUtil.decryptionWechatSubString
import io.merculet.wxbot.base.App
import io.merculet.wxbot.config.Config.WX_FILE_PATH
import io.merculet.wxbot.domain.WechatBean
import io.merculet.wxbot.util.SPHelper
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteDatabaseHook
import org.litepal.crud.DataSupport
import java.io.File
import java.util.*


/**
 * @Description
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/24 4:44 PM
 * @Version
 */
object DBHelper {

    val WX_ROOT_PATH = "/data/data/com.tencent.mm/"
    val WX_SP_UIN_PATH = WX_ROOT_PATH + "shared_prefs/auth_info_key_prefs.xml"
    val WX_DB_DIR_PATH = WX_ROOT_PATH + "MicroMsg"
    val WX_DB_FILE_NAME = "EnMicroMsg.db"
    val mCurrApkPath = "/data/data/" + "com.wechatutils.chatrecord" + "/"
    private val COPY_WX_DATA_DB = "wx_data.db"
    val copyFilePath = mCurrApkPath + COPY_WX_DATA_DB
    private val TAG = "DBHelper"
    private val DB_FILE = ""
    private var uinEnc = ""                       // 加密后的uin

    /**
     * 连接数据库
     *
     * @param
     */
    fun openWxDb(context: Context, mDbPassword: String): List<WechatBean> {
        val weChatDataList = ArrayList<WechatBean>()
        val copyWxDataDb = File(copyFilePath)
        SQLiteDatabase.loadLibs(context)
        val hook = object : SQLiteDatabaseHook {
            override fun preKey(sqLiteDatabase: net.sqlcipher.database.SQLiteDatabase) {

            }

            override fun postKey(sqLiteDatabase: net.sqlcipher.database.SQLiteDatabase) {
                sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;") //兼容2.0的数据库
            }

        }

        try {
            //打开数据库连接
            // SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(copyWxDataDb, mDbPassword, null, hook);
            val db = SQLiteDatabase.openDatabase(copyWxDataDb.absolutePath, mDbPassword, null, 1, hook)
            //查询所有联系人（verifyFlag!=0:公众号等类型，群里面非好友的类型为4，未知类型2）
            val c1 = db.rawQuery("select * from rcontact where verifyFlag = 0 and type != 4 and type != 2 and nickname != '' limit 20, 9999", null)
            val defile = SPHelper.create(context).getString(DB_FILE, "").replace("/storage/emulated/0/tencent/MicroMsg/", "")
            while (c1.moveToNext()) {
                val wechatBean = WechatBean()
                val userName = c1.getString(c1.getColumnIndex("username"))
                var alias = c1.getString(c1.getColumnIndex("alias"))
                val nickName = c1.getString(c1.getColumnIndex("nickname"))
                wechatBean.username = userName
                //微信用户头像解密
                val wechatUserAvatarImage = decryptionWechatUserAvatarImage(userName, defile)
                if (TextUtils.isEmpty(alias)) {
                    alias = userName
                }
                wechatBean.avatarImage = wechatUserAvatarImage
                wechatBean.alias = alias
                wechatBean.username = userName
                wechatBean.nickname = nickName
                weChatDataList.add(wechatBean)
            }
            c1.close()
            db.close()
        } catch (e: Exception) {
            Log.d(TAG, "读取数据库信息失败" + e.toString())
            e.printStackTrace()
        }

        Log.d(TAG, "openWxDb: ========" + weChatDataList.size)
        return weChatDataList
    }

    fun decryptionWechatUserAvatarImage(userName: String, defile: String): String {
        //根据微信的 WechatBean的userName然后使用md5加密，成字符串，再截取前面两个字段的文件目录
        val decryptionWechatMd5 = decryptionWechatMd5(userName.toByteArray())
        //decryptionWechatMd5  5f39b18498a4107de947dc9b1e5d29b2
        val decryptionWechatSubString = decryptionWechatSubString(decryptionWechatMd5)
        //decryptionWechatSubString  5f/39/
        // /data/user/0/com.tencent.mm/MicroMsg/1306e8eb3f168108d6f138fd6dbc511e/avatar/5f/39/user_5f39b18498a4107de947dc9b1e5d29b2.png
        //这个就是当前用户的头像地址
        return "/data/user/0/com.tencent.mm/MicroMsg/" + defile + "/avatar/" + decryptionWechatSubString + "user_" + decryptionWechatMd5 + ".png"
    }

    /**
     * 微信数据库操作
     */
    private fun openWXDB(file: File, password: String) {
        toast("正在打开微信数据库，请稍候...")
        SQLiteDatabase.loadLibs(App.instance)
        val hook = object : SQLiteDatabaseHook {
            override fun preKey(database: SQLiteDatabase) {}

            override fun postKey(database: SQLiteDatabase) {
                database.rawExecSQL("PRAGMA cipher_migrate;") // 兼容2.0的数据库
            }
        }
        try {
            // 打开数据库连接
            val db = SQLiteDatabase.openOrCreateDatabase(file, password, null, hook)
            openUserInfoTable(db)
            openContactTable(db)
            openMessageTable(db)
            openChatRoomTable(db)
            db.close()
        } catch (e: Exception) {
            log("打开数据库失败：${e.message}")
            FileUtils.writeLog(App.instance, "打开数据库失败：${e.message}\n")
            toast("打开数据库失败：${e.message}")
        }
    }

    // 打开用户信息表
    private fun openUserInfoTable(db: SQLiteDatabase) {
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
        FileUtils.writeLog(App.instance, "用户信息：$userInfo\n")
        // 切换数据库
        DBUtils.switchDBUser(userInfo.username)
    }

    // 打开联系人表
    private fun openContactTable(db: SQLiteDatabase) {
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
                // 避免保存重复数据
                val list = DataSupport.where("username = ?", username).find(Contact::class.java)
                if (list.isEmpty()) {
                    val contact = Contact()
                    contact.username = username
                    contact.nickname = nickname
                    contact.type = type
                    contact.conRemark = conRemark
                    contact.save()
                }
                log("微信联系人列表:  $nickname")
            }
        }
        cursor.close()
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
                    log("聊天信息：$message")
                    message.save()
                }
            }
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

    // 打开微信群表
    private fun openChatRoomTable(db: SQLiteDatabase) {
        val cursor = db.rawQuery("select * from chatroom ", arrayOf())
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex("chatroomname"))
                val memberList = cursor.getString(cursor.getColumnIndex("memberlist"))
                val roomOwner = cursor.getString(cursor.getColumnIndex("roomowner"))
                var selfDisplayName = cursor.getString(cursor.getColumnIndex("selfDisplayName"))
                val modifyTime = cursor.getLong(cursor.getColumnIndex("modifytime"))
                if (selfDisplayName == null) {
                    selfDisplayName = ""
                }
                val list = DataSupport.where("name = ?", name).find(ChatRoom::class.java)
                if (list.isEmpty()) {
                    // 新建群信息
                    val chatRoom = ChatRoom()
                    chatRoom.name = name
                    chatRoom.memberList = memberList
                    chatRoom.roomOwner = roomOwner
                    chatRoom.selfDisplayName = selfDisplayName
                    chatRoom.modifyTime = modifyTime
                    chatRoom.save()
                } else {
                    // 修改群信息
                    val first = list[0]
                    if (first.modifyTime != modifyTime) {
                        first.memberList = memberList
                        first.roomOwner = roomOwner
                        first.selfDisplayName = selfDisplayName
                        first.modifyTime = modifyTime
                        first.isModify = 0
                        first.save()
                    }
                }
            }
        }
        cursor.close()
    }

    private fun toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(App.instance, text, duration).show()
        }
    }

    private fun log(msg: String) {
        LogUtils.i(App.instance, msg)
    }
}
