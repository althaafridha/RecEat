package com.althaafridha.receat.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.althaafridha.receat.data.DetailResponse
import com.althaafridha.receat.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentDetailBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val data = arguments?.getString("RECIPE_NAME")

        viewModel.getDetailById(data!!)
        viewModel.isLoading.observe(viewLifecycleOwner) {showLoading(it)}
        viewModel.detailResponse.observe(viewLifecycleOwner){
            initView(it)
        }
        return binding.root
    }

    private fun initView(it: DetailResponse?) {
        binding.apply {
            var result = ""
            for (i in it?.results?.ingredient?.indices!!) {
                result += it.results.ingredient[i] + "\n"
            }
            val title = it.results.title?.split(",")?.get(0)
            val formattedTitle = title?.replace("Resep ", "")
            tvTitle.text = formattedTitle
            Glide.with(requireContext())
                .load(it.results.thumb)
                .into(imgDetail)
            tvTimes.text = it.results.times ?: "-"
            tvDifficulty.text = it.results.dificulty ?: "-"
            tvPortion.text = it.results.servings ?: "-"
            tvDetail.text = result

        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.progressDetail.visibility = View.VISIBLE
            binding.cvDetail.visibility = View.INVISIBLE
            binding.tvTitle.visibility = View.INVISIBLE
            binding.tvTimes.visibility = View.INVISIBLE
            binding.tvDifficulty.visibility = View.INVISIBLE
            binding.tvDetail.visibility = View.INVISIBLE
            binding.tvPortion.visibility = View.INVISIBLE
        } else {
            binding.progressDetail.visibility = View.INVISIBLE
            binding.cvDetail.visibility = View.VISIBLE
            binding.tvTitle.visibility = View.VISIBLE
            binding.tvTimes.visibility = View.VISIBLE
            binding.tvDifficulty.visibility = View.VISIBLE
            binding.tvDetail.visibility = View.VISIBLE
            binding.tvPortion.visibility = View.VISIBLE
        }
    }

}