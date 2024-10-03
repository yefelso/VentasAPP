package com.yefell.minegocio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateProductActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var productImageView: ImageView
    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var productStockEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        databaseHelper = DatabaseHelper(this)

        productNameEditText = findViewById(R.id.productNameEditText)
        productPriceEditText = findViewById(R.id.productPriceEditText)
        productStockEditText = findViewById(R.id.productStockEditText)
        productImageView = findViewById(R.id.productImageView)

        val saveProductButton = findViewById<Button>(R.id.saveProductButton)

        // Acción para guardar el producto
        saveProductButton.setOnClickListener {
            saveProduct()
        }
    }

    private fun saveProduct() {
        val name = productNameEditText.text.toString()
        val price = productPriceEditText.text.toString().toDoubleOrNull()
        val stock = productStockEditText.text.toString().toIntOrNull()

        // Obtén la URL de la imagen desde el nuevo EditText
        val productImageUrl = findViewById<EditText>(R.id.productImageUrlEditText).text.toString()

        if (name.isNotEmpty() && price != null && stock != null && productImageUrl.isNotEmpty()) {
            // Crea un objeto Producto
            val product = Producto(nombre = name, precio = price, stock = stock, imagen = productImageUrl)
            databaseHelper.agregarProducto(product) // Guarda el producto en la base de datos

            Toast.makeText(this, "Producto guardado con éxito", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}
