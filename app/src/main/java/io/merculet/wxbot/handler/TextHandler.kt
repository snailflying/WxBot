package io.merculet.wxbot.handler

import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import io.merculet.core.config.Config
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.hook.Methods
import io.merculet.wxbot.hook.Objects

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
                val content = proccessReply(reply)
                val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                return success
            }
        }
        LogUtil.log("TextHandler false")

        return false
    }

    //处理返回消息,将 wx_id 和 回复的内容用分隔符分开
    private fun proccessReply(reply: ReplyRes.Reply): String {

        return "${reply.talker}${Config.WX_MSG_SPLIT}${reply.detail?.content?.replace(Config.MERCULET_MSG_SPLIT, "\n")}"
    }
}