package com.example.mvvm_aplication.presentation.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_aplication.R
import com.example.mvvm_aplication.databinding.ActivityMainBinding
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.presentation.common.ProgressDialog
import com.example.mvvm_aplication.utils.getJsonFileFromAssets
import com.example.mvvm_aplication.utils.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AuthorizationActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel by viewModel<AuthorizationViewModel>()

    private val validationJson by lazy {
        JSONObject(getJsonFileFromAssets("validation.json"))
    }
    private val errorMessagesJson by lazy {
        JSONObject(getJsonFileFromAssets(
            "message_${if (Locale.getDefault().language == "ru") "ru" else "en"}.json")
        )
    }
    lateinit var progressDialog: ProgressDialog
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
            setDoOnTextChanged(
                listView = listOf(
                    it.loginText to it.loginInput,
                    it.passwordText to it.passwordInput,
                    it.emailRegisterText to it.emailRegisterLayout,
                    it.passwordRegisterText to it.passwordRegisterLayout,
                    it.repeatPasswordRegisterText to it.repeatPasswordRegisterLayout,
                    it.userNameRegisterText to it.userNameRegisterLayout)
                )
            it.loginButton.setOnClickListener { view ->
                view.hideKeyboard()
                login()
            }
            it.registerButton.setOnClickListener { view ->
                view.hideKeyboard()
                register()
            }
            it.emailRegisterText.setText("arturlynko@gmail.com")
            it.passwordRegisterText.setText("Aa!11111")
            it.repeatPasswordRegisterText.setText("Aa!11111")
            it.userNameRegisterText.setText("Loghdfd")
        }

    }

    private fun login() {
        val login = getIfValid(binding!!.loginInput, "login")
            ?: return
        val password = getIfValid(binding!!.passwordInput, "password")
            ?: return
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.login(UserLoginInfo(email = login,
                password = password)).collect { status ->
                when (status) {
                    is Status.Success -> {
                        withContext(Dispatchers.Main) {
                            showProgress(false)
                        }
                    }
                    is Status.Loading -> {
                        withContext(Dispatchers.Main) {
                            showProgress(true)
                        }
                    }
                    is Status.Failure -> {
                        //TODO("Show error dialog")
                    }
                }
            }
        }
    }

    private fun register() {
        val email = getIfValid(binding!!.emailRegisterLayout, "email")
            ?: return
        val password = getIfValid(binding!!.passwordRegisterLayout, "password")
            ?: return
        val confirmPassword = getIfValid(binding!!.repeatPasswordRegisterLayout, "confirm_password")
            ?: return
        if (password != confirmPassword) {
            binding!!.repeatPasswordRegisterLayout.error = getString(R.string.passwords_do_not_match)
            return
        }
        val userName = getIfValid(binding!!.userNameRegisterLayout, "user_name")
            ?: return
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.registration(UserRegisterInfo(
                email = email,
                password = password,
                userName = userName)).collect { status ->
                  when (status) {
                      is Status.Success -> {
                          withContext(Dispatchers.Main) {
                              showProgress(false)
                          }
                      }
                      is Status.Loading -> {
                          withContext(Dispatchers.Main) {
                              showProgress(true)
                          }
                      }
                      is Status.Failure -> {
                        //TODO("Show error dialog")
                      }
                  }
            }
        }
    }

    private fun getIfValid(inputLayout: TextInputLayout, jsonField: String): String? {
        val text = inputLayout.editText!!.text!!.trim().toString()
        if (text.isEmpty()) {
            inputLayout.error = getString(R.string.field_is_required)
            return null
        }
        val json = validationJson.getJSONObject(jsonField)
        return when {
            json.optString("reg_exp").takeIf { it.isNotEmpty() }?.toRegex()?.matches(text) != false -> {
                text
            }
            else -> {
                inputLayout.error = errorMessagesJson.getString(json.getString("error_code"))
                null
            }
        }
    }

    private fun setDoOnTextChanged(listView: List<Pair<TextInputEditText, TextInputLayout>>) {
        listView.forEach {
            it.first.doOnTextChanged { text, start, before, count -> it.second.error = null  }
        }
    }

    private fun showProgress(show: Boolean) {
        progressDialog.show(show)
    }
}