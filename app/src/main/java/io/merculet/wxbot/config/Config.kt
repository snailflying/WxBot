package io.merculet.wxbot.config

/**
 * @Author Aaron
 * @Date 2018/12/13
 * @Email aaron@magicwindow.cn
 * @Description
 */
object Config {

    const val FOLDER_SHARED_PREFS = "shared_prefs"
    const val ACTION_UPDATE_PREF = "io.merculet.wxbot.ACTION_UPDATE_PREF"
    const val PREFERENCE_NAME_SETTINGS = "settings"
    const val PREFERENCE_PROVIDER_AUTHORITY = "io.merculet.wxbot.preferences"


    const val SETTINGS_INTERFACE_HIDE_ICON = "settings_interface_hide_icon"
    const val SETTINGS_TURING_MASTER = "settings_turing_master"


    const val TURING_ID = "922271764dce42f890ffe347101df65c"


    const val ROUTER_FRAGMENT_SETTINGS = "fragment/settings"

    const val FRAGMENT_SETTINGS_ARG_PREF_RES = "preferencesResId"
    const val  FRAGMENT_SETTINGS_ARG_PREF_NAME = "preferencesFileName"

    const val PROVIDER_PREF_KEY = "key"
    const val PROVIDER_PREF_VALUE = "value"
    const val PROVIDER_PREF_TYPE = "type"

    // wx_id 和消息 的分隔符号，可以使用 wx_id 中不会出现的字符
    const val WX_MSG_SPLIT = "\t"
    const val MERCULET_MSG_SPLIT = "command-newline-separator"



}