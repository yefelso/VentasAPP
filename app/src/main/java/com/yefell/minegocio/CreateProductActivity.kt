package com.yefell.minegocio

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
    private var productImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        databaseHelper = DatabaseHelper(this)

        productNameEditText = findViewById(R.id.productNameEditText)
        productPriceEditText = findViewById(R.id.productPriceEditText)
        productStockEditText = findViewById(R.id.productStockEditText)
        productImageView = findViewById(R.id.productImageView)

        val uploadImageButton = findViewById<Button>(R.id.uploadImageButton)
        val saveProductButton = findViewById<Button>(R.id.saveProductButton)

        // Acción para subir imagen
        uploadImageButton.setOnClickListener {
            openImageChooser()
        }

        // Acción para guardar el producto
        saveProductButton.setOnClickListener {
            saveProduct()
        }
    }

    private fun openImageChooser() {
        // Abre el selector de imágenes
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            productImageUri = data.data
            productImageView.setImageURI(productImageUri) // Muestra la imagen seleccionada
        } else {
            Toast.makeText(this, "Error al seleccionar la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveProduct() {
        val name = productNameEditText.text.toString()
        val price = productPriceEditText.text.toString().toDoubleOrNull()
        val stock = productStockEditText.text.toString().toIntOrNull()

        if (name.isNotEmpty() && price != null && stock != null && productImageUri != null) {
            // Crea un objeto Producto
            val product = Producto(nombre = name, precio = price, stock = stock, imagen = productImageUri.toString())
            databaseHelper.agregarProducto(product) // Guarda el producto en la base de datos

            Toast.makeText(this, "Producto guardado con éxito", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
