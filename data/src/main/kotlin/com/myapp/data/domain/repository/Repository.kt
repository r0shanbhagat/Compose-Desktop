package com.myapp.data.domain.repository

import com.myapp.data.network.NetworkResult

interface Repository {

    suspend fun getProductList(): NetworkResult

    suspend fun getProductDetail(id: String): NetworkResult

}