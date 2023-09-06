package com.cibertec.cibertecapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {

    //metodo de padre
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_login)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        btnLogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



}