package com.fake.simplicity

import android.text.Editable
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fake.simplicity.databinding.ItemListElementBinding

class ElementsAdapter<T : Any>(private val elementCallback: ElementCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ElementViewHolder<T>(
            binding = ItemListElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback = elementCallback
        )
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items?.get(position)
        item?.also {
            holder as ElementViewHolder<T>
            holder.onBind(it)
        }
    }

    fun submitList(listOfItems: List<T>?) {
        listOfItems?.also {
            items = it
            notifyDataSetChanged()
        }
    }

    class ElementViewHolder<T : Any>(
        private val binding: ItemListElementBinding,
        private val callback: ElementCallback
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: T) {
            binding.cardView.setOnClickListener {
                callback.openElementInfo(adapterPosition)
            }
            binding.description.text = Editable.Factory.getInstance()
                .newEditable(Html.fromHtml(DescriptionGenerator.generateDescription(item)))
        }
    }

}