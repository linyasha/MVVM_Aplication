package com.example.mvvm_aplication.presentation.authorization

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_aplication.R
import com.example.mvvm_aplication.databinding.FragmentRegistrationBinding
import com.example.mvvm_aplication.domain.model.Status
import com.example.mvvm_aplication.domain.model.UserRegisterInfo
import com.example.mvvm_aplication.utils.getDialog
import com.example.mvvm_aplication.utils.getJsonFileFromAssets
import com.example.mvvm_aplication.utils.hideKeyboard
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class RegisterFragment : Fragment() {
/*
    private var binding: FragmentRegistrationBinding? = null
    private val viewModel by viewModel<AuthorizationViewModel>()

    private val validationJson by lazy {
        JSONObject(requireActivity().getJsonFileFromAssets("validation.json"))
    }
    private val errorMessagesJson by lazy {
        JSONObject(
            requireActivity().getJsonFileFromAssets(
                "message_${if (Locale.getDefault().language == "ru") "ru" else "en"}.json"
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUi()
    }

    private fun initUi() {
        binding?.let {
            it.back.setOnClickListener {
                requireActivity().onBackPressed()
                //TODO("Add ripple effect on press")_
            }
            it.signUp.setOnClickListener {
                view?.hideKeyboard()
                register()
            }

            setDoOnTextChanged(
                listView = listOf(
                    it.emailText to it.email,
                    it.passwordText to it.password,
                    it.userNameText to it.userName
                )
            )

            it.userNameText.setText("Artur Lynko")
            it.emailText.setText("arturlynko@gmail.com")
            it.passwordText.setText("Aa!11111")
        }
    }








 */

}