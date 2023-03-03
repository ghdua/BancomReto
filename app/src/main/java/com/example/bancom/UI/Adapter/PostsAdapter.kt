package com.example.bancom.UI.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bancom.Data.Model.Post
import com.example.bancom.R

class PostsAdapter(private val postLista: List<Post>): RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(layoutInflater.inflate(R.layout.posts_layout, parent, false))
    }

    override fun getItemCount(): Int = postLista.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = postLista[position]
        holder.ListarPosts(item, position)
    }
}