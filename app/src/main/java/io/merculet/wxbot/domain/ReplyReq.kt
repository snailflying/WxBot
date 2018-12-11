package io.merculet.wxbot.domain

import com.google.gson.annotations.SerializedName

/**
 * @Author Aaron
 * @Date 2018/12/10
 * @Email aaron@magicwindow.cn
 * @Description
 */
class ReplyReq() {
    @SerializedName("command_key")
    var commandKey: String? = null
    @SerializedName("chat_room_id")
    var chatRoomId: String? = null
}