package com.example.firemind.InicioDeSesion

import User
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.firemind.DatabaseManager.DatabaseManager
import com.example.firemind.Home.MainActivity
import com.example.firemind.R
import com.example.firemind.Storage.dialogNotification
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

class InicioDeSesion : AppCompatActivity(), OnClickListener, TextWatcher, dialogNotification {

    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var buttonCrearCuenta : Button
    private lateinit var buttonInicioDeSesion : Button
    private lateinit var biometria : Button
    private lateinit var auth: FirebaseAuth
    private var canAuth : Boolean = false
    private lateinit var prompt: BiometricPrompt.PromptInfo
    private lateinit var user : User
    private var db = DatabaseManager()
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_de_sesion)
        fondo(R.drawable.fondoinitsessionday, R.drawable.fondoinitsessionnigth)
        editTextEmail = findViewById(R.id.User)
        editTextPassword = findViewById(R.id.Password)
        buttonCrearCuenta = findViewById(R.id.CrearCuenta)
        buttonInicioDeSesion = findViewById(R.id.InicioDeSesion)
        biometria = findViewById(R.id.Biometria)
        buttonInicioDeSesion.isEnabled = false
        var testUser = UserRecords(this).getUserDataDefault()

        if(testUser != null){
            user = testUser
            editTextEmail.setText(user.email)
            editTextPassword.setText(user.pass)
        } else{
            buttonInicioDeSesion.isEnabled = false
        }
        buttonCrearCuenta.setOnClickListener(this)
        buttonInicioDeSesion.setOnClickListener(this)
        editTextEmail.addTextChangedListener(this)
        editTextPassword.addTextChangedListener(this)
        biometria.setOnClickListener(this)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        buttonInicioDeSesion.addTextChangedListener(this)

        if(androidx.biometric.BiometricManager.from(this).canAuthenticate()
            == androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
        ){
            canAuth = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            prompt = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticacion")
                .setSubtitle("Usa el sensor biométrico")
                .setNegativeButtonText("Cerrar")
                .setConfirmationRequired(false)
                .build()
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.CrearCuenta -> {
                var dialog = DialogInitSession()
                dialog.show(supportFragmentManager, "User")
            }
            R.id.InicioDeSesion -> {
                iniciarSesion()
            }
            R.id.Biometria -> {
                biometria(prompt)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun biometria(auth: BiometricPrompt.PromptInfo){
        if(canAuth){
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
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
                        var sharedp = getSharedPreferences("data", MODE_PRIVATE)
                        var mail = sharedp.getString("email", "")
                        Toast.makeText(applicationContext, "Autenticación exitosa", Toast.LENGTH_SHORT)
                            .show()
                        goto()

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(applicationContext, "Autenticación fallida", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ).authenticate(auth)
        }
    }

    fun iniciarSesion(){
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun crearUsuario(){
        val email = user.email
        val password = user.pass
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                    UserRecords(this).insertUserData(email, password, true)
                } else {
                    Toast.makeText(
                        this,
                        "Error al crear el usuario: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        db.addUser(user)
    }

    fun fondo(noche : Int , dia : Int){
        val hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isDayTime = hourOfDay in 7..21
        val rootView = findViewById<View>(android.R.id.content)
        val drawableResourceId = if (isDayTime) noche else dia
        rootView.setBackgroundResource(drawableResourceId)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        buttonInicioDeSesion.isEnabled = editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty()
    }

    override fun onDismissDialog(b: Boolean) {

    }

    fun goto(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateUser(user: User) {
        this.user = user
        crearUsuario()
    }

}