package com.example.thi_comand_work.presentation.singIn.cardList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.FragmentCardsListBinding
import com.example.thi_comand_work.domain.dto.HouseDto
import com.example.thi_comand_work.presentation.singIn.SetCardFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val api  = HouseDto()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val houseList = api.getAllHouse()
                withContext(Dispatchers.Main) {
                    if (houseList.isNotEmpty()) {
                        val rcv = binding.recyclerViewCardList
                        rcv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CardsAdapter(houseList, requireContext())
                        rcv.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(),
                            "Не стабильное интернет соединение",Toast.LENGTH_LONG)
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(),
                    "Не стабильное интернет соединение",Toast.LENGTH_LONG)
                e.printStackTrace()
                // Обработка ошибки загрузки данных
            }
        }
        binding.button.setOnClickListener {
            openFragment(SetCardFragment.newInstance())

        }

    }
    fun openFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment).commit()
    }


}