package com.example.thi_comand_work.presentation.auth

import TokenViewModel
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.FragmentChoosAuthRegistrationBinding
import com.example.thi_comand_work.domain.dto.ApiDto
import com.example.thi_comand_work.presentation.singIn.MainSingInActivity
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class ChoosAuthRegistration : Fragment() {
    private lateinit var binding: FragmentChoosAuthRegistrationBinding
    private lateinit var tokenViewModel: TokenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoosAuthRegistrationBinding.inflate(inflater)
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = ChoosAuthRegistration()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(TokenViewModel::class.java)


        binding.apply {
            //todo open RegistrationFragment
            textRegistration.setOnClickListener{
                openFragment(RegistrationFragment.newInstance())
            }
            //todo open ForgotPassFragment
            textViewForgotPass.setOnClickListener {
                openFragment(ForgotPassFragment.newInstance())
            }
            //todo open next activity
            buttonSingIn.setOnClickListener {
                val api = ApiDto()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        if (!isInternetAvailable(requireContext())) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Отсутствует подключение к интернету", Toast.LENGTH_LONG).show()
                            }
                            return@launch
                        }
                        val token = api.postLoginUser(binding.editTextText.toString(), binding.editTextTextPassword.toString())

                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Успешная авторизация", Toast.LENGTH_LONG).show()
                            tokenViewModel.saveToken(token.access_token)

                            val intent = Intent(activity, MainSingInActivity::class.java)
                            requireContext().startActivity(intent)
                            activity?.finish()
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

    fun openFragment(fragment: Fragment) {
        val activity = activity ?: return
        val transition = requireActivity().supportFragmentManager.beginTransaction()
        transition.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transition.replace(R.id.mainFragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }





}