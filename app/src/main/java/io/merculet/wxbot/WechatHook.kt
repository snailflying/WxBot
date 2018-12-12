package io.merculet.wxbot

import com.gh0u1l5.wechatmagician.spellbook.SpellBook
import com.gh0u1l5.wechatmagician.spellbook.util.BasicUtil
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.merculet.wxbot.hook.MsgHook
import io.merculet.wxbot.hook.SendMsgHooker

class WechatHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        BasicUtil.tryVerbosely {
            if (SpellBook.isImportantWechatProcess(lpparam)) {
                LogUtil.log("Hello Wechat!")
                SpellBook.startup(lpparam, listOf(
                        SendMsgHooker,
                        MsgHook
                ))
            }
        }
    }
}