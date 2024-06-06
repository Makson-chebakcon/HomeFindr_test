package com.example.thi_comand_work.presentation.auth

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.databinding.FragmentRegistrationBinding
import com.example.thi_comand_work.domain.dto.ApiDto
import com.example.thi_comand_work.domain.objects.User
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val api = ApiDto()

        binding.registrationButton.setOnClickListener {
            if (!binding.editEmailText.text.toString().contains('@')) {
                Toast.makeText(requireContext(), "Неправильно введена почта", Toast.LENGTH_LONG).show()
            } else if (binding.editPassText.text.toString() != binding.editPassSecText.text.toString()) {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        if (!isInternetAvailable(requireContext())) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Отсутствует подключение к интернету", Toast.LENGTH_LONG).show()
                            }
                            return@launch
                        }

                        val user = User(
                            binding.editPhoneText.text.toString(),
                            binding.editPassText.text.toString(),
                            binding.editPassSecText.text.toString(),
                            binding.editEmailText.text.toString()
                        )

                        val message = api.postRegistrationUser(user)//тут выполняется запрос
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_LONG).show()
                            parentFragmentManager.popBackStack()
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
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
