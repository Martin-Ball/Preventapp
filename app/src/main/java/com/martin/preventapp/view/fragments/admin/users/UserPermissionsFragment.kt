package com.martin.preventapp.view.fragments.admin.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentDetailUserBinding
import com.martin.preventapp.databinding.FragmentUserPermissionsBinding
import com.martin.preventapp.view.adapter.PermissionsAdapter
import com.martin.preventapp.view.entities.Permission

class UserPermissionsFragment : Fragment() {

    private var _binding: FragmentUserPermissionsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var userPermissionsFragment: UserPermissionsFragment? = null
        @JvmStatic
        val instance: UserPermissionsFragment?
            get() {
                if (userPermissionsFragment == null) {
                    userPermissionsFragment = UserPermissionsFragment()
                }
                return userPermissionsFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPermissionsBinding.inflate(inflater)

        val itemList = listOf(
            Permission("Permiso 1", true),
            Permission("Permiso 2", false),
            Permission("Permiso 3", true),
            Permission("Permiso 4", false),
        )

        // Adaptador para el ListView
        val adapter = PermissionsAdapter(requireContext(), R.layout.item_checkbox_permissions, itemList)
        binding.listPermissionsCheckbox.adapter = adapter

        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }

        binding.btnModifyPermissions.setOnClickListener {
            Toast.makeText(requireContext(), adapter.getUpdatedPermissionsList().toString(), Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}