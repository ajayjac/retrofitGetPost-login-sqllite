package com.example.task6_v1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task6_v1.dataclass.ResponceProduct

class ProductAdapter(private val productList: ArrayList<ResponceProduct>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    //id setting
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBox : ImageView = itemView.findViewById(R.id.imageHome)
        val title: TextView = itemView.findViewById((R.id.titleHome))
        val price: TextView = itemView.findViewById((R.id.titlePrice))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
       /* holder.imageView.setImageResource(ResponseItem.image)
        holder.textView.text = ResponseItem.title*/

        /*holder.imageView.setImageResource(ResponseItem.image)
        holder.textView.text = ResponseItem.title*/

       /* holder.price.text = product.price
        holder.title.text = product.title*/
    }

    override fun getItemCount(): Int {
        return productList.size    }

}
