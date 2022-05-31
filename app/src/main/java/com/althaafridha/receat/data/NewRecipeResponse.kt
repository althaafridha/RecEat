package com.althaafridha.receat.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewRecipeResponse(

	@field:SerializedName("results")
	val result: List<NewRecipeItem>? = null
): Parcelable

@Parcelize
data class NewRecipeItem(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val imageUrl: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("key")
	val name: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null
) : Parcelable
