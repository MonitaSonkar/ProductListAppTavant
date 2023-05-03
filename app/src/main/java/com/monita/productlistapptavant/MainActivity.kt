package com.monita.productlistapptavant

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.monita.productlistapptavant.databinding.ActivityMainBinding
import com.monita.productlistapptavant.model.Product
import com.monita.productlistapptavant.mvvm.ProductViewModel
import com.monita.productlistapptavant.mvvm.ProductViewModelFactory
import com.monita.productlistapptavant.mvvm.Repository
import com.monita.productlistapptavant.network.ProductService
import com.monita.productlistapptavant.network.RetrofitHelper

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val productService= RetrofitHelper.getInstance().create(ProductService::class.java)
        val repository= Repository(productService)
        productViewModel= ViewModelProvider(this, ProductViewModelFactory(repository)).get(ProductViewModel::class.java)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            if(productViewModel.favarray.size>0)
            {
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            else
            {
                Snackbar.make(view, "No item in wishlist", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}


