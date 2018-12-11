package io.merculet.wxbot.hook

import android.content.ContentValues
import com.gh0u1l5.wechatmagician.spellbook.base.Operation
import com.gh0u1l5.wechatmagician.spellbook.interfaces.IDatabaseHook
import de.robv.android.xposed.XposedBridge
import io.merculet.wxbot.domain.ReplyReq
import io.merculet.wxbot.hook.SendMsgHooker.wxMsgSplitStr
import io.merculet.wxbot.util.OkHttpUtils

/**
 * @Author Aaron
 * @Date 2018/12/3
 * @Email aaron@magicwindow.cn
 * @Description
 */
object MsgHook : IDatabaseHook {

    override fun onDatabaseInserting(thisObject: Any, table: String, nullColumnHack: String?, initialValues: ContentValues?, conflictAlgorithm: Int): Operation<Long> {
        if (table == "message") {
            XposedBridge.log("aaron1 MsgHook onDatabaseInserting initialValues: $initialValues")

            val initialValuesStr = initialValues.toString()

            reply(initialValuesStr)

        }
        return super.onDatabaseInserting(thisObject, table, nullColumnHack, initialValues, conflictAlgorithm)
    }

    private fun reply(initialValuesStr: String) {

        val isSend = " isSend=(\\d+) type=".toRegex().find(initialValuesStr)?.groups?.get(1)?.value
        val type = " type=(\\d+) bizChatId=".toRegex().find(initialValuesStr)?.groups?.get(1)?.value
        XposedBridge.log("aaron1 MsgHook onDatabaseInserting isSend:$isSend, type:$type")

        if (isSend != "1") {//1 代表自己发出的，不处理
            if (type == "1") { //文本消息
                // field_content 就是消息内容，可以接入图灵机器人回复
                val contentStr = getStringValueByKey(initialValuesStr, "content")
                val talker = getStringValueByKey(initialValuesStr, "talker")
                XposedBridge.log("aaron1 MsgHook onDatabaseInserting replyContent: $contentStr")


                val request = ReplyReq()
                request.commandKey = contentStr
                request.chatRoomId = talker

                OkHttpUtils.instance.getByCommandKey(request) { response ->

                    if (response.data?.detail?.content != null) {
                        Objects.ChattingFooterEventImpl?.apply {
                            // 将 wx_id 和 回复的内容用分隔符分开
//                        val talker = getStringValueByKey(initialValuesStr, "talker")
                            val content = "$talker$wxMsgSplitStr${response.data?.detail?.content}"
                            XposedBridge.log("aaron1 MsgHook onDatabaseInserting Methods1 = ${Methods.ChattingFooterEventImpl_SendMsg}")

                            val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                            XposedBridge.log("aaron1 MsgHook onDatabaseInserting reply msg success1 = $success")
                        }
                    } else {
                        Objects.ChattingFooterEventImpl?.apply {
                            // 将 wx_id 和 回复的内容用分隔符分开
//                        val talker = getStringValueByKey(initialValuesStr, "talker")
                            val content = "$talker$wxMsgSplitStr$contentStr"
                            XposedBridge.log("aaron1 MsgHook onDatabaseInserting Methods2 = ${Methods.ChattingFooterEventImpl_SendMsg}")

                            val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                            XposedBridge.log("aaron1 MsgHook onDatabaseInserting reply msg success2 = $success")
                        }
                    }

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
                if (realKey == key) {
                    result = it.substring(splitIndex + 1, endIndex)
                    return result
                }
            }

        }
        return result
    }
}