package com.ryvk.sampleprojectday5.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday5.R

class ProductExtrasAdapter (private val extrasList : List<ProductExtras>) : RecyclerView.Adapter<ProductExtrasAdapter.ProductExtrasViewHolder>() {

    private var selectedPosition = extrasList.indexOfFirst { it.selected }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductExtrasViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.product_extras,parent,false)
        return ProductExtrasViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductExtrasViewHolder,
        position: Int
    ) {
        val productExtra : ProductExtras = extrasList[position]
        holder.nameButton.text = productExtra.name
        holder.priceTextView.text = productExtra.price
        holder.nameButton.isChecked = position == selectedPosition

        holder.extrasView.setOnClickListener {
            if(selectedPosition != position){
                val previousSelected = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return extrasList.size
    }

    class ProductExtrasViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nameButton: RadioButton = view.findViewById(R.id.extrasRadioButton)
        val priceTextView: TextView = view.findViewById(R.id.extrasPrice)
        val extrasView: LinearLayout = view.findViewById(R.id.productExtraMain)
    }

}

data class ProductExtras(
    val name: String,
    val price: String,
    val selected : Boolean = false
)