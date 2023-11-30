package com.martin.preventapp.view.fragments.admin.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.UserManagerInterface
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.ActivityUserManagerBinding
import com.martin.preventapp.view.activities.admin.MainAdminActivity

class UserManagerActivity : AppCompatActivity(), UserManagerInterface.View {
    private lateinit var binding: ActivityUserManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagerBinding.inflate(layoutInflater);
        setContentView(binding.root)

        UserManagerController.instance!!.setContext(this)
        UserManagerController.instance!!.setView(this)

        if(intent.getBooleanExtra("createUser", true)){
            showFragment(CreateUserFragment.instance!!)
        }else{
            UserManagerController.instance!!.getUsers()
            showFragment(UserListFragment.instance!!)
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