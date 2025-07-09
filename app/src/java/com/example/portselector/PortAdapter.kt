package com.example.portselector

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class PortAdapter(
    private val ports: List<Port>,
    private val isFrench: Boolean,
    private val selectedIds: MutableSet<String>,
    private val onToggle: (String) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = ports.size
    override fun getItem(position: Int): Any = ports[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.port_item, parent, false)

        val port = ports[position]

        val nameText = view.findViewById<TextView>(R.id.port_name)
        val statusText = view.findViewById<TextView>(R.id.port_status)
        val checkbox = view.findViewById<CheckBox>(R.id.port_checkbox)

        nameText.text = if (isFrench) port.nameFr else port.nameEn
        statusText.text = if (isFrench) {
            if (port.isAvailable) "Disponible" else "Indisponible"
        } else {
            if (port.isAvailable) "Available" else "Unavailable"
        }

        statusText.setTextColor(if (port.isAvailable) Color.GREEN else Color.RED)
        checkbox.isChecked = selectedIds.contains(port.id)

        view.setOnClickListener {
            onToggle(port.id)
            notifyDataSetChanged()
        }

        return view
    }
}
