package com.example.thi_comand_work.presentation.singIn

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.thi_comand_work.R
import com.example.thi_comand_work.databinding.ActivityMainSingInBinding
import com.example.thi_comand_work.domain.objects.api.HouseApi
import com.example.thi_comand_work.presentation.singIn.cardList.CardsListFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainSingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tb = binding.tbMainScreen
        setSupportActionBar(tb)
        openFragment(CardsListFragment.newInstance())
        //<-- TODO: test for backend  -->
        val interseptor = HttpLoggingInterceptor()
        interseptor.level = HttpLoggingInterceptor.Level.BODY
        val cl = OkHttpClient.Builder().addInterceptor(interseptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://realestatecorrect-1.onrender.com").client(cl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val houseApi = retrofit.create(HouseApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var result = houseApi.getHouseList()
            }
            catch (e : Exception){
                Log.e("MyApp", "Exception caught: ${e.message}", e)
            }
        }
        //<-- TODO: test for backend  -->

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
    fun withParOpenFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }



}