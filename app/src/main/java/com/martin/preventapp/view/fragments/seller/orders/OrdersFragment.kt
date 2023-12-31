package com.martin.preventapp.view.fragments.seller.orders

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.seller.interfaces.OrdersInterface
import com.martin.preventapp.databinding.FragmentOrdersBinding
import com.martin.preventapp.view.adapter.OrderAdapter
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.NewOrder
import java.util.Calendar
import java.util.TimeZone

class OrdersFragment : Fragment(), OrdersInterface.ViewOrders {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OrderItemClickListener

    companion object {
        private var ordersFragment: OrdersFragment? = null
        @JvmStatic
        val instance: OrdersFragment?
            get() {
                if (ordersFragment == null) {
                    ordersFragment = OrdersFragment()
                }
                return ordersFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"))
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
                binding.dateSelected.text = "Fecha: ${selectedDate}"
                ConfirmedOrdersController.instance!!.getOrdersByDate(selectedDate, 3)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailOrderFragment.instance!!)
        transaction.commit()
    }

    override fun setListener(listener: OrderItemClickListener) {
        this.listener = listener
    }

    fun showOrderDetail(list: List<NewOrder>){
        val adapter = OrderAdapter(requireContext(), list, listener)
        binding.orderList.adapter = adapter
    }

    fun showToast(text:String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}