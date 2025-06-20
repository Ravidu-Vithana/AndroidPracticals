package com.ryvk.navigationgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val button = view.findViewById<Button>(R.id.button6)
        button.setOnClickListener{v->
            val action = RegisterFragmentDirections.actionRegisterFragmentToVerificationFragment2()
            v.findNavController().navigate(action)
        }

        return view
    }

}