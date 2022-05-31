package com.althaafridha.receat.data.network

import com.althaafridha.receat.ui.NewRecipeResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

	@GET("/api/recipes")
	fun getNewRecipe(): Flowable<List<NewRecipeResponse>>
}