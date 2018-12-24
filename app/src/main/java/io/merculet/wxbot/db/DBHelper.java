package io.merculet.wxbot.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import io.merculet.wxbot.domain.WechatBean;
import io.merculet.wxbot.util.SPHelper;

/**
 * @Description
 * @Author sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 2018/12/24 4:44 PM
 * @Version
 */
public class DBHelper {

    public static final String WX_ROOT_PATH = "/data/data/com.tencent.mm/";
    public static final String WX_SP_UIN_PATH = WX_ROOT_PATH + "shared_prefs/auth_info_key_prefs.xml";
    public static final String WX_DB_DIR_PATH = WX_ROOT_PATH + "MicroMsg";
    public static final String WX_DB_FILE_NAME = "EnMicroMsg.db";
    public static final String mCurrApkPath = "/data/data/" + "com.wechatutils.chatrecord" + "/";
    private static final String COPY_WX_DATA_DB = "wx_data.db";
    public static final  String copyFilePath = mCurrApkPath + COPY_WX_DATA_DB;
    private static final String TAG = "DBHelper";
    private static final String DB_FILE = "";

    /**
     * 连接数据库
     *
     * @param
     */
    public static List<WechatBean> openWxDb(Context context, String mDbPassword) {
        List<WechatBean>  weChatDataList=new ArrayList<>();
        File copyWxDataDb = new File(copyFilePath);
        SQLiteDatabase.loadLibs(context);
        SQLiteDatabaseHook hook = new SQLiteDatabaseHook() {
            @Override
            public void preKey(net.sqlcipher.database.SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void postKey(net.sqlcipher.database.SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;"); //兼容2.0的数据库
            }

        };

        try {
            //打开数据库连接
            // SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(copyWxDataDb, mDbPassword, null, hook);
            SQLiteDatabase db = SQLiteDatabase.openDatabase(copyWxDataDb.getAbsolutePath(), mDbPassword, null,1, hook);
            //查询所有联系人（verifyFlag!=0:公众号等类型，群里面非好友的类型为4，未知类型2）
            Cursor c1 = db.rawQuery("select * from rcontact where verifyFlag = 0 and type != 4 and type != 2 and nickname != '' limit 20, 9999", null);
            String defile = SPHelper.Companion.create(context).getString(DB_FILE, "").replace("/storage/emulated/0/tencent/MicroMsg/","");
            while (c1.moveToNext()) {
                WechatBean wechatBean = new WechatBean();
                String userName = c1.getString(c1.getColumnIndex("username"));
                String alias = c1.getString(c1.getColumnIndex("alias"));
                String nickName = c1.getString(c1.getColumnIndex("nickname"));
                wechatBean.setUsername(userName);
                //微信用户头像解密
                String wechatUserAvatarImage = decryptionWechatUserAvatarImage(userName, defile);
                if(TextUtils.isEmpty(alias)){
                    alias=userName;
                }
                wechatBean.setAvatarImage(wechatUserAvatarImage);
                wechatBean.setAlias(alias);
                wechatBean.setUsername(userName);
                wechatBean.setNickname(nickName);
                weChatDataList.add(wechatBean);
            }
            c1.close();
            db.close();
        } catch (Exception e) {
            Log.d(TAG, "读取数据库信息失败" + e.toString());
            e.printStackTrace();
        }
        Log.d(TAG, "openWxDb: ========"+weChatDataList.size());
        return weChatDataList;
    }

    public static String decryptionWechatUserAvatarImage(String userName,String defile) {
        //根据微信的 WechatBean的userName然后使用md5加密，成字符串，再截取前面两个字段的文件目录
        String decryptionWechatMd5 = decryptionWechatMd5(userName.getBytes());
        //decryptionWechatMd5  5f39b18498a4107de947dc9b1e5d29b2
        String decryptionWechatSubString = decryptionWechatSubString(decryptionWechatMd5);
        //decryptionWechatSubString  5f/39/
        String imagePath = "/data/user/0/com.tencent.mm/MicroMsg/" + defile + "/avatar/" + decryptionWechatSubString + "user_" + decryptionWechatMd5+".png";
        // /data/user/0/com.tencent.mm/MicroMsg/1306e8eb3f168108d6f138fd6dbc511e/avatar/5f/39/user_5f39b18498a4107de947dc9b1e5d29b2.png
        //这个就是当前用户的头像地址
        return imagePath;
    }

    /**
     *
     *微信Md5解密
     */
    public static final String decryptionWechatMd5(byte[] bArr) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                int i4 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
                i2 = i4;
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }


    public static String decryptionWechatSubString(String str) {
        if (!TextUtils.isEmpty(str) && str.length() > 4) {
            return str.substring(0, 2) + "/" + str.substring(2, 4) + "/";
        }
        return null;
    }
}
