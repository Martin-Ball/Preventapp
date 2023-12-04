import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Client

class ClientAdapter(context: Context, private val clientList: List<Client>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var filteredClientList: List<Client> = clientList

    override fun getCount(): Int {
        return filteredClientList.size
    }

    override fun getItem(position: Int): String {
        return filteredClientList[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_client, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.clientName.text = filteredClientList[position].name
        viewHolder.address.text = "Direccion: ${filteredClientList[position].address}"
        viewHolder.deliveryHour.text = "Horario: ${filteredClientList[position].deliveryHour}"

        return view
    }

    fun filter(query: String) {
        filteredClientList = clientList.filter { it.name.contains(query, ignoreCase = true) }
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val clientName: TextView = view.findViewById(R.id.clientName)
        val address: TextView = view.findViewById(R.id.address)
        val deliveryHour: TextView = view.findViewById(R.id.delivery_hour)
    }
}
