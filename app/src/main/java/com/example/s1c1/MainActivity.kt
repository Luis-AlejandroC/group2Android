package com.example.s1c1

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.example.s1c1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var edtUserName: EditText? = null
    private var edtPassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUserName = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)

    }

    fun onLogin(botonLogin: View)
    {
        if (edtUserName!!.text.toString() == "milena@email.com")
        {
            if ( edtPassword!!.text.toString()== "1234")
            {
                val intento = Intent(this, WelcomeActivity::class.java)
                startActivity(intento)
            }

        }
    }
    fun onsingUp(botonsingUp: View) {}
}



