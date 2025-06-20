package com.ryvk.navigationgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class ForgotPasswordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        val button = view.findViewById<Button>(R.id.button4)
        button.setOnClickListener{v->
            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToVerificationFragment2()
            v.findNavController().navigate(action)
        }

        return view
    }

}