package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.databinding.FragmentSettingsBinding
import java.util.Locale


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var flag:Boolean
        binding.switchLanguage.setText(Locale.getDefault().toLanguageTag())
        binding.apply {
            switchLanguage.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    flag  = setLanguage(Locale.getDefault().toLanguageTag())
                    binding.switchLanguage.setText(Locale.getDefault().toLanguageTag())
                }
                else{
                    flag = setLanguage(Locale.getDefault().toLanguageTag())
                    binding.switchLanguage.setText(Locale.getDefault().toLanguageTag())
                }
                if (!flag){
                    Toast.makeText(context,"Error setLanguage", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setLanguage(languageTeg:String):Boolean{//смена языка
        if (languageTeg == "en"){
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.create(Locale.forLanguageTag("ru"))
            )
            return true
        }
        else if (languageTeg == "ru"){
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.create(Locale.forLanguageTag("en"))
            )
            return true
        }
        return false
    }

}