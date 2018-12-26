package io.merculet.core.ext

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentActivity
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import io.merculet.core.R
import io.merculet.core.config.GlideApp
import io.merculet.core.config.GlideRequest

/**
 * @Description glide相关扩展函数
 * @Author Sean
 * @Email xiao.lu@magicwindow.cn
 * @Date 21/04/2018 8:52 PM
 * @Version 1.0.0
 */


/**
 * 占位符矩形
 */
fun ImageView.load(url: String?, placeholderRes: Int = R.drawable.shape_default_rec_bg, errorRes: Int = R.drawable.shape_default_rec_bg) {
    get(url).placeholder(placeholderRes)
            .error(errorRes)
            .into(this)
}

/**
 * 占位符圆角矩形
 */
fun ImageView.loadRound(url: String?, centerCrop: Boolean = false) {
    get(url).placeholder(R.drawable.shape_default_round_bg)
            .error(R.drawable.shape_default_round_bg)
//            .transform(RoundedCornersTransformation(DisplayUtil.dp2px(context, 10f), 0, centerCrop = centerCrop))
            .into(this)
}

/**
 * 占位符圆形
 */
fun ImageView.loadCircle(drawable: Drawable?) {
    get(drawable).placeholder(R.drawable.shape_default_circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.shape_default_circle_bg)
            .into(this)
}

fun ImageView.loadCircle(resourceID: Int?) {
    get(resourceID).placeholder(R.drawable.shape_default_circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.shape_default_circle_bg)
            .into(this)
}

fun ImageView.loadCircle(url: String?) {
    get(url).placeholder(R.drawable.shape_default_circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .error(R.drawable.shape_default_circle_bg)
            .into(this)
}

/**
 * url转换为bitmap
 */
fun FragmentActivity.asBitmap(url: String?, callback: (bitmap: Bitmap) -> Unit) {
    GlideApp.with(this).asBitmap()
            .load(url)
            .placeholder(R.drawable.shape_default_rec_bg)
            .error(R.drawable.shape_default_rec_bg)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callback(resource)
                }
            })
}

fun ImageView.get(resourceID: Int?): GlideRequest<Drawable> = GlideApp.with(context).load(resourceID)
fun ImageView.get(url: String?): GlideRequest<Drawable> = GlideApp.with(context).load(url)
fun ImageView.get(drawable: Drawable?): GlideRequest<Drawable> = GlideApp.with(context).load(drawable)
