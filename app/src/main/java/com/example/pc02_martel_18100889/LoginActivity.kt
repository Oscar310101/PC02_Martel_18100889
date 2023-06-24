package com.example.pc02_martel_18100889

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val txtDni = findViewById<EditText>(R.id.etDNI)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val db = FirebaseFirestore.getInstance()
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val Dni = txtDni.text.toString()
            val clave = txtPassword.text.toString()
            // FALTA FILTRAR EL USUARIO YA REGISTRADO
            db.collection("users").addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Snackbar
                        .make(
                            findViewById(android.R.id.content),
                            "Inicio de sesión exitoso",
                            Snackbar.LENGTH_LONG
                        ).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    //Credenciales inválidas
                    Snackbar
                        .make(
                            findViewById(android.R.id.content),
                            "Credenciales inválidas",
                            Snackbar.LENGTH_LONG
                        ).show()
                }

            }
        }

    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Aquí puedes agregar la lógica para verificar las credenciales en una base de datos o cualquier otro método de autenticación
        // En este ejemplo, se asume que el usuario y la contraseña son "admin"
        return username == "admin" && password == "admin"
    }
}

