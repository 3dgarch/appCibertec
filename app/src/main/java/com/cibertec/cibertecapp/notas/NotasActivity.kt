package com.cibertec.cibertecapp.notas

import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.cibertecapp.R
import com.cibertec.cibertecapp.database.Nota
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotasActivity: AppCompatActivity(), NotasAdapter.ItemClickListener {

    private lateinit var notaViewModel: NotasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        notaViewModel = run{
            ViewModelProvider(this)[NotasViewModel::class.java]
        }

        val fabNuevaNota = findViewById<FloatingActionButton>(R.id.fabNuevaNota)
        fabNuevaNota.setOnClickListener{
            registerNota(null, tipo = 0)
        }

        val recyclerNotas = findViewById<RecyclerView>(R.id.recyclerNotas)

        val adapter = NotasAdapter(this)
        recyclerNotas.adapter = adapter
        recyclerNotas.layoutManager = LinearLayoutManager(this)


        notaViewModel.notas?.observe(this){notas ->
            notas?.let{
                adapter.setNotas(notas)
            }
        }


    }

    fun registerNota(nota: Nota?, tipo: Int){
        val mDialogBView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_note, null)

        //val titleAlertNote = "Register note"
        val titleAlertNote = if(tipo == 0) "Registrar" else "Editar"
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogBView)
            .setTitle(titleAlertNote)

        val mAlertDialog = mBuilder.show()

        val edtTitleNote = mDialogBView.findViewById<EditText>(R.id.edtTitleNote)
        val edtDescNote = mDialogBView.findViewById<EditText>(R.id.edtDescriptionNote)
        val btnCreate = mDialogBView.findViewById<Button>(R.id.btnCreate)


        if(tipo == 1){
            edtTitleNote.setText(nota?.title)
            edtDescNote.setText(nota?.description)
        }

        btnCreate.setOnClickListener{
            mAlertDialog.dismiss()

            val title = edtTitleNote.text.toString()
            val description = edtDescNote.text.toString()
            val date = formatDate(LocalDateTime.now())

            if(tipo == 0){
                val nota = Nota(title,description,date)

                notaViewModel.saveNoteWithCoroutines(nota)
            }else{
                val notaV = Nota(title,description,date)
                notaV.noteId = nota?.noteId!!
                notaViewModel.updateNoteWithCoroutines(notaV)
            }


        }
    }

    fun formatDate(date: LocalDateTime): String{
        // 02/20/2023 15:50:09

        val format = "dd/MM/yyyy HH:mm:ss"
        //val formatDate = date.format(DateTimeFormatter.ofPattern(""))

        //return formatDate
        return date.format(DateTimeFormatter.ofPattern(format))

    }

    override fun onItemClick(nota: Nota) {
        // function than show the alert
        registerNota(nota, tipo = 1)
    }
}