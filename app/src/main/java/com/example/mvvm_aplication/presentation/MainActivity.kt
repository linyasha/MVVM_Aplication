package com.example.mvvm_aplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.mvvm_aplication.R
import com.example.mvvm_aplication.data.repository.UserRepositoryImpl
import com.example.mvvm_aplication.data.storage.UserStorageImpl
import com.example.mvvm_aplication.databinding.ActivityMainBinding
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.domain.usecase.LoginUseCase
import com.example.mvvm_aplication.domain.usecase.RegisterUseCase
import com.example.mvvm_aplication.utils.getJsonFileFromAssets
import com.example.mvvm_aplication.utils.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val validationJson by lazy {
        JSONObject(getJsonFileFromAssets("validation.json"))
    }
    private val errorMessagesJson by lazy {
        JSONObject(getJsonFileFromAssets(
            "message_${if (Locale.getDefault().language == "ru") "ru" else "en"}.json")
        )
    }

    private val loginUseCase = LoginUseCase(userRepository = UserRepositoryImpl(UserStorageImpl()))
    private val registerUseCase = RegisterUseCase(userRepository = UserRepositoryImpl(UserStorageImpl()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initUi()
    }

    private fun initUi() {
        binding?.let { it ->
            setDoOnTextChanged(
                listView = listOf(
                    it.loginText to it.loginInput,
                    it.passwordText to it.passwordInput,
                    it.loginRegisterText to it.loginRegisterLayout,
                    it.passwordRegisterText to it.passwordRegisterLayout,
                    it.repeatPasswordRegisterText to it.repeatPasswordRegisterLayout,
                    it.emailRegisterText to it.emailRegisterLayout,
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
        }

    }

    private fun login() {
        val login = getIfValid(binding!!.loginInput, "login")
            ?: return
        val password = getIfValid(binding!!.passwordInput, "password")
            ?: return
        loginUseCase.execute(UserLoginInfo(login = login, password = password))
    }

    private fun register() {
        val login = getIfValid(binding!!.loginRegisterLayout, "login")
            ?: return
        val password = getIfValid(binding!!.passwordRegisterLayout, "password")
            ?: return
        val confirmPassword = getIfValid(binding!!.repeatPasswordRegisterLayout, "confirm_password")
            ?: return
        if (password != confirmPassword) {
            binding!!.repeatPasswordRegisterLayout.error = getString(R.string.passwords_do_not_match)
            return
        }
        val email = getIfValid(binding!!.emailRegisterLayout, "email")
            ?: return
        val userName = getIfValid(binding!!.userNameRegisterLayout, "user_name")
            ?: return
        registerUseCase.execute(
            UserRegisterInfo(
                login = login,
                password = password,
                email = email,
                userName = userName))
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
}