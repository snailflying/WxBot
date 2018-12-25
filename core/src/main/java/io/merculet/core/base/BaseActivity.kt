package io.merculet.core.base

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.v4.widget.SlidingPaneLayout
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import io.merculet.core.ext.finishWithAnim


/**
 * @author Aaron
 * @email aaron@magicwindow.cn
 * @date 18/03/2018 22:15
 * @description
 */
open class BaseActivity : AppCompatActivity(), SlidingPaneLayout.PanelSlideListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    protected open fun isSupportSwipeBack(): Boolean = true


    /**
     * 初始化滑动返回,方式2
     */

    private fun initSlideBackClose() {
        if (isSupportSwipeBack()) {
            val slidingPaneLayout = SlidingPaneLayout(this)
            // 通过反射改变mOverhangSize的值为0，
            // 这个mOverhangSize值为菜单到右边屏幕的最短距离，
            // 默认是32dp，现在给它改成0
            try {
                val overhangSize = SlidingPaneLayout::class.java.getDeclaredField("mOverhangSize")
                overhangSize.isAccessible = true
                overhangSize.set(slidingPaneLayout, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            slidingPaneLayout.setPanelSlideListener(this)
            slidingPaneLayout.sliderFadeColor = resources
                    .getColor(android.R.color.transparent)

            // 左侧的透明视图
            val leftView = View(this)
            leftView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            slidingPaneLayout.addView(leftView, 0)

            val decorView = window.decorView as ViewGroup


            // 右侧的内容视图
            val decorChild = decorView.getChildAt(0) as ViewGroup
            decorChild.setBackgroundColor(resources
                    .getColor(android.R.color.white))
            decorView.removeView(decorChild)
            decorView.addView(slidingPaneLayout)

            // 为 SlidingPaneLayout 添加内容视图
            slidingPaneLayout.addView(decorChild, 1)
        }
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {

    }

    override fun onPanelOpened(panel: View) {
        //        this.overridePendingTransition(0, R.anim.out_to_right);
        finishWithAnim()
    }

    override fun onPanelClosed(panel: View) {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean = if (KeyEvent.KEYCODE_BACK == keyCode) {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finishWithAnim()
            true
        } else {
            try {
                supportFragmentManager.popBackStackImmediate()
            } catch (e: Exception) {
            }
            true
        }
    } else {
        super.onKeyDown(keyCode, event)
    }

    /*点击空白处隐藏键盘 Start*/
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 判定当前是否需要隐藏
     */
    private fun isShouldHideKeyBord(v: View?, ev: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(ev.x > left && ev.x < right && ev.y > top && ev.y < bottom)
        }
        return false
    }

    /**
     * 隐藏软键盘
     */
    private fun hideSoftInput(token: IBinder?) {
        if (token != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            manager?.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
    /*点击空白处隐藏键盘 End*/
}