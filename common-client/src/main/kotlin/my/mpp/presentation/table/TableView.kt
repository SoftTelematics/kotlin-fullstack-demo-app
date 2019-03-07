package my.mpp.presentation.table

import my.mpp.presentation.BaseView

interface TableView<T>: BaseView {
    var loading: Boolean
    var refresh: Boolean
    fun showList(orders: List<T>, total: Int)
}