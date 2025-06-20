package com.ryvk.headline

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var categoryButtonList: List<CategoryButton>
    private lateinit var categoryButtonLayout: LinearLayout
    private var categoryButtonObjectList : MutableList<Button> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView : RecyclerView= findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.setLayoutManager(layoutManager)

        categoryButtonList = listOf(
            CategoryButton(
                "Weather",
            ),
            CategoryButton(
                "Crypto",
            ),
            CategoryButton(
                "Stocks",
            ),
            CategoryButton(
                "Disasters",
            ),
            CategoryButton(
                "Achievements",
            )

        )

        categoryButtonLayout = findViewById(R.id.categoryButtonLayout)

        setCategoryButtons()
    }

    override fun onResume() {
        super.onResume()
    }

    fun setCategoryButtons(){

        categoryButtonList.forEachIndexed{index , categoryButton ->
            val categoryButtonInflated = LayoutInflater.from(this@MainActivity).inflate(R.layout.category_button,categoryButtonLayout,false) as Button

            categoryButtonInflated.text = categoryButton.name
            categoryButtonLayout.addView(categoryButtonInflated)

            categoryButtonInflated.setOnClickListener{
                setActiveButton(categoryButtonInflated)
                loadNewsList(categoryButton.name)
            }

            if(index == 0){
                loadNewsList(categoryButton.name)
                categoryButtonInflated.setTextColor(getColor(R.color.blue))
            }

            categoryButtonObjectList.add(categoryButtonInflated)

        }
    }

    fun setActiveButton(selectedButton: Button){
        for (button in categoryButtonObjectList){
            if(button === selectedButton){
                selectedButton.setTextColor(getColor(R.color.blue))
            }else{
                button.setTextColor(getColor(R.color.black))
            }
        }
    }

    fun loadNewsList(query : String){
        AlertUtils.showLoader(this@MainActivity)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.removeAllViews()

        MainActivityViewModal().getNewsList(this@MainActivity,query,){ mutableIt ->
            mutableIt.observe(this@MainActivity){
                val newsItemAdapter = NewsItemAdapter(it)
                recyclerView.adapter = newsItemAdapter
                AlertUtils.hideLoader()
            }
        }
    }
}

data class CategoryButton(val name: String)