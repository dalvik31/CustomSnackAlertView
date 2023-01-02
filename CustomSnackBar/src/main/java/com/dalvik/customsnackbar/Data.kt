package com.dalvik.customsnackbar

import android.view.Gravity
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

class Data constructor(
    var leftIcon: Int? = null,
    var rightIcon: Int? = null,
    var showOrientation: Int = Gravity.TOP,
    @StringRes var message: Int? = android.R.string.untitled,
    var customMessage: String? = null,
    @ColorRes var backgroundColor: Int = android.R.color.white,
    @ColorRes var textColor: Int = android.R.color.black,
    @ColorRes var rightIconTint: Int = android.R.color.black,
    @ColorRes val leftIconTint: Int = android.R.color.black,
    var onClick: (() -> Unit)? = null
)