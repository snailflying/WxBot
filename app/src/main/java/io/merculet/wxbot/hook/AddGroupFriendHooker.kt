package io.merculet.wxbot.hook

import android.content.Intent
import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal
import com.gh0u1l5.wechatmagician.spellbook.base.Hooker
import com.gh0u1l5.wechatmagician.spellbook.base.HookerProvider
import com.gh0u1l5.wechatmagician.spellbook.util.LogUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers.*


/**
 * @Description 添加群组好友
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2019/1/2 5:10 PM
 * @Version 1.0.0
 */
object AddGroupFriendHooker : HookerProvider {

    override fun provideStaticHookers(): List<Hooker>? = listOf(addFriend())

    private fun addFriend(): Hooker = Hooker {
        LogUtil.log("AddGroupFriendHooker addFriend ~")
        val list = ArrayList<String>()
        list.add("wxid_b5ltyvv78tpt11")

        val clazz = findClass("com.tencent.mm.ui.contact.SelectContactUI", WechatGlobal.wxLoader)
//        val activityThread = callStaticMethod(findClass("android.app.ActivityThread", null), "currentActivityThread")
//        LogUtil.log("AddGroupFriendHooker ~ ${com.tencent.mm.roomsdk.a.b} ~ ")

        //com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(this.dmT, G, str2);
        val clazzss = findClass("com.tencent.mm.roomsdk.a.b", WechatGlobal.wxLoader)
        val requestCaller = callStaticMethod(clazzss, "YK", "12088168663@chatroom")
        callMethod(requestCaller, "a", "12088168663@chatroom", list, null)

        XposedBridge.hookMethod(Methods.AddFriend_em, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("劫持结束了~")
                LogUtil.log("劫持结束了~  thisObject: ${param.thisObject}")
                LogUtil.log("劫持结束了~  method: ${param.method.name}")
                LogUtil.log("参数1 = " + param.args[0])
            }
        })

        val chatroomInfoUI = findClass("com.tencent.mm.chatroom.ui.ChatroomInfoUI", WechatGlobal.wxLoader)
        findAndHookMethod(chatroomInfoUI, "onActivityResult", C.Int, C.Int, C.Intent, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("Select_Contact 开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("Select_Contact 开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])  //1
                LogUtil.log("param: " + param.args[1])  //-1
                LogUtil.log("param: " + param.args[2])

                val intent = param.args[2] as Intent
                val selectContact = intent.getStringExtra("Select_Contact")
                LogUtil.log("Select_Contact: $selectContact")   //luxiao680766  -- qq970484954,luxiao680766
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("Select_Contact 劫持结束了~")
                LogUtil.log("劫持结束了~  thisObject: ${param.thisObject}")
                LogUtil.log("劫持结束了~  method: ${param.method.name}")
                LogUtil.log("参数1 = " + param.args[0])
            }
        })

        findAndHookMethod(chatroomInfoUI, "c", C.String, C.String, C.Int, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("ChatroomInfoUI_c 开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("ChatroomInfoUI_c 开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])  //luxiao680766
                LogUtil.log("param: " + param.args[1])  //null
                LogUtil.log("param: " + param.args[2])  //2131296401
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("Select_Contact 劫持结束了~")
            }
        })

        //final com.tencent.mm.roomsdk.a.c.a a = com.tencent.mm.roomsdk.a.b.YK(this.dmT).a(this.dmT, G, str2);
        //com.tencent.mm.roomsdk.a
        findAndHookMethod(findClass("com.tencent.mm.roomsdk.a.b", WechatGlobal.wxLoader), "YK", C.String, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("roomsdk_yk 开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("roomsdk_yk 开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])  //12088168663@chatroom
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("roomsdk_yk 劫持结束了~")
            }
        })
        //com.tencent.mm.roomsdk.a.b.a
        findAndHookMethod(findClass("com.tencent.mm.roomsdk.a.b.a", WechatGlobal.wxLoader), "a", C.Int, C.Int, C.String, Any::class.java, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                LogUtil.log("roomsdk_a 开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("roomsdk_a 开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])
                LogUtil.log("param: " + param.args[1])
                LogUtil.log("param: " + param.args[2])
                LogUtil.log("param: " + param.args[3])
            }
        })

        //com.tencent.mm.roomsdk.a.a.a
        findAndHookMethod(findClass("com.tencent.mm.roomsdk.a.a.a", WechatGlobal.wxLoader), "a", C.String, C.List, C.String, object : XC_MethodHook() {

            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                LogUtil.log("roomsdk_a 开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("roomsdk_a 开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])
                LogUtil.log("param: " + param.args[1])
                LogUtil.log("param: " + param.args[2])
            }
        })

        XposedBridge.hookAllMethods(clazz, "en", object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("en开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("en开始劫持了~  method: ${param.method.name}")
                LogUtil.log("enparam: " + param.args[0])
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("en劫持结束了~")
                LogUtil.log("en劫持结束了~  thisObject: ${param.thisObject}")
                LogUtil.log("en劫持结束了~  method: ${param.method.name}")
                LogUtil.log("en参数1 = " + param.args[0])
            }
        })
        XposedBridge.hookAllMethods(clazz, "adU", object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("adU开始劫持了~  thisObject: ${param.thisObject}")
                LogUtil.log("adU开始劫持了~  method: ${param.method.name}")
                LogUtil.log("param: " + param.args[0])
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("adU劫持结束了~")
                LogUtil.log("adU劫持结束了~  thisObject: ${param.thisObject}")
                LogUtil.log("劫持结束了~  method: ${param.method.name}")
                LogUtil.log("参数1 = " + param.args[0])
            }
        })

        XposedBridge.hookAllMethods(clazz, "xU", object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("开始劫持了~  method: ${param.method.name}")
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("劫持结束了~  thisObject: ${param.thisObject}")
            }
        })

        XposedBridge.hookAllMethods(clazz, "e", object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("开始劫持了~  method: ${param.method.name}")
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("劫持结束了~  param: ${param.args[0]}")
            }
        })

        XposedBridge.hookAllMethods(findClass("com.tencent.mm.ui.contact.z", WechatGlobal.wxLoader), "a", object : XC_MethodHook() {

            override fun beforeHookedMethod(param: MethodHookParam) {
                LogUtil.log("contact.z 开始劫持了~  method: ${param.method.name}")
            }

            override fun afterHookedMethod(param: MethodHookParam) {
                LogUtil.log("劫持结束了~  method: ${param.method.name}")
            }
        })
    }

}