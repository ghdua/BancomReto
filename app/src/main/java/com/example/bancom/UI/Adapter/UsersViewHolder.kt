package com.example.bancom.UI.Adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bancom.Data.Model.Post
import com.example.bancom.Data.Model.User
import com.example.bancom.Data.Model.UsersResponse
import com.example.bancom.R
import com.example.bancom.UI.View.PostsActivity

class UsersViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
    val tvNombre = vista.findViewById<TextView>(R.id.tvNombre)
    val tvUsername = vista.findViewById<TextView>(R.id.tvUsername)
    val tvDireccion = vista.findViewById<TextView>(R.id.tvDireccion)
    val tvCorreo = vista.findViewById<TextView>(R.id.tvCorreo)
    val tvFono = vista.findViewById<TextView>(R.id.tvFono)
    val tvPosts = vista.findViewById<TextView>(R.id.tvPosts)
    var Vista: View = vista

    fun ListarUsuarios(usuario: User, pPosition: Int)
    {
        val Street: String = usuario.address.Street
        val Suite: String = usuario.address.Suite
        val City: String = usuario.address.City
        tvNombre.text = usuario.Name
        tvUsername.text = usuario.UserName
        tvDireccion.text = Street + " " + Suite + ", " + City
        tvCorreo.text = usuario.EMail
        tvFono.text = usuario.Phone
        tvPosts.text = "Ver posts"

        tvPosts.setOnClickListener{
            var intent  = Intent(Vista.context, PostsActivity::class.java)
            intent.putExtra("ItemID", pPosition+1)
            intent.putExtra("Names", usuario.Name)
            Vista.context.startActivity(intent)
        }
    }
}