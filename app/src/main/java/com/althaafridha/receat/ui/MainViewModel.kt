package com.althaafridha.receat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaafridha.receat.data.NewRecipeResponse
import com.althaafridha.receat.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel: ViewModel() {

	var isLoading = MutableLiveData<Boolean>()
	var isError = MutableLiveData<Throwable>()
	var recipeResponse = MutableLiveData<List<NewRecipeResponse>>()

	fun getData(responseHandler: (List<NewRecipeResponse>) -> Unit, errorHandler: (Throwable) -> Unit) {
		ApiClient.getApiService().getNewRecipe()
			// membuat background thread / proses asynchronous
			.subscribeOn(Schedulers.io())
			// menentukan dimana thread akan dibuat
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				responseHandler(this.recipeResponse.value!!)
			}, {
				errorHandler(it)
			})
	}

	fun getNewRecipe() {
		isLoading.value = true
		getData({
			isLoading.value = false
			recipeResponse.value = it
		},{
			isLoading.value = false
			isError.value = it
		})
	}

}