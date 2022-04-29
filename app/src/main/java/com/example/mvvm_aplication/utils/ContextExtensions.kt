package com.example.mvvm_aplication.utils

import android.content.Context
import android.content.DialogInterface.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.mvvm_aplication.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.getJsonFileFromAssets(name: String): String {
    return assets.open(name).bufferedReader().use { it.readText() }
}

fun Context.getDialog(
    title: String,
    message: String,
    negativeButton: String,
    negativeClickListener: OnClickListener? = null,
    positiveButton: String = "",
    positiveClickListener: OnClickListener? = null,
    background: Drawable? = null): MaterialAlertDialogBuilder {

    return MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeButton, negativeClickListener)
        .setPositiveButton(positiveButton, positiveClickListener)
        .setBackground(background)
}
