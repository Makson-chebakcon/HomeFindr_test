package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.databinding.FragmentFullHouseCardBinding
import com.example.thi_comand_work.domain.objects.House

class FullHouseCardFragment : Fragment() {
    private var fullHouse: House? = null
    private lateinit var binding: FragmentFullHouseCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fullHouse = it.getParcelable(ARG_FULL_HOUSE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullHouseCardBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fullHouse?.let { house ->
            binding.name.text = house.name
            binding.adress.text = house.address
            binding.description.text = house.description
            binding.price.text = house.price.toString()
            binding.textView10.text = house.owner

        }
    }

    companion object {
        private const val ARG_FULL_HOUSE = "fullHouse"

        @JvmStatic
        fun newInstance(fullHouse: House) =
            FullHouseCardFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FULL_HOUSE, fullHouse)
                }
            }
    }
}
