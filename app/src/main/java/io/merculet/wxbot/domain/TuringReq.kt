package io.merculet.wxbot.domain

import io.merculet.core.config.Config

/**
 * @Author Aaron
 * @Date 2018/12/10
 * @Email aaron@magicwindow.cn
 * @Description
 */
class TuringReq(inputText: String, userName: Int = 1) {

    val user = userName
    var reqType: Int? = 0
    var perception: Perception? = Perception(inputText)
    var userInfo: UserInfo? = UserInfo(userName)

    class Perception(inputText: String) {
        var inputText: InputText? = InputText(inputText)
        var selfInfo: SelfInfo? = null

        //输入
        class InputText(inputText: String) {
            var text: String? = inputText
        }

        //用户所在地，暂时不用
        class SelfInfo {
            var location: Location? = null

            class Location {
                var city: String? = null
                var province: String? = null
                var street: String? = null
            }
        }
    }

    class UserInfo(userId: Int) {
        var apiKey: String = Config.TURING_ID
        //用户名
        var userId: String = userId.toString()
    }

}