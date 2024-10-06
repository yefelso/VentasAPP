package com.yefell.minegocio

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatalogoActivity : AppCompatActivity() {

    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var listaProductos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        databaseHelper = DatabaseHelper(this)
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        try {
            // Obtener la lista de productos desde la base de datos
            cargarProductos()
            Log.d("CatalogoActivity", "Productos cargados correctamente: ${listaProductos.size} productos encontrados.")
        } catch (e: Exception) {
            Log.e("CatalogoActivity", "Error al cargar productos: ${e.message}")
            Toast.makeText(this, "Error al cargar productos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarProductos() {
        // Cargar la lista de productos desde la base de datos
        listaProductos = databaseHelper.obtenerProductos()
        // Configurar el adaptador solo para mostrar productos, isCrudMode se establece en false
        productoAdapter = ProductoAdapter(listaProductos, isCrudMode = false,
            onEditClick = { producto ->
                // Aquí puedes implementar la lógica si es necesario más adelante
            },
            onDeleteClick = { producto ->
                // Aquí puedes implementar la lógica si es necesario más adelante
            }
        )
        recyclerViewProductos.adapter = productoAdapter
    }
}
