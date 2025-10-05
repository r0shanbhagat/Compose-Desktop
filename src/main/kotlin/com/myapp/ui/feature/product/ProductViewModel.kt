package com.myapp.ui.feature.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.myapp.data.domain.model.ProductDetail
import com.myapp.data.domain.model.ProductItem
import com.myapp.data.domain.repository.Repository
import com.myapp.data.network.onError
import com.myapp.data.network.onSuccess
import com.myapp.ui.feature.product.state.ProductListState
import com.myapp.ui.feature.product.state.StateEvent
import com.myapp.util.ViewModel
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _product = mutableStateOf(ProductListState())
    val product: State<ProductListState> get() = _product

    /**
     * Set state intent
     *
     * @param StateEvent
     */
    internal fun setStateIntent(event: StateEvent) {
        when (event) {
            is StateEvent.ProductListing -> {
                fetchProductList()
            }

            is StateEvent.ProductDetails -> {
                fetchProductDetails(event.productId)
            }

            is StateEvent.None -> {
                //TODO will work on New flow
            }
        }
    }

    /**
     * Fetch product details from the repository and update the state.
     */
    private fun fetchProductList() {
        _product.value = ProductListState(isLoading = true)
        viewModelScope.launch {
            repository.getProductList()
                .onSuccess<List<ProductItem>> {
                    _product.value = ProductListState(list = it)
                }.onError<Nothing> {
                    _product.value = ProductListState(error = "No Response From Server")
                }
        }
    }

    private fun fetchProductDetails(id: String) {
        _product.value = ProductListState(isLoading = true)
        viewModelScope.launch {
            repository.getProductDetail(id)
                .onSuccess<ProductDetail> {
                    _product.value = ProductListState(detail = it)
                }.onError<Nothing> {
                    _product.value = ProductListState(error = "No Response From Server")
                }
        }
    }

}