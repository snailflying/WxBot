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
import io.merculet.wxbot.domain.TuringReq
import io.merculet.wxbot.domain.TuringRes
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

    fun postByCommandKey(replyReq: ReplyReq, onRes: (response: ReplyRes?) -> Unit) {
        val path = "v1/score/community/robot/command/getByCommandKey"

        Log.e("LogUtil", "chatRoomId:${replyReq.chatRoomId}, commandKey:${replyReq.commandKey}")

        val requestBody = RequestBody.create(JSON_TYPE, Gson().toJson(replyReq))

        val request = Request.Builder()
                .url(HOST + path)
                .post(requestBody)
                .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                onRes(null)
            }

            override fun onResponse(call: Call, response: Response) {

                val responseStr = response.body()?.string()
                Log.e("LogUtil", "response.code(): ${response.code()}, response body1: $responseStr")
                if (response.code() == 200) {
                    Gson().fromJson(responseStr, ReplyRes::class.java)?.apply {
                        if (data != null) {
                            Log.e("LogUtil", "response body2: $responseStr")
                            return onRes(this)
                        }
                    }
                }
                Log.e("LogUtil", "response body3: $responseStr")
                onRes(null)

            }

        })

    }

    fun postTuring(turingReq: TuringReq, onSuccess: (response: TuringRes) -> Unit) {
        val url = "http://openapi.tuling123.com/openapi/api/v2"

        val requestBody = RequestBody.create(JSON_TYPE, Gson().toJson(turingReq))

        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
        Log.e("LogUtil", "request: $request")

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                val responseStr = response.body()?.string()
                Log.e("LogUtil", "response.code(): ${response.code()}, response body: $responseStr")
                if (response.code() == 200) {
                    Gson().fromJson(responseStr, TuringRes::class.java)?.apply {
                        onSuccess(this)
                    }
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
