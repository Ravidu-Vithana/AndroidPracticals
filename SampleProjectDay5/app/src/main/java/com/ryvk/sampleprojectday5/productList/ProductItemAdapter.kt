package com.ryvk.sampleprojectday5.productList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday5.R

class ProductItemAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        Log.d("Checking iteration", "onBindViewHolder: $position")
        val item : Product = productList[position]
        holder.nameText.text = item.name
        holder.timeText.text = item.time.toString()+"mins"
        holder.priceText.text = "$"+item.price.toString()
    }

    class ProductItemViewHolder (view: View): RecyclerView.ViewHolder(view){
        var nameText: TextView = view.findViewById(R.id.textView24)
        var timeText: TextView = view.findViewById(R.id.textView25)
        var priceText: TextView = view.findViewById(R.id.textView26)
    }
}

data class Product(var name: String, var time: Int, var price: Double)