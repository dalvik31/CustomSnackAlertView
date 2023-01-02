package com.dalvik.customsnackbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.dalvik.customsnackbar.databinding.AlertSnackViewBinding

class SnackAlertView(context: Context?) : LinearLayout(context) {

    companion object {
        private const val ANIMATION_VALUE = 1000L
    }


    private var _binding: AlertSnackViewBinding? = null
    private val binding: AlertSnackViewBinding
        get() = _binding ?: throw RuntimeException("Binding must be non-null")
    private var data: Data? = null


    fun load(@StringRes message: Int? = null, data: Data? = null) {
        this.data = data ?: Data()
        message?.let {
            this.data!!.message = it
        }
        loadView()
    }

    private fun loadView() {
        inflate()
        loadMessage()
        setOkClickListener()
        show()
    }

    private fun inflate(): View {
        val view = getActivity()?.findViewById<View>(R.id.loaderSnackAlertView)
        if (view != null) {
            _binding = AlertSnackViewBinding.bind(view)
            return view
        }
        val root = getActivity()?.findViewById<FrameLayout>(android.R.id.content)
        _binding = AlertSnackViewBinding.inflate(LayoutInflater.from(context), root, true)
        return requireNotNull(_binding).root
    }

    private fun loadMessage() {
        data?.let { currentData ->

            binding.alertSnackViewHeaderBack.backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    androidx.core.content.ContextCompat.getColor(
                        context,
                        currentData.backgroundColor
                    )
                )


            (binding.loaderSnackAlertView.layoutParams as FrameLayout.LayoutParams).gravity =
                currentData.showOrientation

            currentData.leftIcon?.let {
                binding.leftIcon.isVisible = true
                binding.leftIcon.setImageResource(it)
            } ?: run { binding.leftIcon.isVisible = false }

            currentData.rightIcon?.let {
                binding.rightIcon.isVisible = true
                binding.rightIcon.setImageResource(it)
            } ?: run { binding.rightIcon.isVisible = false }


            currentData.customMessage?.let {
                binding.message.text = it
                binding.message.isVisible = true
            } ?: kotlin.run {
                currentData.message?.let {
                    binding.message.setText(it)
                    binding.message.isVisible = true
                }
            }



            binding.message.setTextColor(
                android.content.res.ColorStateList.valueOf(
                    androidx.core.content.ContextCompat.getColor(
                        context,
                        currentData.textColor
                    )
                )
            )


            binding.leftIcon.imageTintList = (android.content.res.ColorStateList.valueOf(
                androidx.core.content.ContextCompat.getColor(
                    context,
                    currentData.leftIconTint
                )
            ))


            binding.rightIcon.imageTintList = (android.content.res.ColorStateList.valueOf(
                androidx.core.content.ContextCompat.getColor(
                    context,
                    currentData.rightIconTint
                )
            ))

        }


    }

    private fun setOkClickListener(
    ) {
        binding.root.setOnClickListener {
            data?.let { currentData ->
                currentData.onClick?.invoke()
            }
        }
    }


    private fun show() {
        inflate()
        val animation: Animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                loadAutoClose()
            }

            override fun onAnimationStart(p0: Animation?) {
                visibility = View.VISIBLE
            }
        })
        binding.loaderSnackAlertView.startAnimation(animation)
    }


    private fun loadAutoClose() {
        binding.root.postDelayed({
            hide()
        }, ANIMATION_VALUE)
    }


    private fun hide() {
        val animation: Animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                visibility = View.GONE
                hideView()
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        binding.loaderSnackAlertView.startAnimation(animation)
    }


    private fun hideView() {
        try {
            val root = getActivity()?.findViewById<FrameLayout>(android.R.id.content)
            val view = root?.findViewById<LinearLayout>(R.id.loaderSnackAlertView)
            root?.removeView(view)
            _binding = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}