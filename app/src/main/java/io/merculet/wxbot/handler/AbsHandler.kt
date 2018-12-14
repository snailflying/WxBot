package io.merculet.wxbot.handler

import io.merculet.wxbot.domain.ReplyRes

/**
 * @Author Aaron
 * @Date 2018/12/12
 * @Email aaron@magicwindow.cn
 * @Description
 */
abstract class AbsHandler {

    // 责任链的下一个节点，即处理者
    var nextHandler: AbsHandler? = null

    // 捕获具体请求并进行处理，或是将请求传递到责任链的下一级别
    fun handleReply(reply: ReplyRes.Reply?) {

        if (reply == null) {
            return
        }

        if (!handle(reply)) {
            // 当前处理者不能胜任，则传递至责任链的下一节点
            if (this.nextHandler != null) {
                this.nextHandler!!.handleReply(reply)
            }
        }
    }

    // 处理异步handle by aaron 2018/12/14
    protected fun handlerNextForAsyncCallback(reply: ReplyRes.Reply?) {
        if (reply == null) {
            return
        }
        if (this.nextHandler != null) {
            this.nextHandler!!.handleReply(reply)
        }
    }


    // 定义链中每个处理者具体的处理方式
    protected abstract fun handle(reply: ReplyRes.Reply): Boolean
}
