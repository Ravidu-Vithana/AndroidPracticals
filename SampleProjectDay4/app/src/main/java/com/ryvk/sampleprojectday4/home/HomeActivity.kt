package com.ryvk.sampleprojectday4.home

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.ryvk.sampleprojectday4.R
import com.ryvk.sampleprojectday4.home.ui.HomeFragment
import com.ryvk.sampleprojectday4.home.ui.NotificationsFragment
import com.ryvk.sampleprojectday4.home.ui.StatisticsFragment
import com.ryvk.sampleprojectday4.home.ui.UserFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonList: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val homeImageButton: ImageButton = findViewById(R.id.imageButton7)
        val statisticsImageButton: ImageButton = findViewById(R.id.imageButton8)
        val notificationsImageButton: ImageButton = findViewById(R.id.imageButton9)
        val userImageButton: ImageButton = findViewById(R.id.imageButton10)

        buttonList = listOf(
            homeImageButton,
            statisticsImageButton,
            notificationsImageButton,
            userImageButton
        )

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.homeFragmentView,HomeFragment(),"homeFragment")
        fragmentTransaction.commit()
        setActiveButton(homeImageButton)

        homeImageButton.setOnClickListener{
            val fragmentTransactionNew: FragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransactionNew.replace(R.id.homeFragmentView,HomeFragment(),"homeFragment")
            fragmentTransactionNew.commit()
            setActiveButton(homeImageButton)
        }
        statisticsImageButton.setOnClickListener{
            val fragmentTransactionNew: FragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransactionNew.replace(R.id.homeFragmentView,StatisticsFragment(),"statisticsFragment")
            fragmentTransactionNew.commit()
            setActiveButton(statisticsImageButton)
        }
        notificationsImageButton.setOnClickListener{
            val fragmentTransactionNew: FragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransactionNew.replace(R.id.homeFragmentView,NotificationsFragment(),"notificationsFragment")
            fragmentTransactionNew.commit()
            setActiveButton(notificationsImageButton)
        }
        userImageButton.setOnClickListener{
            val fragmentTransactionNew: FragmentTransaction = supportFragmentManager.beginTransaction();
            fragmentTransactionNew.replace(R.id.homeFragmentView,UserFragment(),"userFragment")
            fragmentTransactionNew.commit()
            setActiveButton(userImageButton)
        }

    }

    private fun setActiveButton (selectedButton: ImageButton){

        for (button in buttonList){
            if(button == selectedButton){
                selectedButton.setColorFilter(ContextCompat.getColor(this@HomeActivity, R.color.primary_iconColor_selected))
            }else{
                if(button.colorFilter != null){
                    button.clearColorFilter()
                }
            }
        }
    }

}