package com.cibertec.cibertecapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class WelcomeActivity: AppCompatActivity() {

    private val listNoticias = listOf(
        Noticia("¿Porque invertir en una especialidad?","Por que ayuda mejorar tus conocimientos",R.drawable.noticia1),
                Noticia("¿Porque invertir en una Cryptomonedas?","Por que ayuda mejorar tus conocimientos",R.drawable.noticia2),
                Noticia("¿Porque invertir en una MAstria?","Por que a mejora tus conocimientos",R.drawable.noticia3)

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val recyclerNews = findViewById<RecyclerView>(R.id.recyclerNews)

        // apply > aplica configuraciones sobre un objeto
        recyclerNews.apply {
           // layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            //layoutManager = GridLayoutManager(context, 2)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = NoticiasAdapter(listNoticias)
        }
    }

}