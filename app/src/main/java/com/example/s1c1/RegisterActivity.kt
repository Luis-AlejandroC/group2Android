package com.example.s1c1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.util.Patterns.*
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private var edtRegName : EditText?=null
    private var edtRegLastname : EditText?=null
    private var edtRegNit : EditText?=null
    private var edtRegEmail : EditText?=null
    private var edtRegPhone : EditText?=null
    private var edtRegPass : EditText?=null
    private var chkPolicies : CheckBox?=null

    private val NAME_PATTERN : Pattern = Pattern.compile(
        "[a-zA-z]*"
    )
    private val PASSWORD_PATTERN : Pattern = Pattern.compile(
        "^"+
                "(?=.*[0-9])"+
                "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*[$&+,:;=?@#|'<>.-^*()%!])"+
                "(?=\\S+$)"+
                ".{8,}"+
                "$"
    )
    private val NUMBER_PATTERN : Pattern = Pattern.compile(
        "[0-9]*"
    )
    private val EMAIL_PATTERN : Pattern = Patterns.EMAIL_ADDRESS


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtRegName = findViewById(R.id.edtRegName)
        edtRegLastname = findViewById(R.id.edtRegLastname)
        edtRegNit = findViewById(R.id.edtRegNit)
        edtRegEmail = findViewById(R.id.edtRegEmail)
        edtRegPhone = findViewById(R.id.edtRegPhone)
        edtRegPass = findViewById(R.id.edtRegPass)
        chkPolicies = findViewById(R.id.chkPolicies)

    }

    fun onRegRegister(view: android.view.View) {
        if(validateForm()){
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
            //Pasa a la otra pantalla
            val positiveButton = { dialog: DialogInterface, which:Int->
                val intento = Intent(this, WelcomeActivity::class.java)
                startActivity(intento)
            }

            val dialog = AlertDialog.Builder(this)
                .setTitle("Hola")
                .setMessage("Usuario: "+edtRegEmail!!.text.toString())
                .setPositiveButton("Ok", positiveButton)

            dialog.create().show()
        }
    }

    fun validateForm() : Boolean{
        var onValidate = true
        var inputName : String = edtRegName!!.text.toString().replace(" ", "")
        var inputLastname : String = edtRegLastname!!.text.toString().replace(" ", "")
        var inputNit : String = edtRegNit!!.text.toString()
        var inputEmail : String = edtRegEmail!!.text.toString()
        var inputPhone : String = edtRegPhone!!.text.toString()
        var inputPass : String = edtRegPass!!.text.toString()

        //Name
        if(!validateField(inputName, edtRegName!!, NAME_PATTERN, "Solo caracteres")){
            onValidate = false
        }
        //Lastname
        if(!validateField(inputLastname, edtRegLastname!!, NAME_PATTERN, "Solo caracteres")){
            onValidate = false
        }
        //Nit
        if(!validateField(inputNit, edtRegNit!!, NUMBER_PATTERN, "Solo números")){
            onValidate = false
        }
        //Email
        if(!validateField(inputEmail, edtRegEmail!!, EMAIL_PATTERN, "No es correo válido")){
            onValidate = false
        }
        //Phone
        if(!validateField(inputPhone, edtRegPhone!!, NUMBER_PATTERN, "Solo números")){
            onValidate = false
        }
        //Password
        if(!validateField(inputPass, edtRegPass!!, PASSWORD_PATTERN, "No es contraseña valida")){
            onValidate = false
        }
        //Check policies
        if(!chkPolicies!!.isChecked())
        {
            chkPolicies!!.error = "Acepte politicas de privacidad"
            onValidate = false

        }else chkPolicies!!.error = null

        return onValidate
    }

    fun validateField(input : String, editTxt: EditText, pattern: Pattern, errorMsg : String) : Boolean{
        if(TextUtils.isEmpty(input)){
            editTxt.error = "Campo requerido"
            return false
        }else if(!pattern.matcher(input).matches()){
            editTxt.error = errorMsg
            return false
        }else editTxt.error = null

        return true
    }

}