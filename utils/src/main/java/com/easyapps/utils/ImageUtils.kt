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
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream

object ImageUtils {

    fun ImageView.glideCenterCrop(url:String?,error:Int = R.drawable.ic_broken_image){
        if (url.isNullOrEmpty()) return

        val animPlaceholder = ContextCompat.getDrawable(context, R.drawable.loading_animation_shimmer) as AnimationDrawable?
        animPlaceholder?.start()
        scaleType = ImageView.ScaleType.CENTER_INSIDE

        Glide.with(context)
            .load(url)
            .placeholder(animPlaceholder)
            .error(error)
            .centerCrop()
            .into(this)
    }

    fun ImageView.glideFitCenter(url:String?,error:Int = R.drawable.ic_broken_image){
        if (url.isNullOrEmpty()) return

        val animPlaceholder = ContextCompat.getDrawable(context, R.drawable.loading_animation_shimmer) as AnimationDrawable?
        animPlaceholder?.start()
        scaleType = ImageView.ScaleType.CENTER_INSIDE

        Glide.with(context)
            .load(url)
            .placeholder(animPlaceholder)
            .error(error)
            .fitCenter()
            .into(this)
    }


    fun ImageView.glideCenterCropPlaceholder(url:String?,placeholder:Int ,error: Int= R.drawable.ic_broken_image){
        if (url.isNullOrEmpty()) return
        scaleType = ImageView.ScaleType.CENTER_CROP

        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(error)
            .centerCrop()
            .into(this)
    }

    fun ImageView.glideCenterInside(url:String?){
        if (url.isNullOrEmpty()) return

        val animPlaceholder = ContextCompat.getDrawable(context, R.drawable.loading_animation_shimmer) as AnimationDrawable?
        animPlaceholder?.start()

        Glide.with(context)
            .load(url)
            .placeholder(animPlaceholder)
            .error(R.drawable.ic_broken_image)
            .centerInside()
            .into(this)
    }

    fun ImageView.glideFitCenter(url:String?){
        if (url.isNullOrEmpty()) return

        val animPlaceholder = ContextCompat.getDrawable(context, R.drawable.loading_animation_shimmer) as AnimationDrawable?
        animPlaceholder?.start()

        Glide.with(context)
            .load(url)
            .placeholder(animPlaceholder)
            .error(R.drawable.ic_broken_image)
            .fitCenter()
            .into(this)
    }

    fun glideBitmap(context: Context, url: String?, onBitmapLoaded: (Bitmap?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onBitmapLoaded(null)
            return
        }

        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onBitmapLoaded(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    onBitmapLoaded(null)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    onBitmapLoaded(null)
                }
            })
    }

    fun loadBitmapCircleCrop(context: Context, url: String?, onBitmapLoaded: (Bitmap?) -> Unit) {
        if (url.isNullOrEmpty()) {
            onBitmapLoaded(null)
            return
        }
        val error = ContextCompat.getDrawable(context, R.drawable.ic_broken_image)?.toBitmap()

        Glide.with(context)
            .asBitmap()
            .load(url)
            .error(error)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onBitmapLoaded(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    onBitmapLoaded(error)
                }
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    onBitmapLoaded(error)
                }
            })
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