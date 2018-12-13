package io.merculet.wxbot.handler

import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
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
class TextHandler : AbsHandler() {

    override fun handle(reply: ReplyRes.Reply): Boolean {
        if (reply.detail?.content != null) {
            Objects.ChattingFooterEventImpl?.apply {
                // 将 wx_id 和 回复的内容用分隔符分开
                val content = "${reply.talker}${SendMsgHooker.wxMsgSplitStr}${reply.detail?.content}"
                val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                LogUtil.log("success = $success")
                return success
            }
        }
        LogUtil.log("TextHandler false")

        return false
    }

}