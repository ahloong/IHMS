package com.loong.ihms.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.loong.ihms.R
import com.loong.ihms.databinding.ActivityHomeBinding
import com.loong.ihms.fragment.CuratorFragment
import com.loong.ihms.fragment.HomeFragment
import com.loong.ihms.fragment.PlayingFragment

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding //data binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.bottomNav.setOnNavigationItemSelectedListener(this)
        binding.navView.setNavigationItemSelectedListener(this)

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_nav -> {
                openFragment(HomeFragment())
                return true
            }

            R.id.now_playing -> {
                openFragment(PlayingFragment())
                return true
            }

            R.id.curator -> {
                openFragment(CuratorFragment())
                return true
            }

            R.id.nav_album -> {
                return true
            }
        }

        return false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.homeContainer.id, fragment)
        transaction.commit()
    }
}