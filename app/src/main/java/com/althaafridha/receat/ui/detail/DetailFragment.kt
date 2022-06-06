package com.althaafridha.receat.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.althaafridha.receat.data.DetailResponse
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private var detailResponse: DetailResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val data = arguments?.getString("RECIPE_NAME")

        viewModel.getDetailById(data!!)
        viewModel.isLoading.observe(viewLifecycleOwner) {showLoading(it)}
        viewModel.detailResponse.observe(viewLifecycleOwner){
            initView(it)
        }

//        binding.btnDetailBack.setOnClickListener {
//            activity?.onBackPressed()
//        }
        return binding.root
    }

    private fun initView(it: DetailResponse?) {
        binding.apply {
            tvTitle.text = it?.results?.title ?: "sim"
            Glide.with(requireContext())
                .load(it?.results?.thumb)
                .into(imgDetail)
        }

    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.progressDetail.visibility = View.VISIBLE
            binding.cvDetail.visibility = View.INVISIBLE
            binding.tvTitle.visibility = View.INVISIBLE
            binding.tvDescription.visibility = View.INVISIBLE
        } else {
            binding.progressDetail.visibility = View.INVISIBLE
            binding.cvDetail.visibility = View.VISIBLE
            binding.tvTitle.visibility = View.VISIBLE
            binding.tvDescription.visibility = View.VISIBLE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}