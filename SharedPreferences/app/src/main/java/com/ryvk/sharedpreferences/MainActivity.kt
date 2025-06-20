package com.ryvk.sharedpreferences

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryvk.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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

        val data1 = getValueFromSP("data1")
        if(data1 != null){
            binding.textView3.text = data1
        }
        val data2 = getValueFromSP("data2")
        if(data2 != null){
            binding.textView4.text = data2
        }

        binding.button.setOnClickListener{
            updateSharedPreferences("data1",binding.editTextText.text.toString())
            updateSharedPreferences("data2",binding.editTextText2.text.toString())
        }

    }

    fun updateSharedPreferences (name: String, value: String){
        val sp = getSharedPreferences("com.ryvk.sharedPreferences", MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(name,value)
        editor.apply()
    }

    fun getValueFromSP(name: String) : String?{
        val sp = getSharedPreferences("com.ryvk.sharedPreferences", MODE_PRIVATE)
        return sp.getString(name,null)
    }
}