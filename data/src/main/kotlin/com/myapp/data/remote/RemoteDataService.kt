package com.myapp.data.remote

import com.myapp.data.domain.model.ProductListDTO

/**
 * @Details ApiService
 * @Author Roshan Bhagat
 * @constructor Create Api service
 */
interface RemoteDataService {
    /**
     * Performs a GET call to obtain a getAllProductListAPI
     */
    suspend fun getAllProductList(): List<ProductListDTO>?

    /**
     * Base on Movies Id fetch the details of movie
     * @param id: String id of product based on˚ which product should be fetched
     * @return
     */
    suspend fun getProductDetails(id: String): ProductListDTO?
}
