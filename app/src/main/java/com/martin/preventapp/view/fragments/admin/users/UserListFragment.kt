package com.martin.preventapp.view.fragments.admin.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentCreateUserBinding
import com.martin.preventapp.databinding.FragmentUserListBinding
import com.martin.preventapp.view.adapter.OrderAdapter
import com.martin.preventapp.view.adapter.UsersAdapter
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.User

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            User("Preventista 1", "preventista1@gmail.com", "Preventista"),
            User("Preventista 2", "preventista2@gmail.com", "Preventista"),
            User("Repartidor 1", "repartidor1@gmail.com", "Repartidor")
        )

        val adapter = UsersAdapter(requireContext(), items)
        binding.usersList.adapter = adapter

        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }
    }
}