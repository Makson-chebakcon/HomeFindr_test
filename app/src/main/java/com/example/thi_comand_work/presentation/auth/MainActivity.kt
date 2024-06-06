package com.example.thi_comand_work.presentation.auth

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var inAnimation: Animation
    private lateinit var outAnimation : Animation
    private lateinit var transaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inAnimation =  AnimationUtils.loadAnimation(this, R.anim.anim_in)
        outAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_out)

        supportActionBar?.hide()
        transaction  =  supportFragmentManager.beginTransaction()
        openFragment(ChoosAuthRegistration.newInstance())


    }
    fun openFragment(fragment: Fragment) {

        val transition = this.supportFragmentManager.beginTransaction()
        transition.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        transition.replace(R.id.mainFragmentContainer, fragment)
            .commit()
    }

}