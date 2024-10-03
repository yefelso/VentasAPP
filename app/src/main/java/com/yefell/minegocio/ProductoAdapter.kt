package com.yefell.minegocio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter(private val productos: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProducto: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val textViewNombreProducto: TextView = itemView.findViewById(R.id.textViewNombreProducto)
        val textViewPrecioProducto: TextView = itemView.findViewById(R.id.textViewPrecioProducto)
        val textViewStockProducto: TextView = itemView.findViewById(R.id.textViewStockProducto)
        val buttonComprar: Button = itemView.findViewById(R.id.buttonComprar) // Referencia al botón
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.textViewNombreProducto.text = producto.nombre
        holder.textViewPrecioProducto.text = "Precio: ${producto.precio}"
        holder.textViewStockProducto.text = "Stock: ${producto.stock}"

        // Usar Glide para cargar la imagen del producto
        Glide.with(holder.itemView.context)
            .load(producto.imagen)
            .into(holder.imageViewProducto)

        // Acción al hacer clic en el botón "Comprar"
        holder.buttonComprar.setOnClickListener {
            // Aquí puedes realizar la acción de compra
            Toast.makeText(holder.itemView.context, "Compraste ${producto.nombre}", Toast.LENGTH_SHORT).show()

            // O puedes iniciar otra actividad, o gestionar la compra
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}
