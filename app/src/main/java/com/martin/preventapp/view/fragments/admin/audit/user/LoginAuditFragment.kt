package com.martin.preventapp.view.fragments.admin.audit.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentAuditOrdersBinding
import com.martin.preventapp.databinding.FragmentLoginAuditBinding
import com.martin.preventapp.view.fragments.admin.audit.orders.AuditOrdersFragment

class LoginAuditFragment : Fragment() {
    private var _binding: FragmentLoginAuditBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var loginAuditFragment: LoginAuditFragment? = null

        @JvmStatic
        fun newInstance(loginsList: List<String>): LoginAuditFragment {
            if (loginAuditFragment == null) {
                loginAuditFragment = LoginAuditFragment()
            }
            val args = Bundle().apply {
                putStringArrayList("loginsList", ArrayList(loginsList))
            }
            loginAuditFragment?.arguments = args

            return loginAuditFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginAuditBinding.inflate(inflater)
        val loginsList = arguments?.getStringArrayList("loginsList")
        if (loginsList != null) {
            showLogins(loginsList)
        }
        return binding.root
    }

    private fun showLogins(list: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_rol, list)
        binding.loginList.adapter = adapter
    }
}
