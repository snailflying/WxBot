package io.merculet.wxbot.util

import com.wanzi.wechatrecord.util.FileUtils

import java.io.File

/**
 * @Description
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/24 4:40 PM
 * @Version
 */
object WeChatFileUtil {
    val WX_ROOT_PATH = "/data/data/com.tencent.mm/"
    val WX_SP_UIN_PATH = WX_ROOT_PATH + "shared_prefs/auth_info_key_prefs.xml"
    val WX_DB_DIR_PATH = WX_ROOT_PATH + "MicroMsg"
    val WX_DB_FILE_NAME = "EnMicroMsg.db"
    val mCurrApkPath = "/data/data/" + "com.wechatutils.chatrecord" + "/"
    private val COPY_WX_DATA_DB = "wx_data.db"
    val copyFilePath = mCurrApkPath + COPY_WX_DATA_DB

    fun weChatFileUtil() {
        val wxDataDir = File(WX_DB_DIR_PATH)
        searchFile(wxDataDir, WX_DB_FILE_NAME)
    }

    /**
     * 递归查询微信本地数据库文件
     * @param file     目录
     * @param fileName 需要查找的文件名称
     */
    fun searchFile(file: File, fileName: String) {
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (childFile in files) {
                    searchFile(childFile, fileName)
                }
            }
        } else {
            if (fileName == file.name) {
                FileUtils.copyFile(file.absolutePath, copyFilePath)
            }
        }
    }
}
