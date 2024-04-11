package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.ActivityMainSingInBinding
import com.example.thi_comand_work.presentation.singIn.cardList.CardsListFragment


class MainSingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSingInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tb = binding.tbMainScreen
        setSupportActionBar(tb)

        openFragment(CardsListFragment.newInstance())
        binding.tbMainScreen.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId){
                R.id.menuIcon ->{
                    binding.draver.openDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
        binding.navigationMenu.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.menuProfile ->{
                    openFragment(ProfileFragment.newInstance())
                    binding.draver.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.menuCardList ->{
                    openFragment(CardsListFragment.newInstance())
                    binding.draver.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.menuSetings ->{
                    openFragment(SettingsFragment.newInstance())
                    binding.draver.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.menuChat ->{
                    openFragment(ChatListFragment.newInstance())
                    binding.draver.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar, menu)
        return true
    }

    fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment).commit()
    }



}