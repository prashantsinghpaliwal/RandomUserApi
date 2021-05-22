package com.bigsteptech.deazzle.common

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bigsteptech.deazzle.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.util.*


@BindingAdapter(value = ["setRoundedImageUrl"])
fun bindRoundedImageUrl(view: ImageView, url: String?) {
    if (url != null && url.isNotBlank()) {
        val randomColor = getRandomColor()
        val drawable = ContextCompat.getDrawable(view.context, R.drawable.photos_round_shape)
        drawable!!.mutate().setColorFilter(randomColor, PorterDuff.Mode.SRC_ATOP)
        view.setBackground(drawable)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setClipToOutline(true)
        }

        Glide.with(view.context)
            .load(url).addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v("GlideError", "Exception :${e!!.message.toString()}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.v("GlideError", "onResourceReady")
                    return false
                }

            }).centerCrop()
            .into(view)
    }
}

@BindingAdapter(value = ["setImageUrl"])
fun bindImageUrl(view: ImageView, url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(view.context)
            .load(url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(view)
    }
}

fun getRandomColor(): Int {
    val rand = Random()
    val randomColor: Int
    val r = rand.nextInt(256)
    val g = rand.nextInt(256)
    val b = rand.nextInt(256)
    randomColor = Color.argb((255 * 0.2).toInt(), r, g, b)
    return randomColor
}