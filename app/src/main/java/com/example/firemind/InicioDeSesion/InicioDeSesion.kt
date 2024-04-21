package com.example.firemind.InicioDeSesion

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.firemind.R
import java.util.Calendar
import com.google.firebase.auth.FirebaseAuth

class InicioDeSesion : AppCompatActivity(), OnClickListener {

    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var buttonCrearCuenta : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_de_sesion)
        fondo(R.drawable.fondoinitsessionday, R.drawable.fondoinitsessionnigth)
        editTextEmail = findViewById(R.id.User)
        editTextPassword = findViewById(R.id.Password)
        buttonCrearCuenta = findViewById(R.id.CrearCuenta)
        buttonCrearCuenta.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.CrearCuenta -> {
                crearUsuario()
            }
            R.id.InicioDeSesion -> {
                iniciarSesion()
            }
            R.id.Biometria -> {
                biometria()
            }
        }
    }
    fun biometria(){
        val executor = ContextCompat.getMainExecutor(this)
        val callback = @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(
                    applicationContext,
                    "Error de autenticación: $errString",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(applicationContext, "Autenticación exitosa", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Autenticación fallida", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun iniciarSesion(){
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        var auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun crearUsuario(){
        var auth = FirebaseAuth.getInstance()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error al crear el usuario: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun fondo(noche : Int , dia : Int){
        val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isDayTime = hourOfDay in 7..21
        val rootView = findViewById<View>(android.R.id.content)
        val drawableResourceId = if (isDayTime) dia else noche
        rootView.setBackgroundResource(drawableResourceId)
    }

}