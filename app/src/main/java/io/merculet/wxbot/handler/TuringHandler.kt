package io.merculet.wxbot.handler

import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import io.merculet.wxbot.WechatHook
import io.merculet.wxbot.config.Config
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.domain.TuringReq
import io.merculet.wxbot.hook.Methods
import io.merculet.wxbot.hook.Objects
import io.merculet.wxbot.hook.SendMsgHooker
import io.merculet.wxbot.util.OkHttpUtils

/**
 * @Author Aaron
 * @Date 2018/12/12
 * @Email aaron@magicwindow.cn
 * @Description
 */
class TuringHandler : AbsHandler() {
    private val pref = WechatHook.settings
    private fun isPluginEnabled() = pref.getBoolean(Config.SETTINGS_TURING_MASTER, false)

    override fun handle(reply: ReplyRes.Reply): Boolean {

        LogUtil.log("isPluginEnabled = ${isPluginEnabled()}")

        if (isPluginEnabled() && saidToBot(reply.talker, reply.inputText)) {
            val request = TuringReq(reply.inputText, reply.talkerId)
            OkHttpUtils.instance.postTuring(request) {
                LogUtil.log("response = $it")

                Objects.ChattingFooterEventImpl?.apply {
                    it.results?.get(0)?.values?.text?.let {
                        val content = "${reply.talker}${SendMsgHooker.wxMsgSplitStr}${it}"
                        val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                        LogUtil.log("success = $success")
                    }

                }

            }
            return true
        }


        return false
    }

    //私聊或者群聊@机器人时才回话
    private fun saidToBot(talker: String, input: String): Boolean {
        return input == "@Xposed"
                || input.startsWith("@Xposed ")
                || input.contains("@Xposed")
                || !talker.contains("@chatroom")
    }

}