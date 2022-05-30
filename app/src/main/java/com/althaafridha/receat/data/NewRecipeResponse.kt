package com.althaafridha.receat.ui

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewRecipeResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class ResultsItem(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null
) : Parcelable
