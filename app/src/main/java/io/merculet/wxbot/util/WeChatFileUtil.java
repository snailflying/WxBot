package io.merculet.wxbot.util;

import java.io.File;

/**
 * @Description
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/24 4:40 PM
 * @Version
 */
public class WeChatFileUtil {
    public static final String WX_ROOT_PATH = "/data/data/com.tencent.mm/";
    public static final String WX_SP_UIN_PATH = WX_ROOT_PATH + "shared_prefs/auth_info_key_prefs.xml";
    public static final String WX_DB_DIR_PATH = WX_ROOT_PATH + "MicroMsg";
    public static final String WX_DB_FILE_NAME = "EnMicroMsg.db";
    public static final String mCurrApkPath = "/data/data/" + "com.wechatutils.chatrecord" + "/";
    private static final String COPY_WX_DATA_DB = "wx_data.db";
    public static final  String copyFilePath = mCurrApkPath + COPY_WX_DATA_DB;

    public static void weChatFileUtil() {
        File wxDataDir = new File(WX_DB_DIR_PATH);
        searchFile(wxDataDir, WX_DB_FILE_NAME);
    }

    /**
     * 递归查询微信本地数据库文件
     * @param file     目录
     * @param fileName 需要查找的文件名称
     */
    public static void searchFile(File file, String fileName) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    searchFile(childFile, fileName);
                }
            }
        } else {
            if (fileName.equals(file.getName())) {
                copyFile(file.getAbsolutePath(),copyFilePath);
            }
        }
    }

    private static void copyFile(String absolutePath, String copyFilePath) {

    }
}
