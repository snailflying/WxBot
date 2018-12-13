package io.merculet.wxbot.domain

import java.io.Serializable

/**
 * @Author Aaron
 * @Date 2018/12/13
 * @Email aaron@magicwindow.cn
 * @Description
 */

class WxReply(var talker: String,
              var inputText: String) : Serializable