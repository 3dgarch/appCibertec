package com.cibertec.cibertecapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Arrays

class LoginActivity: AppCompatActivity() {

    /* Method Father */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_login)


        //button register
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //button login
        val btnLogin = findViewById<Button>(R.id.btnlogin)
        btnLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))

            //show alert
            //basicAlert()

            //basicAlertIcon()

            //basicAlertButtonColor()

            //alertWithItems()

            alertWithIneChoiceList()

        }

    }


    /* Alert */
    fun basicaAlert(){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Alerta Basica")
            setMessage("Mostrar un mensaje")

            // button positive
            setPositiveButton("Aceptar") {_: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Acepto", Toast.LENGTH_LONG).show()
            }

            //button negative
            setNegativeButton("Cancelar"){_: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Cancelar", Toast.LENGTH_LONG).show()
            }

            //button neutral
            setNeutralButton("Lo revisare mas tarde"){_: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Mas ADelante",
                    Toast.LENGTH_LONG).show()
            }


            // show alert
            show()
        }
    }

    /* Alert Button Icon */
    @SuppressLint("UseCompatLoadingForDrawables")
    fun basicAlertIcon(){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Llamar a contacto")
            setMessage("Selecciona la opcion para continuar")

            // button
            setPositiveButton("llamar", null)
            setPositiveButtonIcon(resources.getDrawable(R.drawable.baseline_call_24, null))

            setNegativeButton("Cancelar", null)

            setNeutralButton("Talvez", null)

            show()
        }
    }



    /* Alert with buttons colors */
    fun basicAlertButtonColor(){
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Llamar a contacto")
            setMessage("Selecciona la opcion para continuar")
            setPositiveButton("Llamar", null)
            setNegativeButton("Cancelar", null)
            setNeutralButton("Talvez", null)
        }

        val alertDialog = builder.create()
        alertDialog.show()

        val buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        with(buttonPositive){
            setBackgroundColor(resources.getColor(R.color.blue, null))
            setPadding(10, 0, 10, 0)
            setTextColor(Color.WHITE)
        }

        val buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        with(buttonNegative){
            setBackgroundColor(resources.getColor(R.color.blue, null))
            setPadding(10, 0, 10, 0)
            setTextColor(Color.WHITE)
        }


    }


    /* Alert with Items */
    fun alertWithItems(){
        val items = arrayOf("Java","Kotlin","Swift","C++","")
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Lenguajes de programacion")
            setItems(items){ dialog, index ->
                val item = items[index]
                Toast.makeText(context, "$item - Seleccionado", Toast.LENGTH_LONG).show()
            }
            show()
        }
    }


    /* Alert with one choice list */
    fun alertWithIneChoiceList(){
        val items = arrayOf("Java","Kotlin","Swift","C++","C#")
        val builder = AlertDialog.Builder(this)

        var itemsSelected: Int = -1

        with(builder){
            setTitle("Selecciona el lenguaje")
            setSingleChoiceItems(items, 0){ dialog, index ->
                itemsSelected = index
            }
            setPositiveButton("Aceptar"){ dialog, which ->
                val item = items[itemsSelected]
                Toast.makeText(context, "$item - Seleccionado",
                    Toast.LENGTH_LONG).show()
            }
            show()
        }
    }


    /* Alert with Multiple Choice List */
    fun alertWithMultipleChoiceList(){
        val items = arrayOf("Java","Kotlin","Swift","C++","C#")
        val selectedList = ArrayList<Int>()


        var builder = AlertDialog.Builder(this)
        with(builder){
            setTitle("Lista de lenguajes")
            setMultiChoiceItems(items, null){ dialog, index, state ->
                if(state){
                    selectedList.add(index)
                }else if(selectedList.contains(index)){
                    selectedList.remove(index)

                }
            }
            setPositiveButton("Aceptar"){ dialog, which ->
                val selectedStrings = ArrayList<String>()

                for(item in selectedList.indices){
                    selectedStrings.add(items[selectedList[item]])

                }
                Toast.makeText(context, "Los elementos seleccionados son: "
                        + Arrays.toString(selectedStrings.toTypedArray()),
                    Toast.LENGTH_LONG).show()

            }
            show()
        }

    }

}