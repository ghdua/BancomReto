package com.example.bancom.UI.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancom.Data.Model.User
import com.example.bancom.Data.Model.UsersResponse
import com.example.bancom.R

class UsersAdapter(private val userLista: List<User>): RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UsersViewHolder(layoutInflater.inflate(R.layout.users_layout, parent, false))
    }

    override fun getItemCount(): Int = userLista.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = userLista[position]
        holder.ListarUsuarios(item, position)
    }
}