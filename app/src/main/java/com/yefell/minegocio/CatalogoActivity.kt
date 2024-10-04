package com.yefell.minegocio

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class CatalogoActivity : AppCompatActivity() {

    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        databaseHelper = DatabaseHelper(this)
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        try {
            // Obtener la lista de productos desde la base de datos
            val listaProductos = databaseHelper.obtenerProductos()

            // Configurar el adaptador con las funciones de edición y eliminación
            productoAdapter = ProductoAdapter(listaProductos,
                onEditClick = { producto ->
                    // Acción al hacer clic en editar
                    editarProducto(producto)
                },
                onDeleteClick = { producto ->
                    // Acción al hacer clic en eliminar
                    eliminarProducto(producto)
                }
            )
            recyclerViewProductos.adapter = productoAdapter
            Log.d("CatalogoActivity", "Productos cargados correctamente: ${listaProductos.size} productos encontrados.")
        } catch (e: Exception) {
            Log.e("CatalogoActivity", "Error al cargar productos: ${e.message}")
        }
    }

    private fun editarProducto(producto: Producto) {
        // Implementar lógica para editar el producto
        Toast.makeText(this, "Editar producto: ${producto.nombre}", Toast.LENGTH_SHORT).show()
        // Aquí podrías abrir un nuevo Activity o un diálogo para editar el producto
    }

    private fun eliminarProducto(producto: Producto) {
        // Implementar lógica para eliminar el producto
        val success = databaseHelper.eliminarProducto(producto.id) // Asumiendo que 'id' es la propiedad del producto
        if (success) {
            Toast.makeText(this, "Producto eliminado: ${producto.nombre}", Toast.LENGTH_SHORT).show()
            // Actualizar la lista de productos después de eliminar
            val listaProductos = databaseHelper.obtenerProductos()
            productoAdapter = ProductoAdapter(listaProductos,
                onEditClick = { producto -> editarProducto(producto) },
                onDeleteClick = { producto -> eliminarProducto(producto) }
            )
            recyclerViewProductos.adapter = productoAdapter
        } else {
            Toast.makeText(this, "Error al eliminar el producto", Toast.LENGTH_SHORT).show()
        }
    }
}
