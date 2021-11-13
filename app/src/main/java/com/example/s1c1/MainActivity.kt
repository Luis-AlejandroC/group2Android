package com.example.s1c1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.s1c1.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private var edtMainUser : EditText? = null
    private var edtMainPass : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtMainUser = findViewById(R.id.edtMainUser)
        edtMainPass = findViewById(R.id.edtMainPass)
    }

    fun onRegister(view: android.view.View) {
        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)
    }

    fun onLogin(view: android.view.View) {
        var completeFields = true
        var inputUser : String = edtMainUser!!.text.toString()
        var inputPass : String = edtMainPass!!.text.toString()

        if(!validateField(inputUser, edtMainUser!!)){
            completeFields = false
        }

        if(!validateField(inputPass, edtMainPass!!)){
            completeFields = false
        }

        if(completeFields){
            if (inputUser == "hernandba@gmail.com"){
                if(inputPass == "Mision2022!"){

                    val positiveButton = { dialog: DialogInterface, which:Int->
                        val intento = Intent(this, WelcomeActivity::class.java)
                        startActivity(intento)
                    }

                    val dialog = AlertDialog.Builder(this)
                        .setTitle("Hola")
                        .setMessage("Usuario: "+edtMainUser!!.text.toString())
                        .setPositiveButton("Ok", positiveButton)

                    dialog.create().show()

                }else{
                    Toast.makeText(this, "Contraseña inválida", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Usuario inválido", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validateField(input : String, editTxt: EditText) : Boolean{
        if(TextUtils.isEmpty(input)){
            editTxt.error = "Campo requerido"
            return false
        }else editTxt.error = null

        return true
    }
}