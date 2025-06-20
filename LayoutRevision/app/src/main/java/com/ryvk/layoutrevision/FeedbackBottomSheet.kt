package com.ryvk.layoutrevision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FeedbackBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.feedback_bottom_sheet,container,false)

        val submitButton: Button = view.findViewById(R.id.button10)
        submitButton.setOnClickListener{
            Toast.makeText(view.context,"Thank you for your feedback!",Toast.LENGTH_SHORT).show()
        }
        return view
    }

}