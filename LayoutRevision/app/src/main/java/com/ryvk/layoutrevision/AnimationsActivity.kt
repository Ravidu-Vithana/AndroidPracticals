package com.ryvk.layoutrevision

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AnimationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val animation: Animation = AnimationUtils.loadAnimation(this@AnimationsActivity, R.anim.animation_1)
        val animation2: Animation = AnimationUtils.loadAnimation(this@AnimationsActivity, R.anim.animation_2)

        val image: ImageView = findViewById(R.id.imageView11)

        val showAnimationButton: Button = findViewById(R.id.button9)
        showAnimationButton.setOnClickListener{
            image.startAnimation(animation)
        }

        val showAnimationButton2: Button = findViewById(R.id.button11)
        showAnimationButton2.setOnClickListener{
            image.startAnimation(animation2)
        }

    }
}