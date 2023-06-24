package com.example.pc02_martel_18100889

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pc02_martel_18100889.Model.RegistroModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        val txtDni: EditText = findViewById(R.id.txtDni)
        val txtNombre: EditText = findViewById(R.id.txtNombre)
        val txtClave: EditText = findViewById(R.id.txtClave)
        val txtClave2: EditText = findViewById(R.id.txtClave2)
        val btnSaveRegister: Button = findViewById(R.id.btnCrear)
        //val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")

        btnSaveRegister.setOnClickListener {
            val Dni = txtDni.text.toString()
            val Nombre = txtNombre.text.toString()
            val Clave = txtClave.text.toString()
            val Clave2 = txtClave2.text.toString()
            val nuevoRegistro = RegistroModel(Dni, Nombre, Clave, Clave2)
            collectionRef.add(nuevoRegistro)
                .addOnSuccessListener { documentReference ->
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Registro de curso exitoso: ID-> ${documentReference.id}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

