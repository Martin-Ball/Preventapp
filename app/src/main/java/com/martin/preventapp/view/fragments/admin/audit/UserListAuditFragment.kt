package com.martin.preventapp.view.fragments.admin.audit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentUserListAuditBinding
import com.martin.preventapp.databinding.FragmentUserListBinding
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.adapter.UsersAdapter
import com.martin.preventapp.view.fragments.admin.users.UserListFragment

class UserListAuditFragment : Fragment() {
    private var _binding: FragmentUserListAuditBinding? = null
    private val binding get() = _binding!!

    private var users:List<UserModel> = mutableListOf()

    companion object {
        private var userListFragment: UserListAuditFragment? = null
        @JvmStatic
        val instance: UserListAuditFragment?
            get() {
                if (userListFragment == null) {
                    userListFragment = UserListAuditFragment()
                }
                return userListFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListAuditBinding.inflate(inflater)
        AuditController.instance!!.getUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            AuditController.instance!!.goToMain()
        }
    }

    fun setUsers(users: List<UserModel>){
        this.users = users
        val adapter = UsersAdapter(requireContext(), users, AuditController.instance!!)
        binding.usersList.adapter = adapter
    }
}