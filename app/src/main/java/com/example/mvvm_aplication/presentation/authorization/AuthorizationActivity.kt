package com.example.mvvm_aplication.presentation.authorization

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvvm_aplication.R
import com.example.mvvm_aplication.databinding.AuthorizationActivityBinding
import com.example.mvvm_aplication.presentation.BaseActivity
import com.example.mvvm_aplication.presentation.common.ProgressDialog

class AuthorizationActivity : AppCompatActivity() {

    private var binding: AuthorizationActivityBinding? = null

    lateinit var progressDialog: ProgressDialog
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthorizationActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initUi()
    }

    override fun onStart() {
        super.onStart()
        progressDialog = ProgressDialog(this)
    }

    override fun onStop() {
        progressDialog.show(false)
        super.onStop()
    }

    private fun initUi() {
        binding?.let { it ->
            it.signUp.setOnClickListener {
                pushFragment(LoginFragment.newInstance(false))
            }
            it.login.setOnClickListener {
                pushFragment(LoginFragment.newInstance(true))
            }
            it.google.setOnClickListener {
                //TODO(Add register by Google)
            }
            it.facebook.setOnClickListener {
                //TODO(Add register by Facebook)
            }
            it.apple.setOnClickListener {
                //TODO(Add register by Apple)
            }
        }
    }

     fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun showProgress(show: Boolean) {
        progressDialog.show(show)
    }
}