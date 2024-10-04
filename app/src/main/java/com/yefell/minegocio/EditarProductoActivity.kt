package com.yefell.minegocio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarProductoActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        databaseHelper = DatabaseHelper(this)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextPrecio = findViewById<EditText>(R.id.editTextPrecio)
        val editTextStock = findViewById<EditText>(R.id.editTextStock)
        val buttonActualizar = findViewById<Button>(R.id.buttonActualizar)

        // Obtener datos del intent
        val id = intent.getIntExtra("id", -1) // Este debe ser un Int
        editTextNombre.setText(intent.getStringExtra("nombre"))
        editTextPrecio.setText(intent.getDoubleExtra("precio", 0.0).toString())
        editTextStock.setText(intent.getIntExtra("stock", 0).toString())

        buttonActualizar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val precio = editTextPrecio.text.toString().toDoubleOrNull()
            val stock = editTextStock.text.toString().toIntOrNull()

            if (precio != null && stock != null) {
                databaseHelper.actualizarProducto(id, nombre, precio, stock, "") // Asegúrate de que id sea Int
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK) // Devuelve el resultado
                finish() // Regresar a la actividad anterior
            } else {
                Toast.makeText(this, "Por favor, ingrese datos válidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
