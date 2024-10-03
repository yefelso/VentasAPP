package com.yefell.minegocio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index)

        // Botón para agregar productos
        val buttonAgregarProducto = findViewById<Button>(R.id.buttonAgregarProducto)
        buttonAgregarProducto.setOnClickListener {
            val intent = Intent(this, CreateProductActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir al catálogo
        val buttonIrCatalogo = findViewById<Button>(R.id.buttonIrCatalogo)
        buttonIrCatalogo.setOnClickListener {
            val intent = Intent(this, CatalogoActivity::class.java)
            startActivity(intent)
        }
    }
}
