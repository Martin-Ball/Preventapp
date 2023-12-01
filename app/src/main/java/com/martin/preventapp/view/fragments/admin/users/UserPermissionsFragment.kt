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

        val adapter = UserManagerController.instance!!.getUserToModify()?.permissions?.let {
            PermissionsAdapter(requireContext(), R.layout.item_checkbox_permissions,
                it
            )
        }

        binding.listPermissionsCheckbox.adapter = adapter

        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }

        binding.btnModifyPermissions.setOnClickListener {
            val list = adapter?.getUpdatedPermissionsList()
            if(!list.isNullOrEmpty()) {
                UserManagerController.instance!!.updatePermissionsState(list)
            }else{
                UserManagerController.instance!!.showToast("Error al obtener la lista")
            }
        }

        return binding.root
    }
}