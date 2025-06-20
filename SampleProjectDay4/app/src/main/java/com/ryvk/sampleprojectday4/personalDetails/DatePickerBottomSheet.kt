package com.ryvk.sampleprojectday4.personalDetails

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ryvk.sampleprojectday4.R
import java.util.Calendar

class DatePickerBottomSheet(private val activity: PersonalDataActivity) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.datepicker_bottom_sheet,container,false)

        val datePicker: DatePicker = view.findViewById(R.id.datePicker)
        val today = Calendar.getInstance()

        datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val date = String.format("%02d/%02d/%04d",day, month, year)
            val dateEditText: EditText? = activity.findViewById<EditText>(R.id.editTextText2)
            dateEditText?.setText(date)
            dismiss()
        }
        return view
    }
}