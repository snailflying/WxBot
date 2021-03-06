package io.merculet.core.ext

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.support.annotation.ColorInt
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import io.merculet.core.base.App


/**
 * @author Aaron
 * @email aaron@magicwindow.cn
 * @date 2018/4/10 12:50
 * @description
 */


/**
 * 向Toast中添加自定义view
 * @param view
 * @param postion
 * @return
 */
fun Toast.addView(addView: View, position: Int): Toast {

    (view as LinearLayout).addView(addView, position)

    return this
}

fun Toast.addView(block: () -> View, position: Int): Toast {

    (view as LinearLayout).addView(block(), position)

    return this
}

fun Toast.setGravityCenter(): Toast {
    setGravity(Gravity.CENTER, 0, 0)
    return this
}

/**
 * 设置Toast字体及背景颜色
 * @param messageColor
 * @param backgroundColor
 * @return
 */
fun Toast.setToastColor(@ColorInt messageColor: Int, @ColorInt backgroundColor: Int) {
    val view = view
    if (view != null) {
        val message = view.findViewById(android.R.id.message) as TextView
        message.setBackgroundColor(backgroundColor)
        message.setTextColor(messageColor)
    }
}

/**
 * 设置Toast字体及背景
 * @param messageColor
 * @param background
 * @return
 */
fun Toast.setBackground(@ColorInt messageColor: Int = Color.WHITE): Toast {
    val view = view
    if (view != null) {
        val message = view.findViewById(android.R.id.message) as TextView
        message.setTextColor(messageColor)
    }
    return this
}

@SuppressLint("ShowToast")
fun toast(text: CharSequence) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(App.instance, text, Toast.LENGTH_SHORT)
                .setGravityCenter()
                .setBackground()
                .show()
    }
}