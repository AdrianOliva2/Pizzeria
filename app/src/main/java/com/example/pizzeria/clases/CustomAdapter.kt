package com.example.pizzeria.clases

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.ConfirmacionPedidoActivity
import com.example.pizzeria.R
import java.util.TreeMap

class CustomAdapter(private val context: Context, private var pizzas: Map<String, Pizza>, private val usuario: Usuario?): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt = itemView.findViewById<TextView>(R.id.textViewRowItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pizzas.keys.toList().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Item(pizzas.keys.toList()[position])
        holder.txt.text = item.getTxtPizza()

        holder.txt.setOnClickListener {
            val item: String = pizzas.keys.toList()[position]
            val pizza: Pizza? = pizzas[item]
            if (pizza != null) {
                val intent = Intent(context, ConfirmacionPedidoActivity::class.java)
                intent.putExtra("pizza", pizza)
                intent.putExtra("usuario", usuario)
                context.startActivity(intent)
            }
        }
    }

}