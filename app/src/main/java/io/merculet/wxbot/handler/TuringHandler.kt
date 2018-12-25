package io.merculet.wxbot.handler

import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import io.merculet.wxbot.WechatHook
import io.merculet.core.config.Config
import io.merculet.wxbot.domain.ReplyRes
import io.merculet.wxbot.domain.TuringReq
import io.merculet.wxbot.hook.Methods
import io.merculet.wxbot.hook.Objects
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
    private val groupSymbol = "@chatroom"

    override fun handle(reply: ReplyRes.Reply): Boolean {

        if (isPluginEnabled() && saidToBot(reply.talker, reply.inputText)) {

            val input: String = trimInput(reply)

            val request = TuringReq(input, reply.talkerId)
            LogUtil.log("talker = ${reply.talker},talkerId = ${reply.talkerId},inputText = $input")

            OkHttpUtils.instance.postTuring(request) {
                LogUtil.log("response = $it")

                val text = it?.results?.get(0)?.values?.text
                if (text != null) {
                    Objects.ChattingFooterEventImpl?.apply {
                        val content = "${reply.talker}${Config.WX_MSG_SPLIT}$text"
                        val success = Methods.ChattingFooterEventImpl_SendMsg.invoke(this, content) as Boolean
                    }
                } else {
                    handlerNextForAsyncCallback(reply)
                }

            }
            return true
        } else {
            return false
        }
    }

    //群聊时过滤掉@Xposed
    private fun trimInput(reply: ReplyRes.Reply): String {
        var result: String = reply.inputText
        if (reply.talker.endsWith(groupSymbol) && reply.inputText.matches(".*$botName.*".toRegex())) {
            result = ".*$botName(.*)".toRegex().find(reply.inputText)?.groups?.get(1)?.value?.trim() ?: ""
        }
        return result
    }

    //私聊或者群聊@机器人时才回话
    private fun saidToBot(talker: String, input: String): Boolean {
        return input == botName
                || input.startsWith("$botName ")
                || input.contains(botName)
                || !talker.contains(groupSymbol)
    }

}