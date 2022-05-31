package com.althaafridha.receat.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailResponse(

	@field:SerializedName("results")
	val results: Results? = null,
) : Parcelable


@Parcelize
data class NeedItemItem(

	@field:SerializedName("thumb_item")
	val thumbItem: String? = null,

	@field:SerializedName("item_name")
	val itemName: String? = null
) : Parcelable

@Parcelize
data class Results(

	@field:SerializedName("servings")
	val servings: String? = null,

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("ingredient")
	val ingredient: List<String?>? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("step")
	val step: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("needItem")
	val needItem: List<NeedItemItem?>? = null,

	@field:SerializedName("dificulty")
	val dificulty: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable
