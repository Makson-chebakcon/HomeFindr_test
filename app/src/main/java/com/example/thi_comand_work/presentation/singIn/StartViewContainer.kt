package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thi_comand_work.R



class StartViewContainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_view_container, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = StartViewContainer()

    }
}