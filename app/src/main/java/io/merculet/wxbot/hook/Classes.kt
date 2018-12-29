package io.merculet.wxbot.hook

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxClasses
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLazy
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLoader
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxPackageName
import com.gh0u1l5.wechatmagician.spellbook.util.ReflectionUtil.findClassesFromPackage

object Classes {
    // 这个类有一个方法可以直接发送消息
    val ChattingFooterEventImpl: Class<*> by wxLazy("ChattingFooterEventImpl") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.ui.chatting")
                .filterByField("android.media.ToneGenerator")
                .filterByField("android.os.Vibrator")
                .filterByField("com.tencent.mm.pluginsdk.ui.chat.ChatFooter")
                .firstOrNull()
    }

    // 发送消息的封装类，可以 hook 消息目的地
    val NetSceneSendMsg: Class<*> by wxLazy("NetSceneSendMsg") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.modelmulti")
                .filterByField("java.util.List")
                .filterByField("long")
                .filterByField("boolean")
                .filterByField("int")
                .filterByMethod(C.String, C.String, C.Object, C.Int)
                .filterByMethod(C.Int, "getType")
                .firstOrNull()
    }

    // 自动添加好友
    val SayHiWithSnsPermissionUI: Class<*> by wxLazy("SayHiWithSnsPermissionUI") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.plugin.profile.ui") //com.tencent.mm.plugin.profile.ui;
                .filterByField("android.view.MenuItem.OnMenuItemClickListener")
                .filterByField("android.text.style.ClickableSpan")
                .filterByField("android.view.MenuItem")
                .filterByField("android.widget.EditText")
                .filterByField("com.tencent.mm.ui.MMActivity")
                .filterByField("android.app.ProgressDialog")
                .filterByField("android.content.DialogInterface.OnCancelListener")
                .firstOrNull()
    }

    // 自动添加好友
    val LuckyMoneyReceiveUI: Class<*> by wxLazy("LuckyMoneyReceiveUI") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.plugin.luckymoney.ui") //com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI
                .filterByField("android.widget.RelativeLayout.LayoutParams")
                .filterByField("android.content.Intent")
                .filterByField("android.widget.Button")
                .filterByField("com.tencent.mm.sdk.platformtools.BackwardSupportUtil")
                .filterByField("com.tencent.mm.wallet_core.ui.WalletTextView")
                .filterByField("android.content.DialogInterface.OnCancelListener")
                .firstOrNull()
    }
}