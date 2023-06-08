package com.althaafridha.receat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.althaafridha.receat.data.response.Recipe

import com.althaafridha.receat.databinding.RowItemTodayBinding
import com.althaafridha.receat.utils.OnItemClickCallbackHead
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class HeadlineAdapter : RecyclerView.Adapter<HeadlineAdapter.MyViewHolder>() {
	private var listHeadRecipe = ArrayList<Recipe>()

	fun setData(data: List<Recipe>?) {
		if (data == null) return
		listHeadRecipe.clear()
		listHeadRecipe.addAll(data)
	}

	private var onItemClickCallbackHead: OnItemClickCallbackHead? = null

	fun setOnItemClickCallbackHead(onItemClickCallbackHead: OnItemClickCallbackHead) {
		this.onItemClickCallbackHead = onItemClickCallbackHead
	}

	class MyViewHolder(val binding: RowItemTodayBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
		RowItemTodayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
	)

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val data = listHeadRecipe[position]
		holder.binding.apply {
			val splittedData =
				data.strMeal.substring(5, data.strMeal.length).split("-").toTypedArray()
			val capitalizedData = splittedData.map {
				it.capitalize()
			}
			val joinedData = capitalizedData.joinTo(StringBuilder(), separator = " ")

			titleFood.text = joinedData
			Glide.with(foodImg.context)
				.load(data.strMealThumb)
				.apply(RequestOptions())
				.override(500, 500)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.priority(Priority.HIGH)
				.into(foodImg)

			holder.itemView.setOnClickListener {
				data.let { item -> onItemClickCallbackHead?.onItemClicked(item) }
			}
		}
	}

	override fun getItemCount() = listHeadRecipe.size

}