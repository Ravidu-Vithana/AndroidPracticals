package com.ryvk.sampleprojectday5.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.ryvk.sampleprojectday5.R
import com.ryvk.sampleprojectday5.product.ProductActivity
import com.ryvk.sampleprojectday5.productList.ProductListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var categoryButtonList: List<CategoryButton>
    private lateinit var categoryButtonLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoryButtonList = listOf(
            CategoryButton(
                "All",
                null
            ),
            CategoryButton(
                "Desert",
                R.drawable.ic_dessert
            ),
            CategoryButton(
                "Pizza",
                R.drawable.ic_pizza
            ),
            CategoryButton(
                "Burger",
                R.drawable.ic_hamburger
            )

        )

        categoryButtonLayout = findViewById(R.id.categoryButtonLayout)

        setCategoryButtons()

        val imageButton: ImageButton = findViewById(R.id.imageButton3)
        imageButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, ProductActivity::class.java))
        }

        val imageButton2: ImageButton = findViewById(R.id.imageButton7)
        imageButton2.setOnClickListener{
            startActivity(Intent(this@MainActivity, ProductListActivity::class.java))
        }
    }

    fun setCategoryButtons(){
        categoryButtonList.forEach{categoryButton ->
            val categoryButtonInflated = LayoutInflater.from(this@MainActivity).inflate(R.layout.category_button,categoryButtonLayout,false) as Button

            categoryButtonInflated.text = categoryButton.name
            if(categoryButton.drawable != null) categoryButtonInflated.setCompoundDrawablesWithIntrinsicBounds(categoryButton.drawable, 0, 0, 0);

            categoryButtonLayout.addView(categoryButtonInflated)

        }
    }
}

data class CategoryButton(val name: String, @DrawableRes val drawable: Int?)