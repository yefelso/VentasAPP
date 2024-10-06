package com.yefell.minegocio

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var listaProductos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        databaseHelper = DatabaseHelper(this)
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        // Cargar productos de la base de datos
        cargarProductos()

        // Botón para agregar un nuevo producto
        val buttonAgregar = findViewById<Button>(R.id.buttonAgregarProducto) // Cambia a buttonAgregarProducto
        buttonAgregar.setOnClickListener {
            // Lógica para agregar un nuevo producto
        }
    }

    private fun cargarProductos() {
        try {
            // Obtener la lista de productos desde la base de datos
            listaProductos = databaseHelper.obtenerProductos()
            // Configurar el adaptador con isCrudMode como true para permitir editar y eliminar
            productoAdapter = ProductoAdapter(listaProductos, isCrudMode = true,
                onEditClick = { producto -> editarProducto(producto) },
                onDeleteClick = { producto -> eliminarProducto(producto) }
            )
            recyclerViewProductos.adapter = productoAdapter
            Log.d("ProductosActivity", "Productos cargados correctamente: ${listaProductos.size} productos encontrados.")
        } catch (e: Exception) {
            Log.e("ProductosActivity", "Error al cargar productos: ${e.message}")
            Toast.makeText(this, "Error al cargar productos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editarProducto(producto: Producto) {
        // Implementar lógica para editar el producto
        Toast.makeText(this, "Editar producto: ${producto.nombre}", Toast.LENGTH_SHORT).show()
    }

    private fun eliminarProducto(producto: Producto) {
        Log.d("ProductosActivity", "Eliminando producto con ID: ${producto.id}") // Verifica el ID que se está usando
        val success = databaseHelper.eliminarProducto(producto.id)
        if (success) {
            Toast.makeText(this, "Producto eliminado: ${producto.nombre}", Toast.LENGTH_SHORT).show()
            cargarProductos() // Actualiza la lista de productos
        } else {
            Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show()
        }
    }


}
