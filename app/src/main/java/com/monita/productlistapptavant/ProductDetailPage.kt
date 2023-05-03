package com.monita.productlistapptavant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

import com.monita.productlistapptavant.databinding.ProductDetailBinding
import com.monita.productlistapptavant.model.Product

class ProductDetailPage : Fragment() {

    private var _binding: ProductDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product=arguments?.getParcelable<Product>("product_info")
        if(product!=null)
        {
            binding.desc.setText(product.description)
            binding.title.setText(product.title)
            binding.category.setText(product.category)
            binding.ratingCount.text ="(${product.rating?.rate.toString()})"
            binding.price.setText("Rs. ${product.price.toString()}")
            binding.ratingbar.rating= product.rating?.rate ?: 0.00f
            Glide.with(this).load(product.image).into(binding.productImage)
            if (product.isFav != null && product.isFav==true)
            {
                binding.favImg.setImageResource(R.drawable.red_fill_fav)
            }
            else
            {
                binding.favImg.setImageResource(R.drawable.red_unfill_fav)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}