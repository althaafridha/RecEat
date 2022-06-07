package com.althaafridha.receat.data.network

import com.althaafridha.receat.data.DetailResponse
import com.althaafridha.receat.data.HeadRecipeResponse
import com.althaafridha.receat.data.NewRecipeResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("api/recipes")
	fun getNewRecipe(): Flowable<NewRecipeResponse>

	@GET("/api/recipe/{key}")
	fun getDetailRecipe(
		@Path("key") key: String
	): Flowable<DetailResponse>

	@GET("/api/search")
	fun getRecipeBySearch(
		@Query("q") key: String
	): Flowable<NewRecipeResponse>

	@GET("/api/recipes/1")
	fun getHeadRecipe(): Flowable<HeadRecipeResponse>

}