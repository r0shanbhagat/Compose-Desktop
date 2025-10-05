package com.myapp.ui.feature.product.state

import com.myapp.data.domain.model.ProductDetail

data class ProductDetailState(
    val isLoading: Boolean = false,
    val data: ProductDetail? = null,
    var error: String = ""
)
