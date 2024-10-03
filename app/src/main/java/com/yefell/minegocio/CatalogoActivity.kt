package com.yefell.minegocio

import android.os.Bundle
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

        // Inicializar la base de datos y el RecyclerView
        databaseHelper = DatabaseHelper(this)
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        // Obtener los productos de la base de datos
        val listaProductos = databaseHelper.obtenerProductos()

        // Inicializar el adaptador con los productos
        productoAdapter = ProductoAdapter(listaProductos)
        recyclerViewProductos.adapter = productoAdapter
    }
}
