package com.martin.preventapp.view.fragments.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    @JvmField
    var view: View? = null

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var registerFragment: RegisterFragment? = null
        @JvmStatic
        val instance: RegisterFragment?
            get() {
                if (registerFragment == null) {
                    registerFragment = RegisterFragment()
                }
                return registerFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            binding.username.setText("")
            binding.password.setText("")
            binding.confirmPassword.setText("")
            LoginController.instance!!.goToLogin()
        }

        binding.btnRegisterUser.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if(isValidEmail(username) && password == confirmPassword){
                LoginController.instance!!.register(username, password)
            }else{
                if(!isValidEmail(username)){
                    Toast.makeText(requireContext(), "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}