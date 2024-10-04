package com.yefell.minegocio

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
            val listaProductos = databaseHelper.obtenerProductos()

            productoAdapter = ProductoAdapter(listaProductos)
            recyclerViewProductos.adapter = productoAdapter
            Log.d("CatalogoActivity", "Productos cargados correctamente: ${listaProductos.size} productos encontrados.")
        } catch (e: Exception) {
            Log.e("CatalogoActivity", "Error al cargar productos: ${e.message}")
        }
    }
}
