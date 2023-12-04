import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Product

class ProductListAdapter(context: Context, private val products: List<Product>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var filteredProducts: List<Product> = products

    override fun getCount(): Int {
        return filteredProducts.size
    }

    override fun getItem(position: Int): Product {
        return filteredProducts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_product, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.textViewName.text = filteredProducts[position].productName
        viewHolder.textViewPrice.text = "$${filteredProducts[position].price}"

        return view
    }

    fun filter(query: String) {
        filteredProducts = products.filter { it.productName.contains(query, ignoreCase = true) }
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val textViewName: TextView = view.findViewById(R.id.name_product)
        val textViewPrice: TextView = view.findViewById(R.id.price_product)
    }
}
