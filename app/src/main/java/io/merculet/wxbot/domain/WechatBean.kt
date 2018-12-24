package io.merculet.wxbot.domain

import java.io.Serializable

class WechatBean : Serializable {
    var id: Int = 0
    var username: String? = null
    var alias: String? = null
    var avatarImage: String? = null
    var nickname: String? = null
    var type: Int = 0
}