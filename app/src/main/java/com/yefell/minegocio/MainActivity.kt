package com.yefell.minegocio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazar elementos de la interfaz con el código
        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val sloganTextView: TextView = findViewById(R.id.sloganTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val startOrderButton: Button = findViewById(R.id.button3)

        // Acción cuando se presiona el botón "Comenzar Pedido"
        startOrderButton.setOnClickListener {
            // Redirigir a la actividad de Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            // Mostrar un mensaje (opcional)
            Toast.makeText(this, "¡Vamos a comenzar tu pedido!", Toast.LENGTH_SHORT).show()
        }
    }
}
