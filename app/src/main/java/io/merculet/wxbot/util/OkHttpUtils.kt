/*
 * ************************************************************
 * 文件：OkHttpUtils.java  模块：app  项目：WeChatGenius
 * 当前修改时间：2018年08月20日 16:05:59
 * 上次修改时间：2018年08月20日 16:05:59
 * 作者：大路
 * Copyright (c) 2018
 * ************************************************************
 */

package io.merculet.wxbot.util

import android.util.Log
import com.google.gson.Gson
import io.merculet.wxbot.domain.ReplyReq
import io.merculet.wxbot.domain.ReplyRes
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpUtils private constructor() {
    //单例获取OkHttpClient实例
    private val okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .build()
    }

    fun getByCommandKey(body: ReplyReq, onSuccess: (response: ReplyRes) -> Unit) {
        val path = "v1/score/community/robot/command/getByCommandKey"

        val requestBody = RequestBody.create(JSON_TYPE, Gson().toJson(body))

        val request = Request.Builder()
                .url(HOST + path)
                .post(requestBody)
                .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                val responseStr = response.body()?.string()
//                Log.e("aaron1", "response$response, response body: $responseStr")
                if (response.code() == 200) {
                    val replyRes: ReplyRes = Gson().fromJson(responseStr, ReplyRes::class.java)
                    onSuccess(replyRes)
                }

            }

        })

    }

    companion object {

        private const val HOST = " http://score-community.liaoyantech.cn/api/"
        private val JSON_TYPE = MediaType.parse("application/json; charset=utf-8")

        private const val DEFAULT_READ_TIMEOUT_MILLIS = (15 * 1000).toLong()
        private const val DEFAULT_WRITE_TIMEOUT_MILLIS = (20 * 1000).toLong()
        private const val DEFAULT_CONNECT_TIMEOUT_MILLIS = (20 * 1000).toLong()
        @Volatile
        private var sInstance: OkHttpUtils? = null

        val instance: OkHttpUtils
            get() {
                if (sInstance == null) {
                    synchronized(OkHttpUtils::class.java) {
                        if (sInstance == null) {
                            sInstance = OkHttpUtils()
                        }
                    }
                }
                return sInstance!!
            }

    }

}
