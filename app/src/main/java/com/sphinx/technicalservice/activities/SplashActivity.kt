package com.sphinx.technicalservice.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sphinx.technicalservice.R
import com.sphinx.technicalservice.utils.helpers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        helpers.getToken(this)

        logotype.alpha = 0f
        logotype.animate().setDuration(1500).alpha(1f).withEndAction{
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

}