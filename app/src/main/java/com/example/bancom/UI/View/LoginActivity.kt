package com.example.bancom.UI.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bancom.R
import com.example.bancom.UI.Session.LoginPreferences
import com.example.bancom.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity : AppCompatActivity() {
    private lateinit var aLoginBinding: ActivityLoginBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var email:String
    lateinit var password:String
    var recordarCorreo:Boolean = false
    val RC_SIGN_IN: Int = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        aLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(aLoginBinding.root)
        supportActionBar!!.hide()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        aLoginBinding.llBtnIngresarGoogle.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        aLoginBinding.llBtnIngresar.setOnClickListener {
            email = aLoginBinding.etEMail.text.toString()
            password = aLoginBinding.etPassword.text.toString()
            ValidarTextosVacios()
            ValidarLogin()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account!=null){
            val Main = Intent(this, MainActivity::class.java)
            Main.putExtra("EMail", account.displayName)
            startActivity(Main)
            finish()
        }

    }

    private fun ValidarTextosVacios() {
        if(email.isEmpty())
            aLoginBinding.etEMail.setError(getString(R.string.ErrorEMail))
        if(password.isEmpty())
            aLoginBinding.etPassword.setError(getString(R.string.ErrorPassword))
    }
    private fun ValidarLogin() {
        if(!email.isEmpty() && !password.isEmpty())
        {
            if(email.equals("juan.rodas@emperu.net") && password.equals("123456")) {
                recordarCorreo = aLoginBinding.cbRecordarCorreo.isChecked
                if (recordarCorreo)
                    GuardarEstadoSesion()
                EntrarMain()
            }
        }
    }

    override fun onBackPressed() {
        onPause()
    }

    private fun EntrarMain() {
        val Main = Intent(this, MainActivity::class.java)
        Main.putExtra("EMail", email)
        startActivity(Main)
        finish()
    }

    private fun GuardarEstadoSesion() {
        var a: LoginPreferences = LoginPreferences(this)
        a.GuardarDataSP(email)
    }
}