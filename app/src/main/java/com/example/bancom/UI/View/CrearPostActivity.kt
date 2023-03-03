package com.example.bancom.UI.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.bancom.Data.Model.PostRequest
import com.example.bancom.R
import com.example.bancom.UI.ViewModel.PostsViewModel
import com.example.bancom.databinding.ActivityCrearPostBinding
import com.example.bancom.databinding.ActivityMainBinding

class CrearPostActivity : AppCompatActivity() {
    private lateinit var aCreatePostBinding: ActivityCrearPostBinding
    private val postsViewModel: PostsViewModel by viewModels()
    var titulo: String = ""
    var contenido: String = ""
    var uid: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aCreatePostBinding = ActivityCrearPostBinding.inflate(layoutInflater)
        setContentView(aCreatePostBinding.root)
        supportActionBar!!.hide()
        uid = intent.getIntExtra("userid", -1)

        aCreatePostBinding.ivBackPosts.setOnClickListener {
            onBackPressed()
        }

        aCreatePostBinding.btnCreateNewPost.setOnClickListener {
            titulo = aCreatePostBinding.etTitleNewPost.text.toString()
            contenido = aCreatePostBinding.etBodyNewPost.text.toString()
            ValidarTextosVacios(titulo, contenido)
            if (!titulo.isEmpty() && !contenido.isEmpty()) {
                val pr = PostRequest(titulo, contenido, uid)
                postsViewModel.addPost(pr)
                postsViewModel.addPostResponse.observe(this, Observer {
                    var postResponse = it!!
                    if (postResponse!=null)
                    {
                        if(!postResponse.PostBody.isEmpty()) {
                            Toast.makeText(this, "Post agregado correctamente", Toast.LENGTH_SHORT)
                                .show()
                            onBackPressed()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "No se pudo agregar, intente luego",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    }
                })
                postsViewModel.isLoading.observe(this, Observer {
                    aCreatePostBinding.pbNewPost.isVisible = it
                })
            }
        }
    }
    private fun ValidarTextosVacios(tit: String, conte: String){
        if(tit.isEmpty())
            aCreatePostBinding.etTitleNewPost.setError("Debe llenar este campo")
        if(conte.isEmpty())
            aCreatePostBinding.etBodyNewPost.setError("Debe llenar este campo")
    }
}