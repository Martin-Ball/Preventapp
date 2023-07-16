package com.martin.preventapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.martin.preventapp.R

class AmountAdapter(private val itemList: MutableList<ItemAmount>) : RecyclerView.Adapter<AmountAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_amount, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: ItemAmount) {
        itemList.add(item)
        notifyItemInserted(itemList.size - 1)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val addButton: ImageButton = itemView.findViewById(R.id.btn_add)
        private val subtractButton: ImageButton = itemView.findViewById(R.id.btn_less)
        private val quantityEditText: EditText = itemView.findViewById(R.id.editTextQuantity)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.btn_delete)

        fun bind(item: ItemAmount) {
            titleTextView.text = item.title
            addButton.setOnClickListener {
                quantityEditText.setText((item.quantity + 1).toString())
                item.quantity ++
            }
            subtractButton.setOnClickListener {
                quantityEditText.setText((item.quantity - 1).toString())
                item.quantity ++
            }
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }
}