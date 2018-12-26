package com.wanzi.wechatrecord

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AutoStartBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private const val ACTION_BOOT = "android.intent.action.BOOT_COMPLETED"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_BOOT) {
            val startIntent = Intent(context, WxInfoActivity::class.java)
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(startIntent)
        }
    }
}
