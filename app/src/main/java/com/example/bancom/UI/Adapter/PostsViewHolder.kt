package com.example.bancom.UI.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bancom.Data.Model.Post
import com.example.bancom.R

class PostsViewHolder(vista: View): RecyclerView.ViewHolder(vista) {
    val tvUserID = vista.findViewById<TextView>(R.id.tvUserIDPost)
    val tvPostID = vista.findViewById<TextView>(R.id.tvPostID)
    val tvPostTitle = vista.findViewById<TextView>(R.id.tvPostTitle)
    val tvPostBody = vista.findViewById<TextView>(R.id.tvPostBody)

    fun ListarPosts(post: Post, pPosition: Int)
    {
        val userID = post.UserID.toString()
        val postID = post.ID.toString()
        val postTitle = post.Title
        val postBody = post.Body

        tvUserID.text = "ID User: " + userID
        tvPostID.text = "ID Post: " + postID
        tvPostTitle.text = "Título:  " + postTitle
        tvPostBody.text = "Body:     " + postBody
    }
}