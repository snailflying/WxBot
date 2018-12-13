package io.merculet.wxbot.domain

import java.io.Serializable

/**
 * @Author Aaron
 * @Date 2018/12/10
 * @Email aaron@magicwindow.cn
 * @Description
 */
data class TuringRes(var intent: Intent?, var results: List<Results>?) : Serializable {

    data class Intent(
            var actionName: String?,
            var code: Int?,
            var intentName: String?
    ) : Serializable

    data class Results(
            var groupType: Int? = 1,
            var resultType: String? = "text",
            var values: Values?

    ) : Serializable {
        data class Values(
                var text: String?
        ) : Serializable
    }


}