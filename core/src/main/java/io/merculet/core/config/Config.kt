package io.merculet.core.config

/**
 * @Author Aaron
 * @Date 2018/12/13
 * @Email aaron@magicwindow.cn
 * @Description
 */
object Config {

    const val NETWORK_WIFI = "0"
    const val NETWORK_2G = "1"
    const val NETWORK_3G = "2"
    const val NETWORK_4G = "3"
    const val NO_NETWORK = "-1"

    const val FOLDER_SHARED_PREFS = "shared_prefs"
    const val ACTION_UPDATE_PREF = "io.merculet.wxbot.ACTION_UPDATE_PREF"
    const val PREFERENCE_NAME_SETTINGS = "settings"
    const val PREFERENCE_PROVIDER_AUTHORITY = "io.merculet.wxbot.preferences"

    const val SETTINGS_INTERFACE_HIDE_ICON = "settings_interface_hide_icon"
    const val SETTINGS_TURING_MASTER = "settings_turing_master"

    const val TURING_ID = "922271764dce42f890ffe347101df65c"

    const val ROUTER_FRAGMENT_SETTINGS = "fragment/settings"
    const val ROUTER_ACTIVITY_WX_INFO = "activity/wx_info"

    const val FRAGMENT_SETTINGS_ARG_PREF_RES = "preferencesResId"
    const val  FRAGMENT_SETTINGS_ARG_PREF_NAME = "preferencesFileName"

    const val PROVIDER_PREF_KEY = "key"
    const val PROVIDER_PREF_VALUE = "value"
    const val PROVIDER_PREF_TYPE = "type"
    const val DB_PWD = "db_pwd"
    const val UIN_ENC = "uin_enc"

    // wx_id 和消息 的分隔符号，可以使用 wx_id 中不会出现的字符
    const val WX_MSG_SPLIT = "\t"
    const val MERCULET_MSG_SPLIT = "command-newline-separator"

    const val WX_ROOT_PATH = "/data/data/com.tencent.mm/"                               // 微信根目录
    const val WX_SP_UIN_PATH = "${WX_ROOT_PATH}shared_prefs/auth_info_key_prefs.xml"    // 微信保存uin的目录
    const val WX_DB_DIR_PATH = "${WX_ROOT_PATH}MicroMsg/"                               // 微信保存聊天记录数据库的目录
    const val WX_DB_FILE_NAME = "EnMicroMsg.db"                                         // 微信聊天记录数据库
    const val WX_FILE_PATH = "/storage/emulated/0/Tencent/micromsg/"                    // 微信保存聊天时语音、图片、视频文件的地址
    const val currApkPath = "/storage/emulated/0/"
    const val COPY_WX_DATA_DB = "wx_data.db"
    const val COPY_FILE_PATH = currApkPath + COPY_WX_DATA_DB
}