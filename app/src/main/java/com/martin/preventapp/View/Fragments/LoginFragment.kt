package com.martin.preventapp.View.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.Controller.Interfaces.LoginInterfaces
import com.martin.preventapp.Controller.LoginController
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    @JvmField
    var context: Activity? = null
    @JvmField
    var view: View? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var loginFragment: LoginFragment? = null
        @JvmStatic
        val instance: LoginFragment?
            get() {
                if (loginFragment == null) {
                    loginFragment = LoginFragment()
                }
                return loginFragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener{
            LoginController.instance!!.goMain()
        }
    }
}