package com.cibertec.cibertecapp.notas

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.cibertecapp.database.CibertecRoomDatabase
import com.cibertec.cibertecapp.database.Nota
import com.cibertec.cibertecapp.database.NotaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotaRepository  (application: Application){
    private val noteDao: NotaDao? =
        CibertecRoomDatabase.getTnstance(application)?.noteDao()


    suspend fun inserTNotaWithCoroutines(nota: Nota){
        processInsertNote(nota)
    }
    private suspend fun processInsertNote(nota: Nota){
        withContext(Dispatchers.Default){
            noteDao?.insert(nota)
        }
    }

    fun getNota(): LiveData<List<Nota>>?{
        return noteDao?.list()
    }
}