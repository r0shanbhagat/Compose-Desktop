package com.myapp.data.domain.model

import com.myapp.data.network.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListDTO(
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("price")
    val price: String,
    @SerialName("title")
    val title: String,
) : BaseResponse()

