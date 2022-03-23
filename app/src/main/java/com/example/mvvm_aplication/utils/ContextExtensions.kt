package com.example.mvvm_aplication.utils

import android.content.Context

fun Context.getJsonFileFromAssets(name: String): String {
    return assets.open(name).bufferedReader().use { it.readText() }
}
