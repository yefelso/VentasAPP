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
    private val onDeleteClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    // Clase ViewHolder para manejar la vista de cada producto
    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombreProducto)
        val textViewPrecio: TextView = itemView.findViewById(R.id.textViewPrecioProducto)
        val textViewStock: TextView = itemView.findViewById(R.id.textViewStockProducto)
        val buttonEditar: Button = itemView.findViewById(R.id.buttonEditar)
        val buttonEliminar: Button = itemView.findViewById(R.id.buttonEliminar)
    }

    // Método para crear el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    // Método para vincular los datos al ViewHolder
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.textViewNombre.text = producto.nombre
        holder.textViewPrecio.text = String.format("$%.2f", producto.precio)
        holder.textViewStock.text = producto.stock.toString()

        // Manejar clic en el botón de editar
        holder.buttonEditar.setOnClickListener {
            onEditClick(producto)
        }

        // Manejar clic en el botón de eliminar
        holder.buttonEliminar.setOnClickListener {
            onDeleteClick(producto)
        }
    }

    // Método para obtener el número de ítems en la lista
    override fun getItemCount(): Int {
        return productos.size
    }
}
