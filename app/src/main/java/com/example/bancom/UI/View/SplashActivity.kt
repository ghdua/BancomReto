package com.example.bancom.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.bancom.R
import com.example.bancom.UI.Session.LoginPreferences

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        VerificarLogin()
    }

    private fun VerificarLogin() {
        var a: LoginPreferences = LoginPreferences(this)
        var EMail = a.VerificarLogin()
        if (!EMail.equals(""))
            IrMain(EMail)
        else
        {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)
        }
    }

    private fun IrMain(pEMail: String) {
        val Main = Intent(this, MainActivity::class.java)
        Main.putExtra("EMail", pEMail)
        startActivity(Main)
    }
}