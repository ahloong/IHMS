package com.loong.ihms.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.databinding.ActivitySplashBinding
import com.loong.ihms.utils.UserRelatedUtil

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val userApiAuth = UserRelatedUtil.getUserApiAuth()

        /*if (userApiAuth.isEmpty()) {
            val intent = Intent(this, IpLoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }*/

        val intent = Intent(this, IpLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}