package com.example.thi_comand_work.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        openFragment(ChoosAuthRegistration.newInstance())


    }
    private fun openFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment).commit()


    }
}