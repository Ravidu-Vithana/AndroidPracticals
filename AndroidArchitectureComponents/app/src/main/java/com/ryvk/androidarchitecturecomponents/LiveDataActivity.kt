package com.ryvk.androidarchitecturecomponents

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ryvk.androidarchitecturecomponents.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {

    private val binding : ActivityLiveDataBinding by lazy {
        ActivityLiveDataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this@LiveDataActivity).get(LiveDataViewModel::class.java)
        viewModel.startCountdown()

        viewModel.seconds().observe(this@LiveDataActivity) {
            binding.textView2.text = it.toString()
        }
        viewModel._finished().observe(this@LiveDataActivity){
            if(it){
                binding.textView2.text = "Finished"
            }
        }
    }
}