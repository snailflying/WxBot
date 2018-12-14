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
    private val botName = "@Xposed"

    override fun handle(reply: ReplyRes.Reply): Boolean {

        LogUtil.log("isPluginEnabled = ${isPluginEnabled()}")

        if (isPluginEnabled() && saidToBot(reply.talker, reply.inputText)) {

            //过滤掉@Xposed
            var input: String = reply.inputText
            if (reply.talker.endsWith("@chatroom") && reply.inputText.matches(".*$botName.*".toRegex())) {
                input = ".*$botName(.*)".toRegex().find(reply.inputText)?.groups?.get(1)?.value?.trim() ?: ""
            }

            val request = TuringReq(input, reply.talkerId)
            LogUtil.log("talker = ${reply.talker},talkerId = ${reply.talkerId},inputText = ${input}")

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
        return input == botName
                || input.startsWith("$botName ")
                || input.contains(botName)
                || !talker.contains("@chatroom")
    }

}