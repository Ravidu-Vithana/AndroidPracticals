package com.ryvk.navigationgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener{v ->
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            v.findNavController().navigate(action)
        }

        val button2 = view.findViewById<Button>(R.id.button2)
        button2.setOnClickListener{v ->
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
            v.findNavController().navigate(action)
        }

        val button3 = view.findViewById<Button>(R.id.button3)
        button3.setOnClickListener{v ->
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            v.findNavController().navigate(action)
        }

        return view
    }

}