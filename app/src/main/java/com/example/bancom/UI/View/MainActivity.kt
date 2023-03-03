package com.example.bancom.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancom.Data.Model.User
import com.example.bancom.Data.Model.UserDataProvider
import com.example.bancom.Data.Model.UsersResponse
import com.example.bancom.R
import com.example.bancom.UI.Adapter.UsersAdapter
import com.example.bancom.UI.Session.LoginPreferences
import com.example.bancom.UI.ViewModel.UsersViewModel
import com.example.bancom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var aMainBinding: ActivityMainBinding
    private val usersViewModel: UsersViewModel by viewModels()
    private var UserList: List<User> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(aMainBinding.root)
        supportActionBar!!.hide()
        var userEmail = intent.getStringExtra("EMail")
        aMainBinding.tvUserEMail.text = "Hola " + userEmail
        CargarUsuarios()
        aMainBinding.ivCerrarSesion.setOnClickListener{
            NotificarCierre()
        }
    }

    private fun NotificarCierre() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Notificando cierre de sesión")
        builder.setMessage("¿Segur@ desea cerrar sesión?")
        builder.setPositiveButton("SÍ") { dialog, which ->
            CerrarSesion()
        }

        builder.setNegativeButton("No") { dialog, which ->
        }
        builder.show()
    }

    private fun CerrarSesion() {
        var a: LoginPreferences = LoginPreferences(this)
        a.BorrarData()
        IrLogin()
    }

    override fun onBackPressed() {
        onPause()
    }

    private fun IrLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun CargarUsuarios() {
        usersViewModel.onCreate()
        usersViewModel.usersResponse.observe(this, Observer {
            aMainBinding.rvUsers.layoutManager = LinearLayoutManager(this)
            aMainBinding.rvUsers.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            val users = it!!
            for (i in 0..users.size)
                UserDataProvider.UserListResult.add(it)
            UserList = users
            aMainBinding.rvUsers.adapter = UsersAdapter(users)
        })
        usersViewModel.isLoading.observe(this, Observer {
            aMainBinding.pbUsers.isVisible = it
        })
    }
}