package com.easyapps.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream

object ImageUtils {


    fun ImageView.glideCenterCrop(url: String?, error: Int? = null, placeholder: Int? = null) {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable?
            animPlaceholder?.start()

            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)
            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            if (animPlaceholder != null) setImageDrawable(animPlaceholder) else setImageDrawable(withoutAnimation)
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable(url,originalScaleType) {
                if (it == null) {
                    setImageResource(error ?: R.drawable.ic_broken_image)
                } else {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setImageDrawable(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun ImageView.glideFitCenter(url: String?, error: Int? = null, placeholder: Int? = null)  {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable
            animPlaceholder?.start()
            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)

            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable( url,originalScaleType) {
                if (it == null) {
                    setImageResource(error ?: R.drawable.ic_broken_image)
                } else {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                    setImageDrawable(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }



    fun ImageView.glideFitEnd(url: String?, error: Int? = null, placeholder: Int? = null) {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable?
            animPlaceholder?.start()

            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)
            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            if (animPlaceholder != null) setImageDrawable(animPlaceholder) else setImageDrawable(withoutAnimation)
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable(url,originalScaleType) {
                if (it == null) {
                    setImageResource(error ?: R.drawable.ic_broken_image)
                } else {
                    scaleType = ImageView.ScaleType.FIT_END
                    setImageDrawable(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }



    fun ImageView.glideCenterInside(url: String?, error: Int? = null, placeholder: Int? = null)  {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable
            animPlaceholder?.start()
            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)

            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable( url,originalScaleType) {
                if (it == null) {
                    setImageResource(error ?: R.drawable.ic_broken_image)
                } else {
                    scaleType = ImageView.ScaleType.CENTER_INSIDE
                    setImageDrawable(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }



    fun ImageView.glide(url: String?, error: Int? = null, placeholder: Int? = null) {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable
            animPlaceholder?.start()
            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)

            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable( url,originalScaleType) {
                if (it == null) setImageResource(error ?: R.drawable.ic_broken_image)
                else setImageDrawable(it)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    fun ImageView.glideFitXY(url: String?, error: Int? = null, placeholder: Int? = null)  {
        try {
            val animPlaceholder = ContextCompat.getDrawable(context, placeholder ?: R.drawable.loading_animation_shimmer) as? AnimationDrawable
            animPlaceholder?.start()
            val withoutAnimation = ContextCompat.getDrawable(context,placeholder ?: R.drawable.placeholder)

            val originalScaleType =  scaleType
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageDrawable(animPlaceholder ?: withoutAnimation)

            glideDrawable( url,originalScaleType) {
                if (it == null) {
                    setImageResource(error ?: R.drawable.ic_broken_image)
                } else {
                    scaleType = ImageView.ScaleType.FIT_XY
                    setImageDrawable(it)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


    private fun ImageView.glideDrawable(url: String?,originalScale:ImageView.ScaleType,onFinish: (Drawable?) -> Unit) {
        try {
            Glide.with(context)
                .load(url)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        scaleType = originalScale
                        setImageDrawable(null)
                        onFinish.invoke(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        scaleType = originalScale
                        setImageDrawable(null)
                        onFinish.invoke(null)
                    }
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        scaleType = originalScale
                        setImageDrawable(null)
                        onFinish.invoke(null)
                    }
                })
        }catch (e:Exception){
            onFinish.invoke(null)
            setImageDrawable(null)
            e.printStackTrace()
        }
    }

    fun glideDrawable(context: Context, url: String?, onFinish: (Drawable?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onFinish(null)
            return
        }

        try {
            Glide.with(context)
                .asDrawable()
                .load(url)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) = onFinish(resource)
                    override fun onLoadCleared(placeholder: Drawable?) =  onFinish(null)
                    override fun onLoadFailed(errorDrawable: Drawable?) = onFinish(null)
                })
        }catch (e:Exception){
            onFinish(null)
        }
    }

    fun glideBitmap(context: Context, url: String?, onFinish: (Bitmap?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onFinish(null)
            return
        }

        try {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) = onFinish(resource)
                    override fun onLoadCleared(placeholder: Drawable?) =  onFinish(null)
                    override fun onLoadFailed(errorDrawable: Drawable?) = onFinish(null)
                })
        }catch (e:Exception){
            onFinish(null)
        }

    }

    fun glideFile(context: Context, url: String?, onFinish: (File?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onFinish(null)
            return
        }

        try {
            Glide.with(context)
                .asFile()
                .load(url)
                .into(object : CustomTarget<File>() {
                    override fun onResourceReady(resource: File, transition: Transition<in File>?) = onFinish(resource)
                    override fun onLoadCleared(placeholder: Drawable?) =  onFinish(null)
                    override fun onLoadFailed(errorDrawable: Drawable?) = onFinish(null)
                })
        }catch (e:Exception){
            onFinish(null)
        }
    }


    fun glideGif(context: Context, url: String?, onFinish: (GifDrawable?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onFinish(null)
            return
        }
        try {
            Glide.with(context)
                .asGif()
                .load(url)
                .into(object : CustomTarget<GifDrawable>() {
                    override fun onResourceReady(resource: GifDrawable, transition: Transition<in GifDrawable>?) = onFinish(resource)
                    override fun onLoadCleared(placeholder: Drawable?) =  onFinish(null)
                    override fun onLoadFailed(errorDrawable: Drawable?) = onFinish(null)
                })
        }catch (e:Exception){
            onFinish(null)
        }
    }



    fun View.screenShot(): Bitmap {
        val bitmap = createBitmap(width, height)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }


    fun Bitmap.share(context: Context,text:String) {
        val file = File(context.externalCacheDir, "screenshot_${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { out -> this.compress(Bitmap.CompressFormat.JPEG, 100, out) }
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, text))
    }
    

}