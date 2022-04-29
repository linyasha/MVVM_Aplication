package com.example.mvvm_aplication.presentation.authorization

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.marginTop
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_aplication.BuildConfig
import com.example.mvvm_aplication.R
import com.example.mvvm_aplication.databinding.FragmentLoginBinding
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserLoginInfo
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.presentation.keepAlive.KeepAliveActivity
import com.example.mvvm_aplication.utils.getDialog
import com.example.mvvm_aplication.utils.getJsonFileFromAssets
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val viewModel by viewModel<AuthorizationViewModel>()

    private val validationJson by lazy {
        JSONObject(requireActivity().getJsonFileFromAssets("validation.json"))
    }
    private val errorMessagesJson by lazy {
        JSONObject(requireActivity().getJsonFileFromAssets(
            "message_${if (Locale.getDefault().language == "ru") "ru" else "en"}.json")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding?.let {
            val isLogin = requireArguments().getBoolean(IS_LOGIN)
            with(it) {
                if (isLogin) {
                    name.text = getString(R.string.login)
                    title.text = getString(R.string.welcome_back)
                    subTitle.text = getString(R.string.please_enter_details)
                    userName.visibility = View.GONE
                    signIn.text = getString(R.string.login)
                }
                else {
                    it.name.text = getString(R.string.sign_up)
                    title.text = getString(R.string.lets_get_started)
                    subTitle.text = getString(R.string.latest_games)
                    forgotPassword.visibility = View.GONE
                    userName.visibility = View.VISIBLE
                    signIn.text = getString(R.string.sign_up)
                }
            }

            it.back.setOnClickListener {
                requireActivity().onBackPressed()
            }
            it.signIn.setOnClickListener {
                if (isLogin) login()
                else register()
            }
            setDoOnTextChanged(
                listView = listOf(
                    it.emailText to it.email,
                    it.passwordText to it.password,
                    it.userNameText to it.userName
                )
            )
            if (BuildConfig.DEBUG) {
                it.emailText.setText("arturlynko@gmail.com")
                it.passwordText.setText("Aa!11111")
            }
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun initViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.authStatus.collect { status ->
                    when (status) {
                        is Status.Success -> {
                            //TODO(Перебросить на основной экран)
                            withContext(Dispatchers.Main) {
                                showProgress(false)
                                startActivity(Intent(requireContext(), KeepAliveActivity::class.java))
                                requireActivity().finish()
                            }
                        }
                        is Status.Loading -> {
                            withContext(Dispatchers.Main) {
                                showProgress(true)
                            }
                        }
                        is Status.Failure -> {
                            withContext(Dispatchers.Main) {
                                showProgress(false)
                                requireActivity().getDialog(
                                    title = getString(R.string.error),
                                    message = status.exception?.message ?: getString(R.string.request_error_try_again)
                                        ?: getString(R.string.request_error_try_again),
                                    negativeButton = getString(R.string.cancel),
                                    background = ContextCompat.getDrawable(
                                        requireActivity(),
                                        R.drawable.background_rounded_dialog
                                    )
                                ).show()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun login() {
        val email = getIfValid(binding!!.email, "email")
            ?: return
        val password = getIfValid(binding!!.password, "password")
            ?: return
        viewModel.login(UserLoginInfo(email = email, password = password))
    }

    private fun register() {
        val userName = getIfValid(binding!!.userName, "user_name")
            ?: return
        val email = getIfValid(binding!!.email, "email")
            ?: return
        val password = getIfValid(binding!!.password, "password")
            ?: return
        viewModel.registration(
            UserRegisterInfo(
                email = email,
                password = password,
                userName = userName
            )
        )
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

    private fun showProgress(progress: Boolean) {
        (requireActivity() as AuthorizationActivity).showProgress(progress)
    }

    private fun setDoOnTextChanged(listView: List<Pair<TextInputEditText, TextInputLayout>>) {
        listView.forEach {
            it.first.doOnTextChanged { text, start, before, count -> it.second.error = null  }
        }
    }

    companion object {

        private const val IS_LOGIN = "isLogin"

        fun newInstance(isLogin: Boolean): LoginFragment {
           return LoginFragment().apply { arguments = bundleOf(IS_LOGIN to isLogin) }
        }
    }

}