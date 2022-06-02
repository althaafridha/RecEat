package com.althaafridha.receat.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaafridha.receat.R
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.databinding.ActivityMainBinding
import com.althaafridha.receat.ui.detail.DetailFragment
import com.althaafridha.receat.utils.OnItemClickCallback


class MainActivity : AppCompatActivity() {

	private var _binding: ActivityMainBinding? = null
	private val binding get() = _binding as ActivityMainBinding


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)


	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(binding.navHostFragmentContainer)
		return super.onSupportNavigateUp() || navController.navigateUp()
	}

}