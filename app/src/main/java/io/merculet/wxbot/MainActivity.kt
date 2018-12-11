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
    }

}
