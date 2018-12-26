package io.merculet.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.content.res.Configuration
import android.graphics.Point
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.view.WindowManager
import java.util.*


/**
 * 获得设备信息
 *
 * @author Aaron.Liu
 */
object Utils {

    private var VERSION_NAME: String = "1.0"
    private var VERSION_CODE: Int = 0


    /**
     * 检查权限是否开启
     *
     * @param permission
     * @return true or false
     */
    @JvmStatic
    fun checkPermissions(context: Context?, permission: String): Boolean {

        val localPackageManager = context!!.applicationContext.packageManager
        return localPackageManager.checkPermission(permission, context.applicationContext.packageName) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 获得系统版本
     *
     * @return os version
     */
    val osVersion: String
        get() = Build.VERSION.RELEASE

    /**
     * 获取设备品牌
     *
     * @return branding
     */
    val branding: String
        get() = Build.BRAND

    /**
     * 获得制造商
     *
     * @return manufacturer
     */
    val manufacturer: String
        get() = Build.MANUFACTURER

    /**
     * 设备的名字
     *
     * @return device model
     */
    val device: String
        get() = Build.MODEL


    /**
     * 获得本地语言和国家
     *
     * @return Language +county
     */
    val local: String
        get() {
            val locale = Locale.getDefault()
            return locale.language + "_" + locale.country
        }


    @SuppressLint("HardwareIds", "MissingPermission", "PrivateApi")
    fun getIMEI(context: Context): String? {
        return if (checkPermissions(context, "android.permission.READ_PHONE_STATE")) {
            if (context.getSystemService(Context.TELEPHONY_SERVICE) != null) {
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    telephonyManager.imei
                } else {
                    telephonyManager.deviceId
                }
            } else {
                null
            }
        } else {
            null
        }


    }

    @SuppressLint("ObsoleteSdkInt")
//这个可获取到类似1080*1920
    fun getScreenSize(context: Context): String {

        var result = ""
        try {
            //first method
            if (Build.VERSION.SDK_INT in 13..16) {
                val windowManager = context.getSystemService(Context
                        .WINDOW_SERVICE) as WindowManager
                val display = windowManager.defaultDisplay
                val size = Point()
                display.getSize(size)

                val screenWidth = size.x
                val screenHeight = size.y
                result = if (context.resources.configuration.orientation == Configuration
                                .ORIENTATION_PORTRAIT) {
                    screenWidth.toString() + "x" + screenHeight
                } else {
                    screenHeight.toString() + "x" + screenWidth
                }
            } else if (Build.VERSION.SDK_INT >= 17) {
                val windowManager = context.getSystemService(Context
                        .WINDOW_SERVICE) as WindowManager
                val display = windowManager.defaultDisplay
                val size = Point()

                display.getRealSize(size)

                val screenWidth = size.x
                val screenHeight = size.y
                result = if (context.resources.configuration.orientation == Configuration
                                .ORIENTATION_PORTRAIT) {
                    screenWidth.toString() + "x" + screenHeight
                } else {
                    screenHeight.toString() + "x" + screenWidth
                }
            } else {
                val dm2 = context.resources.displayMetrics
                // 竖屏
                result = if (context.resources.configuration.orientation == Configuration
                                .ORIENTATION_PORTRAIT) {
                    dm2.widthPixels.toString() + "x" + dm2.heightPixels
                } else {// 横屏
                    dm2.heightPixels.toString() + "x" + dm2.widthPixels
                }
            }
        } catch (ignored: Exception) {

        }

        return result
    }

    /**
     * 获得应用的包名
     *
     * @param context context
     * @return package name
     */
    fun getPackageName(context: Context): String {
        return context.packageName
    }


    /**
     * 获得应用名
     *
     * @param context context
     * @return package name
     */
    fun getAppName(context: Context): String {
        var appName = ""
        try {
            appName = context.packageManager.getApplicationLabel(context.applicationInfo) as String
        } catch (ignored: Exception) {
        }

        return appName
    }

    /**
     * 获得当前应用的版本号
     *
     * @param context context
     * @return App Version
     */

    @Synchronized
    fun getAppVersionName(context: Context): String {
        if (!TextUtils.isEmpty(VERSION_NAME)) {
            return VERSION_NAME
        }

        var info: PackageInfo? = null
        try {
            info = context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (ignored: NameNotFoundException) {
        }

        if (info != null) {
            VERSION_NAME = info.versionName
            VERSION_CODE = info.versionCode
        }
        return VERSION_NAME
    }

    @Synchronized
    fun getAppVersionCode(context: Context): Int {
        if (VERSION_CODE != 0) {
            return VERSION_CODE
        }
        var info: PackageInfo? = null
        try {
            info = context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (ignored: NameNotFoundException) {
        }

        if (info != null) {
            VERSION_CODE = info.versionCode
            VERSION_NAME = info.versionName
        }
        return VERSION_CODE
    }

    /**
     * 判断当前设备是手机还是平板
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    private fun isTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration
                .SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun getDeviceType(context: Context): Int {
        return if (isTablet(context)) {
            1
        } else {
            0
        }
    }

    private var MWChannel: String = ""


    /**
     * 读取application 节点  meta-data 信息
     */
    fun getMetaDataFromApplication(context: Context, tag: String): String {
        var metaData: String = ""
        if (TextUtils.isEmpty(tag)) {
            return ""
        }

        try {
            val appInfo = context.packageManager
                    .getApplicationInfo(context.packageName,
                            PackageManager.GET_META_DATA)
            if (appInfo.metaData != null && appInfo.metaData.getString(tag) != null) {
                metaData = appInfo.metaData.getString(tag)
                if (!TextUtils.isEmpty(metaData)) {
                    metaData = metaData.trim { it <= ' ' }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return metaData
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {

        if (!checkPermissions(context, "android.permission.ACCESS_NETWORK_STATE")){
            return false
        }
        val manager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false
        val networkinfo = manager.activeNetworkInfo
        return !(networkinfo == null || !networkinfo.isAvailable)
    }

    /**
     * 首先检查手机是否安装了某一项APP
     */
    fun isAPPInstalled(context: Context, packageName: String): Boolean {
        val pm = context.packageManager
        val pinfo = pm.getInstalledPackages(0)
        pinfo.forEachIndexed { index, _ ->
            if (pinfo[index].packageName == packageName) {
                return true
            }
        }
        return false
    }
}
