package com.althaafridha.receat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.althaafridha.receat.R
import com.althaafridha.receat.databinding.ActivityMainBinding
import com.althaafridha.receat.notification.NotificationService
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

	private var _binding: ActivityMainBinding? = null
	private val binding get() = _binding as ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		NotificationService().scheduleNotification(applicationContext, "Ada masakan baru nih", "Cek dulu")

//		val navView: BottomNavigationView = binding.navView
//
//		val bottomNavController = findNavController(R.id.nav_host_fragment_container)
//		val appBarConfiguration =
//			AppBarConfiguration(setOf(R.id.homeFragment, R.id.profileFragment, ))

//		navView.setupWithNavController(bottomNavController)
//		binding.navView.selectedItemId = R.id.nav_host_fragment_container
//
//		supportActionBar?.hide()

	}

//	override fun onSupportNavigateUp(): Boolean {
//		val navController = findNavController(binding.navView)
//		return super.onSupportNavigateUp() || navController.navigateUp()
	}
