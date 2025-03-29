package com.easyapps.utils

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt


object WindowUtils {

    fun View.setupTopInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemBars.top)
            insets
        }
    }


    fun View.setupBottomInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = systemBars.bottom)
            insets
        }
    }

    fun setupInsets(top: View,bottom:View) {
        ViewCompat.setOnApplyWindowInsetsListener(top) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            top.updatePadding(top = systemBars.top)
            bottom.updatePadding(bottom = systemBars.bottom)
            insets
        }
    }

    fun View.setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemBars.top,bottom = systemBars.bottom)
            insets
        }
    }


    fun Int.dpToPx(context: Context) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics).roundToInt()
    fun  Int.dp(context: Context) = (this * context.resources.displayMetrics.density).toInt()


    fun Activity.setLightSystemBars(statusBarLight: Boolean) {
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = statusBarLight
        controller.isAppearanceLightNavigationBars = statusBarLight
    }

    fun Fragment.setLightSystemBars(statusBarLight: Boolean) {
        val controller = WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView)
        controller.isAppearanceLightStatusBars =statusBarLight
        controller.isAppearanceLightNavigationBars = statusBarLight
    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    fun getStatusBarHeight(context: Context):Int{
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) result = context.resources.getDimensionPixelSize(resourceId)
        return result
    }

    fun Activity.hideSystemBars() = WindowCompat.getInsetsController(window, window.decorView).apply {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }


    @SuppressLint("ClickableViewAccessibility")
    fun View.setInteractiveClick(unit: () -> Unit = {}) {
        val scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", 0.9f).apply { duration = 100 }
        val scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", 0.9f).apply { duration = 100 }
        val scaleUpX = ObjectAnimator.ofFloat(this, "scaleX", 1f).apply { duration = 300 }
        val scaleUpY = ObjectAnimator.ofFloat(this, "scaleY", 1f).apply { duration = 300 }

        this.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    scaleDownX.start()
                    scaleDownY.start()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    scaleUpX.start()
                    scaleUpY.start()
                    unit.invoke()
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    scaleUpX.start()
                    scaleUpY.start()
                    true
                }
                else -> false
            }
        }
    }

    /* USING IN SINGLE ACTIVITY
    override fun attachBaseContext(newBase: Context) {
        val contextWithoutFontScale = newBase.disableFontScaling()
        super.attachBaseContext(contextWithoutFontScale)
    }*/
    fun Context.disableFontScaling(fontScale: Float = 1.0f): Context {
        val config = Configuration(resources.configuration)
        config.fontScale = fontScale
        return createConfigurationContext(config)
    }




}