package com.althaafridha.receat.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaafridha.receat.R
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.databinding.FragmentHomeBinding
import com.althaafridha.receat.ui.MainViewModel
import com.althaafridha.receat.ui.RecipeAdapter
import com.althaafridha.receat.ui.detail.DetailFragment
import com.althaafridha.receat.utils.OnItemClickCallback

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding as FragmentHomeBinding

	private var _viewModel: MainViewModel? = null
	private val viewModel get() = _viewModel as MainViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		_binding = FragmentHomeBinding.inflate(layoutInflater)

		setUpSortByMenu()

		_viewModel = ViewModelProvider(this)[MainViewModel::class.java]

		viewModel.getNewRecipe()
		viewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
		viewModel.isError.observe(viewLifecycleOwner) { showError(it) }
		viewModel.recipeResponse.observe(viewLifecycleOwner) {
			showData(it.result)
		}

		return binding.root
	}
	private fun showData(data: List<NewRecipeItem>?) {
		binding.recyclerView.apply {
			val mAdapter = RecipeAdapter()
			mAdapter.setData(data)
			layoutManager = LinearLayoutManager(context)
			adapter = mAdapter
			mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
				override fun onItemClicked(item: NewRecipeItem) {
					val bundle = Bundle()
					bundle.putParcelable("data", item)
					findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
				}
			})
		}
	}

	fun setUpSortByMenu() {
		binding.svSearch.setOnQueryTextListener(object :
			androidx.appcompat.widget.SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				viewModel.getNewRecipeBySearch(query!!)
				viewModel.recipeResponse.observe (viewLifecycleOwner) {
					showData(it.result)
				}
				return false
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				viewModel.getNewRecipeBySearch(newText!!)
				viewModel.recipeResponse.observe (viewLifecycleOwner) {
					showData(it.result)
				}
				return false
			}

		})
	}

	private fun showError(isError: Throwable?) {
		Log.e("MainActivity", "Error get data $isError")
	}

	private fun showLoading(isLoading: Boolean?) {
		if (isLoading == true) {
			binding.progressMain.visibility = View.VISIBLE
			binding.recyclerView.visibility = View.INVISIBLE
			binding.rectangle2.visibility = View.INVISIBLE
		} else {
			binding.progressMain.visibility = View.INVISIBLE
			binding.recyclerView.visibility = View.VISIBLE
			binding.rectangle2.visibility = View.VISIBLE
		}
	}
}