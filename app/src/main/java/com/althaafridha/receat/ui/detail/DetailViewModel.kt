package com.althaafridha.receat.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaafridha.receat.data.network.ApiClient
import com.althaafridha.receat.data.response.Recipe
import com.althaafridha.receat.data.response.RecipeResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel : ViewModel() {

	var isLoading = MutableLiveData<Boolean>()
	var detailResponse =  MutableLiveData<RecipeResponse>()


//	make variable to get key from intent
	fun DetailById(responseHandler: (RecipeResponse) -> Unit, errorHandler: (Throwable) -> Unit, key: String) {
		ApiClient.getApiService().getDetailRecipe(key)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				responseHandler(it)
			}, {
				errorHandler(it)
			})
	}

	fun getDetailById(id: String) {
		isLoading.value = true
		DetailById({
			isLoading.value = false
			detailResponse.value = it
		}, {}, id)
	}




}