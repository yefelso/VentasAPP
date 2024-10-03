package com.yefell.minegocio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    lateinit var databaseHelper: DatabaseHelper

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
        val adapter = ProductoAdapter(productos)
        recyclerViewProductos.adapter = adapter
    }
}
