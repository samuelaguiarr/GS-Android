package samuelaguiarr.com.github.alunos_rm550212_rm98007.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import samuelaguiarr.com.github.alunos_rm550212_rm98007.R
import samuelaguiarr.com.github.alunos_rm550212_rm98007.model.ItemModel

class ItemsAdapter(private val onItemRemoved: (ItemModel) -> Unit) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textViewItem)
        val textViewEvento = view.findViewById<TextView>(R.id.textViewEvento)
        val textViewImpacto = view.findViewById<TextView>(R.id.textViewImpacto)
        val textViewData = view.findViewById<TextView>(R.id.textViewData)
        val textViewAfetadas = view.findViewById<TextView>(R.id.textViewAfetadas)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(item: ItemModel) {
            textView.text = item.local
            textViewEvento.text = item.evento
            textViewImpacto.text = item.impacto
            textViewData.text = item.data
            textViewAfetadas.text = item.afetadas

            button.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}
