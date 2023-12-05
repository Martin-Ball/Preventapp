package com.martin.preventapp.view.fragments.admin.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentUserListBinding
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.adapter.UsersAdapter

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private var users:List<UserModel> = mutableListOf()

    companion object {
        private var userListFragment: UserListFragment? = null
        @JvmStatic
        val instance: UserListFragment?
            get() {
                if (userListFragment == null) {
                    userListFragment = UserListFragment()
                }
                return userListFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater)
        UserManagerController.instance!!.getUsers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }
    }

    fun setUsers(users: List<UserModel>){
        this.users = users
        val adapter = UsersAdapter(requireContext(), users)
        binding.usersList.adapter = adapter
    }

}