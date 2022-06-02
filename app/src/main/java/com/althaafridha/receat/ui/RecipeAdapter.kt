package com.althaafridha.receat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.althaafridha.receat.data.NewRecipeItem
import com.althaafridha.receat.databinding.RowItemRecBinding
import com.althaafridha.receat.utils.OnItemClickCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class RecipeAdapter() : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {
	private var listNewRecipe = ArrayList<NewRecipeItem>()


	fun setData(data: List<NewRecipeItem>?) {
		if (data == null) return
		listNewRecipe.clear()
		listNewRecipe.addAll(data)
	}

	private var onItemClickCallback: OnItemClickCallback? = null

	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}

	class MyViewHolder(val binding: RowItemRecBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
		RowItemRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
	)

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		val data = listNewRecipe[position]
		holder.binding.apply {
			val splittedData = data.name?.substring(5, data.name.length)?.split("-")?.toTypedArray()
			val joinedData = splittedData?.joinToString(" ")

			itemName.text = joinedData
			Glide.with(itemImg.context)
				.load(data.imageUrl)
				.apply(RequestOptions())
				.override(500, 500)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.priority(Priority.HIGH)
				.into(itemImg)

			holder.itemView.setOnClickListener {
				data.let { item -> onItemClickCallback?.onItemClicked(item) }
			}
		}
	}

	override fun getItemCount() = 3

}