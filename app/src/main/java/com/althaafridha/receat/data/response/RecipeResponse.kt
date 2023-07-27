package com.althaafridha.receat.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(

	@field:SerializedName("meals")
	val meals: List<Recipe>
) : Parcelable