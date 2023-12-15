package com.martin.preventapp.view.fragments.admin.audit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.admin.interfaces.AuditInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.ActivityUserManagerBinding
import com.martin.preventapp.view.fragments.admin.users.CreateUserFragment
import com.martin.preventapp.view.fragments.admin.users.UserListFragment

class AuditActivity : AppCompatActivity(), AuditInterface.View {
    private lateinit var binding: ActivityUserManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagerBinding.inflate(layoutInflater);
        setContentView(binding.root)

        AuditController.instance!!.setContext(this)
        AuditController.instance!!.setView(this)

        val bundle = intent.extras
        if (bundle != null) {

            when(bundle.getString("audit_type", "")){
                "User" -> showFragment(UserListAuditFragment.instance!!)
                "Price" -> showFragment(UserListAuditFragment.instance!!)
                "Client" -> showFragment(UserListAuditFragment.instance!!)
                "Orders" -> showFragment(UserListAuditFragment.instance!!)
            }
        }
    }

    override fun goToMain() {
        finish()
    }

    override fun showFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}