package com.example.thi_comand_work.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.thi_comand_work.R

class ForgotPassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_pass, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ForgotPassFragment()

    }
}