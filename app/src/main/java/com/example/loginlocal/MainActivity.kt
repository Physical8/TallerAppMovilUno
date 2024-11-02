package com.example.loginlocal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var usuario: EditText
    private lateinit var contrasenia: EditText
    private lateinit var ingresar: Button
    private lateinit var registrar: Button

    object UserStorage {
        val usuariosRegistrados = mutableListOf<Usuario>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Instanciar las variables con los objetos de la vista
        usuario = findViewById(R.id.editTextTextUsuarioRegistro)
        contrasenia = findViewById(R.id.editTextTextPasswordRegistro)
        ingresar = findViewById(R.id.buttonIngresar)
        registrar = findViewById(R.id.buttonRegistrarse)

        ingresar.setOnClickListener { login() }
        registrar.setOnClickListener {
            // Al hacer clic en el botón "Registrar", se redirige a la actividad de registro
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val usuarioText = usuario.text.toString()
        val contraseniaText = contrasenia.text.toString()

        if (usuarioText.isNotEmpty() && contraseniaText.isNotEmpty()) {
            val usuarioValido = UserStorage.usuariosRegistrados.any {
                it.nombre == usuarioText && it.contrasenia == contraseniaText
            }

            if (usuarioValido) {
                val intento = Intent(this, Index::class.java)
                startActivity(intento)
            } else {
                Toast.makeText(this, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Los campos Usuario y Contraseña son obligatorios", Toast.LENGTH_LONG).show()
        }
    }
}
