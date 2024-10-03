package com.yefell.minegocio

data class Producto(
    val id: Int = 0, // Se asigna 0 por defecto si no se proporciona
    val nombre: String,
    val precio: Double,
    val stock: Int,
    val imagen: String
)
