package com.martin.preventapp.view.fragments.admin.users

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentUserBinding
import com.martin.preventapp.view.fragments.admin.list.ListSelectionActivity

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var userFragment: UserFragment? = null
        @JvmStatic
        val instance: UserFragment?
            get() {
                if (userFragment == null) {
                    userFragment = UserFragment()
                }
                return userFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewUser.setOnClickListener {
            val intent = Intent(requireContext(), UserManagerActivity::class.java)
            intent.putExtra("createUser", true)
            requireActivity().startActivity(intent)
        }

        binding.btnViewUser.setOnClickListener {
            val intent = Intent(requireContext(), UserManagerActivity::class.java)
            intent.putExtra("createUser", false)
            requireActivity().startActivity(intent)
        }
    }
}