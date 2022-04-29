package com.example.mvvm_aplication.presentation

import android.content.Context
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvvm_aplication.presentation.common.ProgressDialog

abstract class BaseActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
        private set


    override fun attachBaseContext(base: Context) {
//        val language =
//            base.defaultSharedPreferences.getString(Prefs.LANGUAGE, Locale.getDefault().language)
//        val locale = Locale(language)
//        val configuration = base.resources.configuration
//        configuration.setLocale(locale)
//        LocaleList.setDefault(LocaleList(locale))
//        super.attachBaseContext(base.createConfigurationContext(configuration))
        //TODO("Add change languge")
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        progressDialog = ProgressDialog(this)
    }

    @CallSuper
    override fun onStop() {
        progressDialog.show(false)
        super.onStop()
    }

    open fun pushFragment(fragment: Fragment) {
    }

    open fun popFragment() {
    }
}
