package com.yefell.minegocio

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mi_negocio.db"
        private const val DATABASE_VERSION = 1

        // Constantes para la tabla de usuarios
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nombre_usuario"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "contraseña"

        // Constantes para la tabla de productos
        private const val TABLE_PRODUCTOS = "productos"
        private const val COLUMN_ID_PRODUCTO = "id_producto"
        private const val COLUMN_NOMBRE_PRODUCTO = "nombre"
        private const val COLUMN_PRECIO_PRODUCTO = "precio"
        private const val COLUMN_STOCK_PRODUCTO = "stock" // Cambia 'stok' a 'stock'
        private const val COLUMN_IMAGEN_PRODUCTO = "imagen"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla de usuarios
        val createTableUsers = ("CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createTableUsers)

        // Crear tabla de productos
        val createTableProductos = ("CREATE TABLE $TABLE_PRODUCTOS (" +
                "$COLUMN_ID_PRODUCTO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NOMBRE_PRODUCTO TEXT NOT NULL," +
                "$COLUMN_PRECIO_PRODUCTO REAL NOT NULL," +
                "$COLUMN_STOCK_PRODUCTO INTEGER NOT NULL," + // Cambia 'stok' a 'stock'
                "$COLUMN_IMAGEN_PRODUCTO TEXT)")
        db?.execSQL(createTableProductos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar tablas si ya existen
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        onCreate(db)
    }

    // Métodos para manejar usuarios
    fun verificarUsuario(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?", arrayOf(email, password))
        val exists = cursor.count > 0
        cursor.close() // Cerrar el cursor después de usarlo
        db.close() // Cerrar la base de datos
        return exists
    }

    fun registrarUsuario(usuario: Usuario) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, usuario.nombreUsuario)
            put(COLUMN_EMAIL, usuario.email)
            put(COLUMN_PASSWORD, usuario.contraseña)
        }
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    // Métodos para manejar productos

    // Método para agregar un producto
    fun agregarProducto(producto: Producto) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE_PRODUCTO, producto.nombre) // Asegúrate de que esto esté bien definido
            put(COLUMN_PRECIO_PRODUCTO, producto.precio)
            put(COLUMN_STOCK_PRODUCTO, producto.stock)
            put(COLUMN_IMAGEN_PRODUCTO, producto.imagen)
        }
        db.insert(TABLE_PRODUCTOS, null, values) // Asegúrate de que TABLE_PRODUCTOS esté bien definido
        db.close()
    }

    // Método para obtener todos los productos
    fun obtenerProductos(): List<Producto> {
        val productos = mutableListOf<Producto>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_PRODUCTOS, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_PRODUCTO))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_PRODUCTO))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECIO_PRODUCTO))
                val stock = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STOCK_PRODUCTO)) // Cambia 'stok' a 'stock'
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN_PRODUCTO))
                productos.add(Producto(id, nombre, precio, stock, imagen))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return productos
    }

    // Método para actualizar un producto
    fun actualizarProducto(id: Int, nombre: String, precio: Double, stock: Int, imagen: String) { // Cambia 'stok' a 'stock'
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE_PRODUCTO, nombre)
            put(COLUMN_PRECIO_PRODUCTO, precio)
            put(COLUMN_STOCK_PRODUCTO, stock) // Cambia 'stok' a 'stock'
            put(COLUMN_IMAGEN_PRODUCTO, imagen)
        }
        db.update(TABLE_PRODUCTOS, values, "$COLUMN_ID_PRODUCTO = ?", arrayOf(id.toString()))
        db.close()
    }

    // Método para eliminar un producto
    fun eliminarProducto(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_PRODUCTOS, "$COLUMN_ID_PRODUCTO = ?", arrayOf(id.toString()))
        db.close()
    }
}
