package com.martin.preventapp.view.fragments.admin.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentCreateUserBinding
import com.martin.preventapp.databinding.FragmentUserBinding
import com.martin.preventapp.model.entities.UserToModify

class CreateUserFragment : Fragment() {
    private var _binding: FragmentCreateUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var createUserFragment: CreateUserFragment? = null
        @JvmStatic
        val instance: CreateUserFragment?
            get() {
                if (createUserFragment == null) {
                    createUserFragment = CreateUserFragment()
                }
                return createUserFragment
            }
    }

    private var selectedRol: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateUserBinding.inflate(inflater)

        val user = UserManagerController.instance!!.getUserToModify()

        val roles = arrayOf("Administrador", "Preventista", "Repartidor")

        val adapter = ArrayAdapter(requireContext(), R.layout.item_rol, roles)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerRol.adapter = adapter

        if(user == null){
            selectedRol = roles[0]

            binding.btnRegisterUser.setOnClickListener {
                val userName = binding.username.text.toString()
                val password = binding.password.text.toString()
                val confirmPassword = binding.confirmPassword.text.toString()

                if (userName.isNotEmpty() &&
                    password.isNotEmpty() &&
                    confirmPassword.isNotEmpty() &&
                    password == confirmPassword &&
                    selectedRol.isNotEmpty()) {
                    Toast.makeText(requireContext(), "Usuario registrado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "Los datos ingresados son incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            binding.username.setText(user.username)
            binding.username.isEnabled = false
            binding.btnRegisterUser.isVisible = false
            binding.btnModifyUser.isVisible = true

            selectedRol = roles[roles.indexOf(user.groupName)]
            binding.spinnerRol.setSelection(roles.indexOf(user.groupName))
        }

        binding.spinnerRol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedRol = roles[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.btnRegisterUser.setOnClickListener {
            val userName = binding.username.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if(password == confirmPassword){
                UserManagerController.instance!!.createUser(userName, password, selectedRol)
                UserManagerController.instance!!.goToMain()
                binding.username.setText("")
                binding.password.setText("")
                binding.confirmPassword.setText("")
            }else{
                UserManagerController.instance!!.showToast("Las contraseñas no coinciden")
            }
        }

        if(binding.btnModifyUser.isVisible){
            binding.btnModifyUser.setOnClickListener {
                val userName = binding.username.text.toString()
                val password = binding.password.text.toString()
                val confirmPassword = binding.confirmPassword.text.toString()

                if(password == confirmPassword){
                    UserManagerController.instance!!.updateUser(UserToModify(
                        userName, password, selectedRol
                    ))
                }else{
                    UserManagerController.instance!!.showToast("Las contraseñas no coinciden")
                }
            }
        }

        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }

        return binding.root
    }
}