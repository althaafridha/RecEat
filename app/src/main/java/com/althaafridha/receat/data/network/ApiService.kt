package com.althaafridha.receat.data.network

import com.althaafridha.receat.data.response.RecipeResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("search.php")
	fun getNewRecipe(
		@Query("s")
		query: String
	): Flowable<RecipeResponse>

	@GET("lookup.php")
	fun getDetailRecipe(
		@Query("i")
		id: String
	): Flowable<RecipeResponse>

	@GET("search.php?")
	fun getRecipeBySearch(
		@Query("s")
		query: String
	): Flowable<RecipeResponse>

	@GET("search.php")
	fun getHeadRecipe(
		@Query("s")
		query: String
	): Flowable<RecipeResponse>

}