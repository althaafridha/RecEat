package com.althaafridha.receat.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaafridha.receat.R
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.data.ResultsItem
import com.althaafridha.receat.databinding.FragmentHomeBinding
import com.althaafridha.receat.ui.HeadlineAdapter
import com.althaafridha.receat.ui.MainViewModel
import com.althaafridha.receat.ui.RecipeAdapter
import com.althaafridha.receat.utils.OnItemClickCallback
import com.althaafridha.receat.utils.OnItemClickCallbackHead

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding as FragmentHomeBinding

	private var _viewModel: MainViewModel? = null
	private val viewModel get() = _viewModel as MainViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		_binding = FragmentHomeBinding.inflate(layoutInflater)

		setUpSortByMenu()

		_viewModel = ViewModelProvider(this)[MainViewModel::class.java]

		viewModel.getNewRecipe()
		viewModel.isLoading.observe(viewLifecycleOwner) { showLoading(it) }
		viewModel.isError.observe(viewLifecycleOwner) { showError(it) }
		viewModel.recipeResponse.observe(viewLifecycleOwner) {
			showData(it.result, null)
		}
		viewModel.headResponse.observe(viewLifecycleOwner) {
			showData(null, it.results as List<ResultsItem>?)
		}

		return binding.root
	}

	private fun showData(data: List<NewRecipeItem>?, headlineData: List<ResultsItem>?) {
		binding.recyclerView.apply {
			val mAdapter = RecipeAdapter()

			mAdapter.setData(data)
			layoutManager = LinearLayoutManager(context)
			adapter = mAdapter
			mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
				override fun onItemClicked(item: NewRecipeItem) {
					val bundle = Bundle()
					bundle.putString("RECIPE_NAME", item.name)
					findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
				}
			})
		}

		binding.rvHead.apply {
			val mAdapter = HeadlineAdapter()
			mAdapter.setData(headlineData)
			layoutManager = LinearLayoutManager(context)
			adapter = mAdapter
			mAdapter.setOnItemClickCallback(object : OnItemClickCallbackHead {
				override fun onItemClicked(item: ResultsItem) {
					val bundle = Bundle()
					bundle.putString("RECIPE_NAME", item.title)
					findNavController(binding.root).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
				}
			})
		}


	}

	private fun setUpSortByMenu() {
		binding.svSearch.setOnQueryTextListener(object :
			androidx.appcompat.widget.SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				viewModel.getNewRecipeBySearch(query)
				binding.rvHead.visibility = View.GONE
				viewModel.recipeResponse.observe (viewLifecycleOwner) {
					showData(it.result, null    )
				}
				return false
			}

			override fun onQueryTextChange(newText: String): Boolean {
				viewModel.getNewRecipeBySearch(newText)
				binding.svSearch.visibility = View.VISIBLE
				viewModel.recipeResponse.observe (viewLifecycleOwner) {
					showData(it.result, null)
				}
				viewModel.recipeResponse.observe (viewLifecycleOwner) {
					showData(it.result, null)
				}
				return false
			}

		})

		binding.svSearch.setOnQueryTextFocusChangeListener { _, b ->
			val constraintSet = ConstraintSet()
			if (b) {
				binding.rvHead.visibility = View.GONE
				constraintSet.clone(binding.constraintHome)
				constraintSet.connect(
					binding.recyclerView.id,
					ConstraintSet.TOP,
					binding.cvSearch.id,
					ConstraintSet.BOTTOM
				)
			} else {
				binding.rvHead.visibility = View.VISIBLE
				constraintSet.clone(binding.constraintHome)
				constraintSet.connect(
					binding.recyclerView.id,
					ConstraintSet.TOP,
					binding.cvSearch.id,
					ConstraintSet.BOTTOM
				)
			}
			constraintSet.applyTo(binding.constraintHome)

		}
	}

	private fun showError(isError: Throwable?) {
		Log.e("MainActivity", "Error get data $isError")
	}

	private fun showLoading(isLoading: Boolean?) {
		if (isLoading == true) {
			binding.progressMain.visibility = View.VISIBLE
			binding.recyclerView.visibility = View.INVISIBLE
		} else {
			binding.progressMain.visibility = View.INVISIBLE
			binding.recyclerView.visibility = View.VISIBLE
		}
	}
}