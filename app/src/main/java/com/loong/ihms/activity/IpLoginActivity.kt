package com.loong.ihms.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityIpLoginBinding

class IpLoginActivity : BaseActivity() {
    // Data binding
    private lateinit var binding: ActivityIpLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ip_login)
    }

    fun goToLogin(view: View) {
        var ipStr = binding.ipPortEditText.text.toString()

        if (!ipStr.contains("http")) {
            ipStr = "http://$ipStr"
        }

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("ip_login_params", ipStr)

        startActivity(intent)
    }
}