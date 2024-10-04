package com.yefell.minegocio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index)

        // Botón para agregar productos
        val buttonAgregarProducto = findViewById<Button>(R.id.buttonAgregarProducto)
        buttonAgregarProducto.setOnClickListener {
            Toast.makeText(this, "Navegando a Crear Producto", Toast.LENGTH_SHORT).show() // Mensaje para ver si se activa el botón
            val intent = Intent(this, CreateProductActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir al catálogo
        val buttonIrCatalogo = findViewById<Button>(R.id.buttonIrCatalogo)
        buttonIrCatalogo.setOnClickListener {
            Toast.makeText(this, "Navegando al Catálogo", Toast.LENGTH_SHORT).show() // Mensaje para ver si se activa el botón
            val intent = Intent(this, CatalogoActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir al inventario
        val buttonIrInventario = findViewById<Button>(R.id.buttonIrInventario)
        buttonIrInventario.setOnClickListener {
            Toast.makeText(this, "Navegando al Inventario", Toast.LENGTH_SHORT).show() // Mensaje para ver si se activa el botón
            val intent = Intent(this, InventarioActivity::class.java)
            startActivity(intent)
        }
    }
}
