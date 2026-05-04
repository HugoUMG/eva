package com.proyectoinvdebienes.mobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyectoinvdebienes.mobile.R

class ModuleCardAdapter(
    private val items: List<ModuleCard>,
    private val onClick: (ModuleCard) -> Unit
) : RecyclerView.Adapter<ModuleCardAdapter.ModuleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_module_card, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int = items.size

    class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.moduleCardTitle)
        private val subtitle: TextView = itemView.findViewById(R.id.moduleCardSubtitle)

        fun bind(item: ModuleCard, onClick: (ModuleCard) -> Unit) {
            title.text = item.title
            subtitle.text = item.subtitle
            itemView.setOnClickListener { onClick(item) }
        }
    }
}
