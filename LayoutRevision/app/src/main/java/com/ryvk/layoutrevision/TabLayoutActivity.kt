package com.ryvk.layoutrevision

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tab_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabs)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(BlankFragmentOne(),"Blank 1")
        adapter.addFragment(BlankFragmentTwo(),"Blank 2")
        adapter.addFragment(BlankFragmentThree(),"Blank 3")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }
}