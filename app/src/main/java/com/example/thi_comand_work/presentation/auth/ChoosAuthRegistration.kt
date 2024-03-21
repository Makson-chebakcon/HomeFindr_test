package com.example.thi_comand_work.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.FragmentChoosAuthRegistrationBinding

class ChoosAuthRegistration : Fragment() {
    private lateinit var binding: FragmentChoosAuthRegistrationBinding
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
             //todo open next activity
            }
        }
    }

    fun openFragment(fragment: Fragment){
        val activity = activity ?: return
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment)
            .addToBackStack(null).commit()

    }




}