package com.loong.ihms.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.loong.ihms.R
import com.loong.ihms.base.BaseActivity
import com.loong.ihms.databinding.ActivityLoginBinding
import com.loong.ihms.model.UserProfile
import com.loong.ihms.network.ApiRepositoryFunction
import com.loong.ihms.network.ApiResponseCallback
import com.loong.ihms.utils.UserRelatedUtil

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val url = intent.getStringExtra("ip_login_params") ?: ""
        binding.ipTextView.text = "Connected To: $url"
        UserRelatedUtil.saveMainApiUrl(url)
    }

    fun goToHome(view: View) {
        val username = binding.usernameField.text.toString()
        val password = binding.passwordField.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            ApiRepositoryFunction.getUserLogin(
                username,
                password,
                object : ApiResponseCallback<UserProfile> {
                    override fun onSuccess(responseData: UserProfile) {
                        UserRelatedUtil.saveUserApiAuth(responseData.auth)

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                    override fun onFailed() {

                    }
                }
            )
        }
    }
}