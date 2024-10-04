package com.yefell.minegocio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventarioActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var productos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        databaseHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar productos de la base de datos
        loadProductos()

        // Botón para agregar un nuevo producto
        val buttonAgregar = findViewById<Button>(R.id.buttonAgregar)
        buttonAgregar.setOnClickListener {
            val intent = Intent(this, EditarProductoActivity::class.java)
            startActivityForResult(intent, ADD_PRODUCT_REQUEST)
        }
    }

    private fun loadProductos() {
        // Aquí cargarás la lista de productos desde la base de datos
        productos = databaseHelper.obtenerProductos()
        productoAdapter = ProductoAdapter(productos, { producto ->
            // Manejar la edición
            val intent = Intent(this, EditarProductoActivity::class.java).apply {
                putExtra("id", producto.id) // Asegúrate de que producto.id es Int
                putExtra("nombre", producto.nombre)
                putExtra("precio", producto.precio)
                putExtra("stock", producto.stock)
            }
            startActivityForResult(intent, EDIT_PRODUCT_REQUEST)
        }, { producto ->
            // Manejar la eliminación
            eliminarProducto(producto)
        })

        recyclerView.adapter = productoAdapter
    }

    private fun eliminarProducto(producto: Producto) {
        databaseHelper.eliminarProducto(producto.id)
        loadProductos() // Volver a cargar la lista después de eliminar
        Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            loadProductos() // Volver a cargar productos después de agregar
        } else if (requestCode == EDIT_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            loadProductos() // Volver a cargar productos después de editar
        }
    }

    companion object {
        const val ADD_PRODUCT_REQUEST = 1
        const val EDIT_PRODUCT_REQUEST = 2
    }
}
