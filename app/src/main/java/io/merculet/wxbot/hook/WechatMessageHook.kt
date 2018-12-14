package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.interfaces.IMessageStorageHook
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil.printObjectFields
import de.robv.android.xposed.XposedHelpers

object WechatMessageHook : IMessageStorageHook {
//    override fun onMessageStorageCreated(storage: Any) {
//        LogUtil.log("SpellBook onMessageStorageCreated storage=$storage")
//
//    }

    override fun onMessageStorageInserted(msgId: Long, msgObject: Any) {
        LogUtil.log("WechatMessageHook onMessageStorageInserted msgId=$msgId,msgObject=$msgObject")
        printObjectFields(msgObject)
        // 这些都是消息的属性，内容，发送人，类型等
        val field_content = XposedHelpers.getObjectField(msgObject, "field_content") as String?
        val field_talker = XposedHelpers.getObjectField(msgObject, "field_talker") as String?
        val field_type = (XposedHelpers.getObjectField(msgObject, "field_type") as Int).toInt()
        val field_isSend = (XposedHelpers.getObjectField(msgObject, "field_isSend") as Int).toInt()
        LogUtil.log("WechatMessageHook field_content=$field_content,field_talker=$field_talker," +
                "field_type=$field_type,field_isSend=$field_isSend")
//        if (field_isSend == 1) {// 代表自己发出的，不处理
//            return
//        }
//        if (field_type == 1) { //文本消息
//            // field_content 就是消息内容，可以接入图灵机器人回复
//            val replyContent = "reply: \n$field_content"
//            Objects.ChattingFooterEventImpl?.apply {
//                // 将 wx_id 和 回复的内容用分隔符分开
//                val content = "$field_talker$WX_MSG_SPLIT$replyContent"
//                val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
//                LogUtil.log("reply msg success = $success")
//            }
//        }
    }

}