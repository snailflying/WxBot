package com.gh0u1l5.wechatmagician.spellbook.mirror.com.tencent.mm.storage

import com.gh0u1l5.wechatmagician.spellbook.C
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxClasses
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLazy
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxLoader
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxPackageName
import com.gh0u1l5.wechatmagician.spellbook.WechatGlobal.wxVersion
import com.gh0u1l5.wechatmagician.spellbook.base.Version
import com.gh0u1l5.wechatmagician.spellbook.util.ReflectionUtil.findClassesFromPackage
import de.robv.android.xposed.XposedBridge

object Classes {
    val MsgInfo: Class<*> by wxLazy("MsgInfo") {
        val classes = findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
        XposedBridge.log("aaron1 Classes MsgInfo:${classes.firstOrNull()}, packge:$wxPackageName.storage")
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
                .filterByMethod(C.Boolean, "isSystem")
                .firstOrNull()
    }

    val MsgInfoStorage: Class<*> by wxLazy("MsgInfoStorage") {
        val classes = findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
        XposedBridge.log("aaron1 Classes wxLoader:${wxLoader}")
        XposedBridge.log("aaron1 Classes wxClasses:${wxClasses}")
        XposedBridge.log("aaron1 Classes wxPackageName:$wxPackageName.storage")
        XposedBridge.log("aaron1 Classes MsgInfoStorage:${classes.firstOrNull()}")

        when {

            wxVersion!! >= Version("6.5.8") ->
                findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
                        .filterByMethod(C.Long, MsgInfo, C.Boolean)
                        .firstOrNull()
            else ->
                findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
                        .filterByMethod(C.Long, MsgInfo)
                        .firstOrNull()
        }
    }

    val ContactInfo: Class<*> by wxLazy("ContactInfo") {
        findClassesFromPackage(wxLoader!!, wxClasses!!, "$wxPackageName.storage")
                .filterByMethod(C.String, "getCityCode")
                .filterByMethod(C.String, "getCountryCode")
                .firstOrNull()
    }
}