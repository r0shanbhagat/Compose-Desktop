package com.myapp.data.remote

import com.myapp.data.domain.model.ProductListDTO
import com.myapp.data.network.ApiConstants.BASE_URL
import com.myapp.data.network.ApiConstants.PRODUCT
import com.myapp.data.network.ApiConstants.PRODUCT_DETAIL
import com.myapp.data.network.AppRequest
import com.myapp.data.network.NetworkClient
import com.myapp.data.network.Util.executeGetApiCall
import com.toxicbakery.logging.Arbor


/**
 * @Details :ApiServiceImpl
 * @Author Roshan Bhagat
 */
class RemoteDataSourceImpl : RemoteDataService {

    override suspend fun getAllProductList(): List<ProductListDTO>? {
        try {
            val request = AppRequest(PRODUCT)
            return NetworkClient(BASE_URL).getList<ProductListDTO>(request)
        } catch (e: Exception) {
            Arbor.v("RemoteDataSourceImpl", e.message)
        }
        return emptyList()
    }


    override suspend fun getProductDetails(id: String): ProductListDTO? {
        return executeGetApiCall(PRODUCT_DETAIL.format(id))
    }


}