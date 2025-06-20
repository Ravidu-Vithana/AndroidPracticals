package com.ryvk.sampleprojectday5.productList

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.ryvk.sampleprojectday5.R
import com.ryvk.sampleprojectday5.productList.productTypes.BurgerFragment
import com.ryvk.sampleprojectday5.productList.productTypes.HamburgerFragment
import com.ryvk.sampleprojectday5.productList.productTypes.PizzaFragment


class ProductListActivity : AppCompatActivity() {

    private lateinit var typeButtonList: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton : ImageButton = findViewById(R.id.imageButton8)
        backButton.setOnClickListener{onBackPressed()}

        val button1: Button = findViewById<Button>(R.id.button6)
        button1.setOnClickListener(View.OnClickListener {
            setActiveButton(button1)
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, PizzaFragment(),"PizzaFragment")
            fragmentTransaction.commit()
        })

        val button2: Button = findViewById<Button>(R.id.button8)
        button2.setOnClickListener(View.OnClickListener {
            setActiveButton(button2)
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, BurgerFragment(),"BurgerFragment")
            fragmentTransaction.commit()
        })

        val button3: Button = findViewById<Button>(R.id.button5)
        button3.setOnClickListener(View.OnClickListener {
            setActiveButton(button3)
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, HamburgerFragment(),"HamburgerFragment")
            fragmentTransaction.commit()
        })

        typeButtonList = listOf(
            button1,button2,button3
        )

        button1.callOnClick()
    }

    fun setActiveButton(selectedButton: Button){
        for (button in typeButtonList){
            if(button === selectedButton){
                selectedButton.setTextColor(getColor(R.color.primary_blue))
                selectedButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ProductListActivity,R.color.secondary_blue))
            }else{
                button.setTextColor(getColor(R.color.primary_black))
                button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@ProductListActivity,R.color.transparent))
            }
        }
    }

}