package com.ryvk.sampleprojectday4.personalDetails

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryvk.sampleprojectday4.R

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personal_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datePickerEditText: EditText = findViewById(R.id.editTextText2)
        datePickerEditText.setOnClickListener{
            val datePickerBottomSheet = DatePickerBottomSheet(this@PersonalDataActivity)
            datePickerBottomSheet.show(supportFragmentManager,"DatePickerSheet")
        }

    }
}