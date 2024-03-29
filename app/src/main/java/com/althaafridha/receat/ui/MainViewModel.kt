package com.althaafridha.receat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaafridha.receat.data.Dummy
import com.althaafridha.receat.data.network.ApiClient
import com.althaafridha.receat.data.response.RecipeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

	var isLoading = MutableLiveData<Boolean>()
	var isError = MutableLiveData<Throwable>()
	var recipeResponse = MutableLiveData<RecipeResponse>()
	var headResponse = MutableLiveData<RecipeResponse>()

	fun getData(responseHandler: (RecipeResponse) -> Unit, errorHandler: (Throwable) -> Unit) {
		ApiClient.getApiService().getNewRecipe(Dummy.listCategory)
			// membuat background thread / proses asynchronous
			.subscribeOn(Schedulers.io())
			// menentukan dimana thread akan dibuat
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				responseHandler(it)
			}, {
				errorHandler(it)
			})
	}

	fun getDataByQuery(responseHandler: (RecipeResponse) -> Unit, errorHandler: (Throwable) -> Unit, query: String) {
		ApiClient.getApiService().getRecipeBySearch(query)
			// membuat background thread / proses asynchronous
			.subscribeOn(Schedulers.io())
			// menentukan dimana thread akan dibuat
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				responseHandler(it)
			}, {
				errorHandler(it)
			})
	}

	fun getHeadData(responseHandler: (RecipeResponse) -> Unit, errorHandler: (Throwable) -> Unit) {
		ApiClient.getApiService().getHeadRecipe(Dummy.listCategory)
			// membuat background thread / proses asynchronous
			.subscribeOn(Schedulers.io())
			// menentukan dimana thread akan dibuat
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				responseHandler(it)
			}, {
				errorHandler(it)
			})
	}

	fun getNewRecipe() {
		isLoading.value = true
		getData({
			isLoading.value = false
			recipeResponse.value = it
		}, {
			isLoading.value = false
			isError.value = it
		})
	}

	fun getNewRecipeBySearch(query: String) {
		isLoading.value = true
		getDataByQuery({
			isLoading.value = false
			recipeResponse.value = it
		}, {
			isLoading.value = false
			isError.value = it
		}, query)
	}

	fun getHeadRecipe() {
		isLoading.value = true
		getHeadData({
			isLoading.value = false
			headResponse.value = it
		}, {
			isLoading.value = false
			isError.value = it
		})
	}

}