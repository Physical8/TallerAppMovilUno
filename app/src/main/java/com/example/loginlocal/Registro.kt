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

class Registro : AppCompatActivity() {
    private lateinit var usuario: EditText
    private lateinit var contrasenia: EditText
    private lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Configuración de ajustes de vista y barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de vistas
        usuario = findViewById(R.id.editTextTextUsuarioRegistro)
        contrasenia = findViewById(R.id.editTextTextPasswordRegistro)
        guardar = findViewById(R.id.buttonGuardarUno)

        // Listener del botón "Guardar" para el registro
        guardar.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        val usuarioText = usuario.text.toString()
        val contraseniaText = contrasenia.text.toString()

        if (usuarioText.isNotEmpty() && contraseniaText.isNotEmpty()) {
            // Agrega el nuevo usuario a la lista global
            MainActivity.UserStorage.usuariosRegistrados.add(Usuario(usuarioText, contraseniaText))
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()

            // Redirigir al usuario a la pantalla de inicio de sesión
            val intento = Intent(this, MainActivity::class.java)
            startActivity(intento)
        } else {
            Toast.makeText(this, "Ambos campos son obligatorios", Toast.LENGTH_LONG).show()
        }
    }
}
