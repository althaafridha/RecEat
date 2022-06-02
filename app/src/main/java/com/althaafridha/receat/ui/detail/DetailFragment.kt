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
        val data = arguments?.getParcelable<NewRecipeItem>("data")

        data?.name?.let {
            viewModel.getData({
                detailResponse = it
                Log.i("detailactivity", "onCreate: $it")
            }, {
                Log.i("detailactivity", "onCreate: $it")
            }, it)
        }
        data?.let {
            binding.apply {
                Glide.with(this@DetailFragment,).load(data.imageUrl).into(imgDetail)
            }
        }

        binding.btnDetailBack.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}