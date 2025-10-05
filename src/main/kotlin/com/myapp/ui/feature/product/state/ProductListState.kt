package com.myapp.ui.feature.product.state

import com.myapp.data.domain.model.ProductDetail
import com.myapp.data.domain.model.ProductItem

data class ProductListState(
    val isLoading: Boolean = false,
    val list: List<ProductItem>? = null,
    val detail: ProductDetail? = null,
    var error: String = ""
)
