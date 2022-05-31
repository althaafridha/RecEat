package com.althaafridha.receat.ui

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewRecipeResponse(

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
