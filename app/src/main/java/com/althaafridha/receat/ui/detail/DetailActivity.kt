package com.althaafridha.receat.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.althaafridha.receat.data.DetailResponse
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.databinding.ActivityDetailBinding
import com.althaafridha.receat.data.NewRecipeResponse
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var detailResponse: DetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = intent.getParcelableExtra<NewRecipeItem>(EXTRA_DATA)

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
                Glide.with(this@DetailActivity,).load(data.imageUrl).into(imgDetail)
            }
        }

        binding.btnDetailBack.setOnClickListener {
            finish()
        }

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}