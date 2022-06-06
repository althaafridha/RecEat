package com.althaafridha.receat.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaafridha.receat.data.DetailResponse
import com.althaafridha.receat.data.NewRecipeResponse
import com.althaafridha.receat.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel : ViewModel() {

	var detailResponse =  MutableLiveData<DetailResponse>()




//	make variable to get key from intent
	fun DetailById(responseHandler: (DetailResponse) -> Unit, errorHandler: (Throwable) -> Unit, key: String) {
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
		DetailById({
			detailResponse.value = it
		}, {}, id)
	}




}