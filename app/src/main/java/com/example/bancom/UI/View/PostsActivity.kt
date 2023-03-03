package com.example.bancom.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bancom.Data.Model.Post
import com.example.bancom.Data.Model.PostDataProvider
import com.example.bancom.R
import com.example.bancom.UI.Adapter.PostsAdapter
import com.example.bancom.UI.ViewModel.PostsViewModel
import com.example.bancom.UI.ViewModel.UsersViewModel
import com.example.bancom.databinding.ActivityLoginBinding
import com.example.bancom.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    private lateinit var aPostsBinding: ActivityPostsBinding
    private val postsViewModel: PostsViewModel by viewModels()
    private var PostList: List<Post> = listOf()
    var UserID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aPostsBinding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(aPostsBinding.root)
        supportActionBar!!.hide()
        UserID =  intent.getIntExtra("ItemID", 0)
        var Names = intent.getStringExtra("Names")
        aPostsBinding.tvTitlePosts.text = "Posts de " + Names
        aPostsBinding.ivBackToUsers.setOnClickListener{
            onBackPressed()
        }
        aPostsBinding.ivAddPost.setOnClickListener{
            IrNewPost()
        }
        CargarPosts(UserID)
    }

    private fun IrNewPost() {
        val intent = Intent(this, CrearPostActivity::class.java)
        intent.putExtra("userid", UserID)
        startActivity(intent)
    }

    private fun CargarPosts(pUserID: Int) {
        postsViewModel.onCreate(pUserID)
        postsViewModel.postsResponse.observe(this, Observer {
            aPostsBinding.rvPosts.layoutManager = LinearLayoutManager(this)
            aPostsBinding.rvPosts.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            val posts = it!!
            for (i in 0..posts.size)
                PostDataProvider.PostListResult.add(it)
            PostList = posts
            aPostsBinding.rvPosts.adapter = PostsAdapter(posts)
        })
        postsViewModel.isLoading.observe(this, Observer {
            aPostsBinding.pbPosts.isVisible = it
        })
    }
}