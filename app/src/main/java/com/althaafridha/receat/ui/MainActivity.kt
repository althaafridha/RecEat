package com.althaafridha.receat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.althaafridha.receat.R
import com.althaafridha.receat.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

	private var _binding: ActivityMainBinding? = null
	private val binding get() = _binding as ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}