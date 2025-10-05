package com.myapp.data.network

/**
 * @Details :AppRequest
 * @Author Roshan Bhagat
 */
class AppRequest(val path: String) : BaseRequest() {
    override fun path(): String? = path
}