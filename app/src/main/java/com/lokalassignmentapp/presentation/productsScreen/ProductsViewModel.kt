package com.lokalassignmentapp.presentation.productsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokalassignmentapp.R
import com.lokalassignmentapp.common.Constants
import com.lokalassignmentapp.common.Resource
import com.lokalassignmentapp.common.ScreenState
import com.lokalassignmentapp.data.remote.dto.Product
import com.lokalassignmentapp.domain.usecases.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _screenState = mutableStateOf(ScreenState())
    val screenState: State<ScreenState> = _screenState

    private val _dataState = mutableStateOf(DataState())
    val dataState: State<DataState> = _dataState

    fun getProducts() {
        productUseCase.getProducts().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    hideMessage()
                    hideProgressBar()
                    _dataState.value = dataState.value.copy(
                        productsList = result.data?.products?: emptyList(),
                        categories = result.data?.products?.map { it.category }?.distinct() ?: emptyList(),
                    )
                }
                is Resource.Error -> {
                    hideProgressBar()
                    showMessage(result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    showProgressBar("Loading...")
                }
            }
        }.launchIn(viewModelScope)
    }



    private fun showProgressBar(message: Any) {
        _screenState.value = screenState.value.copy(
            loading = Pair(message,true)
        )
    }

    private fun showMessage(message: Any) {
        _screenState.value = screenState.value.copy(
            message = Pair(message,true),
            loading = Pair("",false)
        )
        viewModelScope.launch {
            delay(Constants.SNACKBAR_DURATION)
            hideMessage()
        }
    }

    private fun hideMessage() {
        _screenState.value = screenState.value.copy(
            message = Pair("",true)
        )
    }

    private fun hideProgressBar() {
        _screenState.value = screenState.value.copy(
            loading = Pair("",false)
        )
    }

    fun onEvent(event: ProductsScreenEvents) {
        when(event) {
            is ProductsScreenEvents.LoadCategory -> {
                val filteredProducts = dataState.value.productsList.filter {
                    it.category == event.category
                }
                val brandMap = mutableMapOf<String, MutableList<Product>>()
                for (product in filteredProducts) {
                    if (brandMap.containsKey(product.brand)) {
                        brandMap[product.brand]?.add(product)
                    } else {
                        brandMap[product.brand] = mutableListOf(product)
                    }
                }
                val list = brandMap.map { BrandItem(it.key, it.value) }
                _dataState.value = dataState.value.copy(
                    brandItems = list
                )
                if(list.isEmpty()){
                    showMessage(R.string.empty)
                }
            }
        }
    }
}