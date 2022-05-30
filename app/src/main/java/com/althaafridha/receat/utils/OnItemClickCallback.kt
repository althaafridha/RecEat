package com.althaafridha.receat.utils

import com.althaafridha.receat.data.NewRecipeResponse

interface OnItemClickCallback {

	fun onItemClicked(item: NewRecipeResponse)
}