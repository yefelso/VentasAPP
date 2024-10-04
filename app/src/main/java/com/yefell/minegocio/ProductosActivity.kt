package com.yefell.minegocio

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        databaseHelper = DatabaseHelper(this)

        // Configurar RecyclerView
        val recyclerViewProductos = findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        // Obtener los productos de la base de datos
        val productos = databaseHelper.obtenerProductos()

        // Crear el adaptador y asignarlo al RecyclerView
        val adapter = ProductoAdapter(
            productos = productos,
            onEditClick = { producto ->
                // Manejar la lógica de edición del producto
                Log.d("ProductosActivity", "Editar producto: ${producto.nombre}")
                // Aquí podrías abrir una actividad o un diálogo para editar el producto
            },
            onDeleteClick = { producto ->
                // Manejar la lógica de eliminación del producto
                Log.d("ProductosActivity", "Eliminar producto: ${producto.nombre}")
                // Aquí podrías mostrar un diálogo de confirmación y luego eliminar el producto de la base de datos
            }
        )

        recyclerViewProductos.adapter = adapter
    }
}
