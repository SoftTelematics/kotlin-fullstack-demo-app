package my.mpp.presentation.orders

import my.mpp.data.Order
import my.mpp.presentation.BaseView

interface OrdersView : BaseView {
    var loading: Boolean
    var refresh: Boolean
    fun showList(orders: List<Order>, total: Int)
}