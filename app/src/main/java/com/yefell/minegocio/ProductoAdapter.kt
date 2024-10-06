package com.yefell.minegocio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onEditClick: (Producto) -> Unit,
    private val onDeleteClick: (Producto) -> Unit,
    private val isCrudMode: Boolean // Modo CRUD para decidir qué layout usar
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    // Clase ViewHolder para manejar la vista de cada producto
    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombreProducto)
        val textViewPrecio: TextView = itemView.findViewById(R.id.textViewPrecioProducto)
        val textViewStock: TextView = itemView.findViewById(R.id.textViewStockProducto)
        val buttonEditar: Button? = itemView.findViewById(R.id.buttonEditar)
        val buttonEliminar: Button? = itemView.findViewById(R.id.buttonEliminar)
    }

    // Método para crear el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        // Decidir qué layout inflar basado en el modo CRUD
        val layout = if (isCrudMode) R.layout.item_crud else R.layout.item_producto
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ProductoViewHolder(view)
    }

    // Método para vincular los datos al ViewHolder
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.textViewNombre.text = producto.nombre
        holder.textViewPrecio.text = String.format("$%.2f", producto.precio)
        holder.textViewStock.text = producto.stock.toString()

        // Solo mostrar los botones de editar y eliminar si estamos en modo CRUD
        if (isCrudMode) {
            holder.buttonEditar?.setOnClickListener {
                onEditClick(producto)
            }

            holder.buttonEliminar?.setOnClickListener {
                onDeleteClick(producto)
            }
        }
    }

    // Método para obtener el número de ítems en la lista
    override fun getItemCount(): Int {
        return productos.size
    }

    // Método para actualizar la lista de productos
    fun actualizarProductos(productosActualizados: List<Producto>) {
        (productos as MutableList).clear()
        productos.addAll(productosActualizados)
        notifyDataSetChanged()
    }
}
