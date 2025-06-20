package com.ryvk.sampleprojectday5.productList.productTypes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryvk.sampleprojectday5.R
import com.ryvk.sampleprojectday5.productList.Product
import com.ryvk.sampleprojectday5.productList.ProductItemAdapter

class PizzaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var productList = listOf(
            Product(
                "Devilled Chicken",
                4,
                15.00
            ),
            Product(
                "Tandoori Chicken",
                3,
                17.50
            ),
            Product(
                "Curry Chicken",
                6,
                12.50
            ),
        )
        val view: View = inflater.inflate(R.layout.fragment_pizza, container, false)

        val productItemAdapter = ProductItemAdapter(productList)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = productItemAdapter

        return view
    }
}