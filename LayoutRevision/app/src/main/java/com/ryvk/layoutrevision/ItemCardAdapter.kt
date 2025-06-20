package com.ryvk.layoutrevision

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemCardAdapter(private val productList: List<ItemCard>): RecyclerView.Adapter<ItemCardAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_card,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product: ItemCard = productList[position]
        holder.productName.text = product.name
        holder.price.text = product.price.toString()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productName: TextView = itemView.findViewById(R.id.textView8)
        var price: TextView = itemView.findViewById(R.id.textView10)
    }

}

data class ItemCard(var name: String,var price: Double) {}