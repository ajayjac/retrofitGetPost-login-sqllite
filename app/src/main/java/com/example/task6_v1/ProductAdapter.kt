package com.example.task6_v1

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.task6_v1.dataclass.Rating
import com.example.task6_v1.dataclass.ResponceProduct
import com.example.task6_v1.ui.DetailsActivity
import com.google.android.material.card.MaterialCardView
import java.lang.reflect.Array.get

class ProductAdapter(val context: Context, val productList: ArrayList<ResponceProduct>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    //id setting
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageBox : ImageView = itemView.findViewById(R.id.imageHome)
        val title: TextView = itemView.findViewById((R.id.titleHome))
        val price: TextView = itemView.findViewById((R.id.titlePrice))
        val card: MaterialCardView = itemView.findViewById(R.id.cardview)
        var rating: RatingBar = itemView.findViewById(R.id.rBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = productList[position]
        Log.d("Adapter", "onBindViewHolder: "+product)
       // product.image?.let { holder.imageBox.setImageResource(it.toInt()) } // wrong use Glide for image set

        Glide.with(holder.itemView.context).load(product.image).into(holder.imageBox)
        holder.price.text = product.price
        holder.title.text = product.title
        var floatRate = product.rating?.rate?.toFloat()
        if (floatRate != null) {
            holder.rating.rating = floatRate
        }

        /*Log.d("id", "onBindViewHolder: "+ product.id)
        Log.d("price", "onBindViewHolder: "+ product.price)
        Log.d("title", "onBindViewHolder: "+ product.title)*/

        holder.card.setOnClickListener{

        val model = productList.get(position)
            var gTitle : String? = model.title
            var gDes : String? = model.description
            var gImage : String? = model.image
            var gPrice : String? = model.price
            var gCategory : String?= model.category
            var gCount : Int?= model.rating?.count
            var gCountInt = gCount.toString()
            var gRating : String? = model.rating?.rate

            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("gTitle",gTitle)
            intent.putExtra("gDes",gDes)
            intent.putExtra("gImage",gImage)
            intent.putExtra("gPrice",gPrice)
            intent.putExtra("gCount",gCountInt)
            intent.putExtra("gRating",gRating)
            intent.putExtra("gCategory",gCategory)

            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return productList.size    }

}
