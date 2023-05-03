package com.monita.productlistapptavant

import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monita.productlistapptavant.databinding.ProductItemviewBinding
import com.monita.productlistapptavant.model.Product

class ProductrAdapter(val productList: List<Product>,var isWhishlist:Boolean,var onItemClicked: ((productItem: Product) -> Unit),var onUpdateFavList: ((productItem: Product) -> Unit)):RecyclerView.Adapter<ProductrAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.product_itemview,parent,false)
        return MyViewHolder(view);
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemModel=productList[position]
        holder.productName.setText(itemModel.title)
        holder.productCategory.setText(itemModel.category)
        holder.productPrice.setText("Rs. ${itemModel.price.toString()}")
        if (itemModel.isFav != null && itemModel.isFav==true)
        {
            holder.favItem.setImageResource(R.drawable.red_fill_fav)
        }
        else
        {
            holder.favItem.setImageResource(R.drawable.red_unfill_fav)
        }
        holder.favItem.setOnClickListener(View.OnClickListener {
            if (itemModel.isFav != null && itemModel.isFav==true)
            {
                holder.favItem.setImageResource(R.drawable.red_unfill_fav)
                itemModel.isFav=false
                onUpdateFavList(itemModel)
            }
            else
            {
                holder.favItem.setImageResource(R.drawable.red_fill_fav)
                itemModel.isFav=true
                onUpdateFavList(itemModel)
            }
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClicked(itemModel)
        })
        holder.ratingBar.rating= itemModel.rating?.rate ?: 0.00f

        Glide.with(holder.itemView.context).load(itemModel.image).into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_img)
        val productName: TextView = itemView.findViewById(R.id.title)
        val productCategory: TextView = itemView.findViewById(R.id.category)
        val productPrice: TextView = itemView.findViewById(R.id.price)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingbar)
        val favItem: ImageView = itemView.findViewById(R.id.fav_img)
    }

}

