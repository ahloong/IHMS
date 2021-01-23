package com.loong.ihms.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.databinding.ActivityIpLoginBinding

class IpLoginActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityIpLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ip_login)
    }

    fun goToLogin(view: View) {
        val ipStr = binding.ipPortEditText.text.toString()

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("ip_login_params", ipStr)

        startActivity(intent)
        finish()
    }
}