package com.dalvik.snackbar

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.dalvik.customsnackbar.Data
import com.dalvik.customsnackbar.SnackAlertView

class MainActivity : AppCompatActivity() {


    private val snackView: SnackAlertView by lazy { SnackAlertView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snackView.load(
            data = Data(
                backgroundColor = android.R.color.holo_red_light,
                textColor = android.R.color.white,
                message = R.string.app_name,
                leftIconTint = android.R.color.white,
                rightIconTint = android.R.color.white,
                rightIcon = com.dalvik.customsnackbar.R.drawable.ic_cancel,
                leftIcon = com.dalvik.customsnackbar.R.drawable.ic_cancel,
                showOrientation = Gravity.RIGHT
            )
        )

        //snackView.load()

    }
}