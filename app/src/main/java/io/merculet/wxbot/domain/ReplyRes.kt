package io.merculet.wxbot.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @Author Aaron
 * @Date 2018/12/10
 * @Email aaron@magicwindow.cn
 * @Description
 */
class ReplyRes : HttpResponse<ReplyRes.Reply>() {

    data class Reply(
            @SerializedName("command_key")
            var commandKey: String?,
            var talker: String,
            @SerializedName("command_type")
            var commandType: String?,
            var detail: ReplyDetail?

    ) : Serializable

    data class ReplyDetail(
            var title: String?,
            var intro: String?,
            @SerializedName("thumbnail_image_url")
            var thumbnailImageUrl: String?,
            @SerializedName("content_url")
            var contentUrl: String?,
            var content: String?

    ) : Serializable
}