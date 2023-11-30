package com.martin.preventapp.view.fragments.admin.users

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.databinding.FragmentDetailUserBinding
import com.martin.preventapp.databinding.FragmentUserListBinding
import com.martin.preventapp.view.adapter.UsersAdapter
import com.martin.preventapp.view.entities.User

class DetailUserFragment : Fragment() {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var detailUserFragment: DetailUserFragment? = null
        @JvmStatic
        val instance: DetailUserFragment?
            get() {
                if (detailUserFragment == null) {
                    detailUserFragment = DetailUserFragment()
                }
                return detailUserFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = UserManagerController.instance!!.getUserToModify()

        binding.tvUser.text = user?.username
        binding.tvType.text = user?.groupName

        binding.btnModifyUser.setOnClickListener {
            UserManagerController.instance!!.showFragment(CreateUserFragment.instance!!)
        }

        binding.btnPermissionsUser.setOnClickListener {
            UserManagerController.instance!!.showFragment(UserPermissionsFragment.instance!!)
        }

        binding.btnDeleteUser.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())

            alertDialogBuilder.setTitle("Eliminar usuario")
            alertDialogBuilder.setMessage("¿Estás seguro de que deseas eliminar este usuario?")

            alertDialogBuilder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
                Toast.makeText(requireContext(), "Usuario eliminado", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }



        binding.backButton.setOnClickListener {
            UserManagerController.instance!!.goToMain()
        }
    }
}