import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.martin.preventapp.R

class ClientAdapter(context: Context, private val clientList: List<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var filteredClientList: List<String> = clientList

    override fun getCount(): Int {
        return filteredClientList.size
    }

    override fun getItem(position: Int): String {
        return filteredClientList[position]
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

        viewHolder.clientName.text = filteredClientList[position]

        return view
    }

    fun filter(query: String) {
        filteredClientList = clientList.filter { it.contains(query, ignoreCase = true) }
        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val clientName: TextView = view.findViewById(R.id.clientName)
    }
}
