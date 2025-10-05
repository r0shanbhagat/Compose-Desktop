package com.myapp.data.domain

import com.myapp.data.domain.model.ProductDetail
import com.myapp.data.domain.model.ProductItem
import com.myapp.data.domain.model.ProductListDTO


fun List<ProductListDTO>?.toProductList(): List<ProductItem> {
    return this?.map {
        ProductItem(
            id = it.id,
            title = it.title,
            price = it.price,
            image = it.image,
            description = it.description
        )
    } ?: emptyList()
}

fun ProductListDTO.toProductDetail(): ProductDetail {
    return ProductDetail(
        category = this.category,
        description = this.description,
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title
    )
}



