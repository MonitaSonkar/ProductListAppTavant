package com.monita.productlistapptavant
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.monita.productlistapptavant.databinding.FragmentFirstBinding
import com.monita.productlistapptavant.mvvm.ProductViewModel
import com.monita.productlistapptavant.mvvm.ProductViewModelFactory
import com.monita.productlistapptavant.mvvm.Repository
import com.monita.productlistapptavant.network.ProductService
import com.monita.productlistapptavant.network.RetrofitHelper

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    lateinit var productViewModel: ProductViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productService= RetrofitHelper.getInstance().create(ProductService::class.java)
        val repository= Repository(productService)
        productViewModel = activity?.run {
            ViewModelProvider(this, ProductViewModelFactory(repository)).get(ProductViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        productViewModel.favproductList.observe(viewLifecycleOwner, Observer {
            val adapter=ProductrAdapter(it,true,{
                val bundle = bundleOf("product_info" to it)
                findNavController().navigate(R.id.action_SecondFragment_to_productDetailPage,bundle)
            },{
                if(it.isFav==true)
                {
                    productViewModel.updateFav(it)
                }
                else
                {
                    productViewModel.removeFav(it)
                }
            })
            binding.recycleview.adapter=adapter

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

