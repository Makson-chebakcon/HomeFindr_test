package com.example.thi_comand_work.presentation.singIn.cardList

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.CustomDilogLayoutBinding
import com.example.thi_comand_work.databinding.FragmentCardsListBinding
import com.example.thi_comand_work.domain.dto.HouseDto
import com.example.thi_comand_work.presentation.singIn.SetCardFragment
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


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
                if (!isInternetAvailable(requireContext())) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Отсутствует подключение к интернету", Toast.LENGTH_LONG).show()
                    }
                    return@launch
                }
                val houseList = api.getAllHouse()
                withContext(Dispatchers.Main) {
                    if (houseList.isNotEmpty()) {
                        val rcv = binding.recyclerViewCardList
                        rcv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CardsAdapter(houseList, requireContext())
                        rcv.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(), "Данные отсутствуют", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Ошибка сети: ${e.message}", Toast.LENGTH_LONG).show()
                }
                e.printStackTrace()
            } catch (e: JsonSyntaxException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Ошибка парсинга данных: ${e.message}", Toast.LENGTH_LONG).show()
                }
                e.printStackTrace()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Неизвестная ошибка: ${e.message}", Toast.LENGTH_LONG).show()
                }
                e.printStackTrace()
            }
        }
        binding.button.setOnClickListener {
            openFragment(SetCardFragment.newInstance())

        }
        binding.filter.setOnClickListener {
            showCustomDialog()
        }

    }
    fun openFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment).addToBackStack(null).commit()
    }
    private fun showCustomDialog() {

        val dialog = Dialog(requireContext(), R.style.CustomDialogStyle)
        val dialogBinding = CustomDilogLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        // Устанавливаем данные в спиннеры
        val facadeTypes = arrayOf("Панельный", "Монолитный", "Кирпичный", "Блочный", "Деревянный", "Другое...")
        val floorOptions = arrayOf("1-3", "4-6", "7-9", "10-12", "Выше...")
        val floorsInHouseOptions = arrayOf("1", "2", "3-5", "6-12", "Больше...")
        val roomsOptions = arrayOf("1", "2", "3", "4", "5", "Студия")
        val apartmentAreaOptions = arrayOf("Менее 30 кв.м", "30-50 кв.м", "50-70 кв.м", "70-90 кв.м", "более 100 кв.м")
        val kitchenAreaOptions = arrayOf("менее 5 кв.м", "5-8 кв.м", "8-12 кв.м", "12-20 кв.м", "более 20 кв.м")

        setSpinnerAdapter(dialogBinding.spinnerFacade, facadeTypes)
        setSpinnerAdapter(dialogBinding.spinnerFloor, floorOptions)
        setSpinnerAdapter(dialogBinding.spinnerFloorsInHouse, floorsInHouseOptions)
        setSpinnerAdapter(dialogBinding.spinnerRooms, roomsOptions)
        setSpinnerAdapter(dialogBinding.spinnerApartmentArea, apartmentAreaOptions)
        setSpinnerAdapter(dialogBinding.spinnerKitchenArea, kitchenAreaOptions)

        // Обработчик для кнопки Применить
        dialogBinding.applyButton.setOnClickListener {
            // Обработка действия Применить
            binding.dimView.visibility = View.GONE
            dialog.dismiss()
        }
        binding.dimView.visibility = View.VISIBLE
        dialog.show()
    }

    private fun setSpinnerAdapter(spinner: Spinner, data: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }




}