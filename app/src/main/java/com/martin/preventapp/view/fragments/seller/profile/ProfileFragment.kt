package com.martin.preventapp.view.fragments.seller.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.preventapp.controller.ProfileController
import com.martin.preventapp.controller.ProfileInterface
import com.martin.preventapp.databinding.FragmentProfileBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ProfileResponse
import com.martin.preventapp.view.activities.admin.MainAdminActivity
import com.martin.preventapp.view.fragments.login.LoginActivity

class ProfileFragment : Fragment(), ProfileInterface.View {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var profileFragment: ProfileFragment? = null
        @JvmStatic
        val instance: ProfileFragment?
            get() {
                if (profileFragment == null) {
                    profileFragment = ProfileFragment()
                }
                return profileFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProfileController.instance!!.getUserInfo()

        binding.logout.setOnClickListener {
            Application.clearTokenShared(requireContext())
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun showUserInfo(info: ProfileResponse) {
        binding.tvClient.text = info.userName
        binding.tvType.text = info.groupName
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}