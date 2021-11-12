package com.example.s1c1

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
            Toast.makeText(this, "Validacion correcta", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Validacion incorrecta", Toast.LENGTH_LONG).show()
        }
    }

    fun validateForm() : Boolean{
        var onValidate = true
        var inputName : String = edtRegName!!.text.toString().replace(" ", "")
        var inputLastname : String = edtRegLastname!!.text.toString().replace(" ", "")
        var inputNit : String = edtRegNit!!.text.toString().replace(" ", "")

        /*
        if(TextUtils.isEmpty(inputName)) {
            edtRegName!!.error = "Campo requerido"
            onValidate = false
        }else if(!NAME_PATTERN.matcher(inputName.replace(" ", "")).matches()){
            edtRegName!!.error = "Solo caracteres"
            onValidate = false
        }else edtRegName!!.error = null
        */

        if(!validateField(inputName, edtRegName!!, NAME_PATTERN, "Solo caracteres")){
            onValidate = false
        }

        if(!validateField(inputLastname, edtRegLastname!!, NAME_PATTERN, "Solo caracteres")){
            onValidate = false
        }

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