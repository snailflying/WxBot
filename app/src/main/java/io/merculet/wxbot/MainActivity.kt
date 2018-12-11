package io.merculet.wxbot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.merculet.wxbot.domain.ReplyReq
import io.merculet.wxbot.util.OkHttpUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        reply()
    }

    private fun reply() {

        val request = ReplyReq()
        request.commandKey = "bravoon:\n" +
                "    哈哈哈哈"
        request.chatRoomId = "4614800334@chatroom"

        OkHttpUtils.instance.getByCommandKey(request) { response ->


            Log.e("aaron1", "response:${response.data}")

        }

    }

}
