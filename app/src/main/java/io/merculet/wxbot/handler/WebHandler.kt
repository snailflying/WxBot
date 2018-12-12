package io.merculet.wxbot.handler

import de.robv.android.xposed.XposedBridge
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.hook.Methods
import io.merculet.wxbot.hook.Objects
import io.merculet.wxbot.hook.SendMsgHooker

/**
 * @Author Aaron
 * @Date 2018/12/12
 * @Email aaron@magicwindow.cn
 * @Description
 */
class WebHandler : AbsHandler() {

    override fun handle(reply: ReplyRes.Reply): Boolean {
        if (reply.detail?.contentUrl != null) {
            Objects.ChattingFooterEventImpl?.apply {
                // 将 wx_id 和 回复的内容用分隔符分开
                val content = "${reply.talker}${SendMsgHooker.wxMsgSplitStr}${reply.detail?.contentUrl}"
                val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                XposedBridge.log("aaron1 MsgHook reply msg success2 = $success")
                return success
            }
        }

        return false
    }

}