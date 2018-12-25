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
class WebHandler : AbsHandler() {

    override fun handle(reply: ReplyRes.Reply): Boolean {
        if (reply.detail?.contentUrl != null) {
            Objects.ChattingFooterEventImpl?.apply {
                // 将 wx_id 和 回复的内容用分隔符分开
                val content = "${reply.talker}${Config.WX_MSG_SPLIT}${reply.detail?.contentUrl}"
                val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                LogUtil.log("success = $success")
                return success
            }
        }
        LogUtil.log("WebHandler false")

        return false
    }

}