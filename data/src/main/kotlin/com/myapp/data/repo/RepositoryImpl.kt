package com.myapp.data.repo

import com.myapp.data.domain.repository.Repository
import com.myapp.data.domain.toProductDetail
import com.myapp.data.domain.toProductList
import com.myapp.data.network.NetworkResult
import com.myapp.data.network.Util.isListNotEmpty
import com.myapp.data.remote.RemoteDataService


class RepositoryImpl(private val apiService: RemoteDataService) : Repository {

    override suspend fun getProductList(): NetworkResult {
        val response = apiService.getAllProductList()
        if (response.isListNotEmpty()) {
            return NetworkResult.Success(response?.toProductList())
        }
        return NetworkResult.Error<Nothing>()
    }

    override suspend fun getProductDetail(id: String): NetworkResult {
        val response = apiService.getProductDetails(id)
        return if (response != null) {
            NetworkResult.Success(response.toProductDetail())
        } else {
            NetworkResult.Error<Nothing>()
        }
    }

}

