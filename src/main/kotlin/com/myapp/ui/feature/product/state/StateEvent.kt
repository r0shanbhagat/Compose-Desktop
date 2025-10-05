package com.myapp.ui.feature.product.state

/**
 * Movie state event.
 *
 * @constructor Create empty constructor for movie state event
 */
sealed class StateEvent {
    /**
     * Get movie list
     *
     * @constructor
     */
    object ProductListing : StateEvent()

    data class ProductDetails(val productId: String) : StateEvent()

    /**
     * None
     *
     * @constructor Create empty None
     */
    data object None : StateEvent()
}