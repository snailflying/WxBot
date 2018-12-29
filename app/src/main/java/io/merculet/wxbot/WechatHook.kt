package io.merculet.wxbot

import android.app.Application
import android.content.Context
import com.gh0u1l5.wechatmagician.spellbook.SpellBook
import com.gh0u1l5.wechatmagician.spellbook.util.BasicUtil
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import com.gh0u1l5.wechatmagician.spellbook.util.Preferences
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import io.merculet.core.config.Config
import io.merculet.wxbot.hook.MsgHook
import io.merculet.wxbot.hook.SendMsgHooker
import io.merculet.wxbot.hook.TestHooker
import io.merculet.wxbot.hook.WxSqlPwdHook

class WechatHook : IXposedHookLoadPackage {

    companion object {
        val settings = Preferences.create(Config.PREFERENCE_NAME_SETTINGS)
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        hookAttachBaseContext(lpparam.classLoader) { context ->
            BasicUtil.tryVerbosely {
                if (SpellBook.isImportantWechatProcess(lpparam)) {
                    LogUtil.log("Hello Wechat!")
                    SpellBook.startup(lpparam, listOf(
                            SendMsgHooker,
                            MsgHook,
                            WxSqlPwdHook,
                            TestHooker
                    ))
                }
            }
            settings?.load(context)
            settings?.listen(context)
        }
    }

    // hookAttachBaseContext is a stable way to get current application on all the platforms.
    private inline fun hookAttachBaseContext(loader: ClassLoader, crossinline callback: (Context) -> Unit) {
        XposedHelpers.findAndHookMethod("android.content.ContextWrapper", loader, "attachBaseContext", Context::class.java,
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        callback(param.thisObject as? Application ?: return)
                    }
                })
    }
}