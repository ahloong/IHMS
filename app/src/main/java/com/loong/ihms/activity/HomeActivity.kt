package com.loong.ihms.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityHomeBinding
import com.loong.ihms.fragment.MainCuratorFragment
import com.loong.ihms.fragment.MainHomeFragment
import com.loong.ihms.fragment.MainNowPlayingFragment

private const val ID_HOME_CONTAINER_FL: Int = R.id.home_container_fl
private const val ID_FRAGMENT_HOME: Int = R.id.home_nav
private const val ID_FRAGMENT_NOW_PLAYING: Int = R.id.now_playing_nav
private const val ID_FRAGMENT_CURATOR: Int = R.id.curator_nav
private const val ID_LOGOUT: Int = R.id.logout_side

class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private val fragmentManager: FragmentManager = supportFragmentManager
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.homeBottomNavView.setOnNavigationItemSelectedListener(this)
        binding.homeNavView.setNavigationItemSelectedListener(this)
        binding.homeToolbar.setNavigationOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }

        setupViewPager()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            ID_FRAGMENT_HOME -> {
                showFragment(ID_FRAGMENT_HOME.toString())
                return true
            }

            ID_FRAGMENT_NOW_PLAYING -> {
                showFragment(ID_FRAGMENT_NOW_PLAYING.toString())
                return true
            }

            ID_FRAGMENT_CURATOR -> {
                showFragment(ID_FRAGMENT_CURATOR.toString())
                return true
            }

            ID_LOGOUT -> {
                val intent = Intent(this, IpLoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                return true
            }
        }

        return false
    }

    private fun setupViewPager() {
        val homeFragment = MainHomeFragment()
        val nowPlayingFragment = MainNowPlayingFragment()
        val curatorFragment = MainCuratorFragment()

        fragmentManager
            .beginTransaction()
            .add(ID_HOME_CONTAINER_FL, homeFragment, ID_FRAGMENT_HOME.toString())
            .hide(homeFragment)
            .commit()

        fragmentManager
            .beginTransaction()
            .add(ID_HOME_CONTAINER_FL, nowPlayingFragment, ID_FRAGMENT_NOW_PLAYING.toString())
            .hide(nowPlayingFragment)
            .commit()

        fragmentManager
            .beginTransaction()
            .add(ID_HOME_CONTAINER_FL, curatorFragment, ID_FRAGMENT_CURATOR.toString())
            .hide(curatorFragment)
            .commit()

        fragmentManager.executePendingTransactions()
        showFragment(ID_FRAGMENT_HOME.toString())
    }

    private fun showFragment(tag: String) {
        val fragment = fragmentManager.findFragmentByTag(tag)

        if (fragment != null) {
            replaceFragment(fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (currentFragment == null) {
            fragmentManager
                .beginTransaction()
                .show(fragment)
                .commit()
        } else if (currentFragment != null && fragment != currentFragment) {
            fragmentManager
                .beginTransaction()
                .hide(currentFragment!!)
                .show(fragment)
                .commit()
        }

        fragmentManager.executePendingTransactions()
        currentFragment = fragment
    }
}