package com.cibertec.cibertecapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class NoticiasViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_news, parent, false)) {

    // variables
    private var imgPortada: ImageView ? = null
    private var textTitle: TextView ? = null
    private var textDescription: TextView ? = null



    //bloque inicializacion o constructor de la clase
    init {
        imgPortada = itemView.findViewById(R.id.imgPortada)
        textTitle = itemView.findViewById(R.id.textNewsTitle)
        textDescription = itemView.findViewById(R.id.textNewDescription)
    }

    fun bind(noticia: Noticia){
        textTitle?.text = noticia.title
        textDescription?.text = noticia.description
        imgPortada?.setImageResource(noticia.image)
    }


}