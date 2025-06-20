package com.ryvk.sampleprojectday4.home.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ryvk.sampleprojectday4.R
import com.ryvk.sampleprojectday4.community.CommunityActivity
import com.ryvk.sampleprojectday4.personalDetails.PersonalDataActivity

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_user, container, false)

        val button1: Button? = view.findViewById(R.id.button9)
        button1?.setOnClickListener{
            startActivity(Intent(activity, PersonalDataActivity::class.java))
        }

        val button2: Button? = view.findViewById(R.id.button7)
        button2?.setOnClickListener{
            startActivity(Intent(activity, CommunityActivity::class.java))
        }

        return view
    }
}