package com.example.mvvm_aplication.presentation.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.example.mvvm_aplication.R

class ProgressDialog(context: Context) {

    private val dialog = AlertDialog.Builder(context)
        .setView(R.layout.layout_progress)
        .setCancelable(false)
        .create()
        .apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    fun show(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            try {
                dialog.dismiss()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

}