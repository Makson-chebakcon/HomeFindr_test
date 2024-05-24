package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.thi_comand_work.databinding.FragmentSetCardBinding
import com.example.thi_comand_work.domain.dto.HouseDto
import com.example.thi_comand_work.domain.objects.HousePost
import kotlinx.coroutines.launch


class SetCardFragment : Fragment() {
    private lateinit var binding: FragmentSetCardBinding
    private val TAG = SetCardFragment::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = HouseDto()
        val owner = "@mail.com"
        binding.apply {
            button2.setOnClickListener {
                if (nameHome.text.toString() == "" || adressHome.text.toString() == "" ||
                    priceHome.text.toString() == "0" ||  priceHome.text.toString() == "" ||
                    descriptionHouse.text.toString() == ""
                ) {
                    Toast.makeText(requireContext(), "Bad data", Toast.LENGTH_LONG).show()
                } else {
                    lifecycleScope.launch {
                        try {
                            val house = HousePost(
                                nameHome.text.toString(),
                                adressHome.text.toString(),
                                descriptionHouse.text.toString(),
                                priceHome.text.toString().toInt(),
                                owner,
                                "",
                                0
                            )
                            api.postHouse(house, owner)
                            Toast.makeText(requireContext(), "House posted successfully", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), "Failed to post house", Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "Error posting house", e)
                        }
                    }
                }
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = SetCardFragment()

    }
}