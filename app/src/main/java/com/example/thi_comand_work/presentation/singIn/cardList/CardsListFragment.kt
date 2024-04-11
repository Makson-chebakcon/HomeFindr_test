package com.example.thi_comand_work.presentation.singIn.cardList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.databinding.FragmentCardsListBinding

class CardsListFragment : Fragment() {
    private lateinit var binding: FragmentCardsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardsListBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = CardsListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            


        }
    }


}