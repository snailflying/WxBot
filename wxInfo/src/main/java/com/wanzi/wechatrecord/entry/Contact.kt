package com.wanzi.wechatrecord.entry

import org.litepal.crud.DataSupport
import java.io.Serializable

/**
 * Created by WZ on 2018-01-29.
 */

class Contact : DataSupport(), Serializable {
    var username : String? = ""
    var nickname: String? = ""
    var type : String? = ""
    var conRemark : String? = ""
    var avatar : String? = ""
}
