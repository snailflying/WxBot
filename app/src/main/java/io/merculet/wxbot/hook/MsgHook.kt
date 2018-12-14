package io.merculet.wxbot.hook

import android.content.ContentValues
import com.gh0u1l5.wechatmagician.spellbook.base.Operation
import com.gh0u1l5.wechatmagician.spellbook.interfaces.IDatabaseHook
import com.gh0u1l5.wechatmagician.spellbook.util.BasicUtil.tryVerbosely
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import io.merculet.wxbot.domain.ReplyReq
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.handler.AbsHandler
import io.merculet.wxbot.handler.TextHandler
import io.merculet.wxbot.handler.TuringHandler
import io.merculet.wxbot.handler.WebHandler
import io.merculet.wxbot.util.OkHttpUtils

/**
 * @Author Aaron
 * @Date 2018/12/3
 * @Email aaron@magicwindow.cn
 * @Description
 */
object MsgHook : IDatabaseHook {

    private val groupSymbol = "@chatroom"

    private var firstHandler: AbsHandler

    override fun onDatabaseInserting(thisObject: Any, table: String, nullColumnHack: String?, initialValues: ContentValues?, conflictAlgorithm: Int): Operation<Long> {
        if (table == "message") {
            tryVerbosely {
                initialValues?.run {
                    reply(this)
                }
            }
        }
        return super.onDatabaseInserting(thisObject, table, nullColumnHack, initialValues, conflictAlgorithm)
    }

    init {
        val handlers = arrayListOf<AbsHandler>()
        handlers.add(TextHandler())
        handlers.add(WebHandler())
        handlers.add(TuringHandler())

        for (i in 0 until handlers.size) {
            if (i > 0) {
                handlers[i - 1].nextHandler = handlers[i]
            }
        }
        firstHandler = handlers[0]
    }

    private fun reply(contentValues: ContentValues) {

        val isSend = contentValues.getAsInteger("isSend");
        val type = contentValues.getAsInteger("type")
        LogUtil.log("MsgHook reply isSend:$isSend, type:$type，contentValues:$contentValues")

        if (isSend != 1) {//1 代表自己发出的，不处理
            if (type == 1) { //文本消息
                // field_content 就是消息内容，可以接入图灵机器人回复
                var contentStr = contentValues.getAsString("content")
                val talker = contentValues.getAsString("talker")
                val talkerId = contentValues.getAsInteger("talkerId")
                LogUtil.log("MsgHook reply replyContent: $contentStr")

                //过滤掉群内
                if (talker.endsWith(groupSymbol) && contentStr.matches(".*:\n.*".toRegex())){
                    contentStr = ".*:\n(.*)".toRegex().find(contentStr)?.groups?.get(1)?.value
                }

                val request = ReplyReq().apply {
                    commandKey = contentStr
                    chatRoomId = talker
                }


                OkHttpUtils.instance.postByCommandKey(request) { response ->

                    var data = response?.data
                    if (data == null) {
                        data = ReplyRes.Reply(null, talker, 0, contentStr, null, null)
                    } else {
                        data.talker = talker
                        data.inputText = contentStr
                        data.talkerId = talkerId
                    }
                    LogUtil.log("firstHandler data: $data")
                    firstHandler.handleReply(data)
                }
            }
        }

    }

    // lvbuffer=[B@444bb8e0 bizClientMsgId= createTime=1544173214000 status=3 msgSeq=688940152 type=1 talkerId=25 content=hi msgSvrId=2134928543161258323 flag=0 bizChatId=-1 msgId=26 isSend=0 talker=bravoon
    private fun getStringValueByKey(string: String, key: String): String {
        val wxMsgSplitStr1 = " "
        val wxMsgSplitStr2 = "="
        var result: String = ""
        val list = string.split(wxMsgSplitStr1)
        list.forEach {
            val splitIndex = it.indexOf(wxMsgSplitStr2)
            val endIndex = it.length

            if (splitIndex in 1..(endIndex - 1)) {
                val realKey = it.substring(0, splitIndex)
                //取最后一个
                if (realKey == key) {
                    result = it.substring(splitIndex + 1, endIndex)
                }
            }

        }
        return result
    }
}