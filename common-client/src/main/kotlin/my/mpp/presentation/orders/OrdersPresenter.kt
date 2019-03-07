package my.mpp.presentation.orders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import my.mpp.controllers.OrderController
import my.mpp.data.ListRequest
import my.mpp.data.Order
import my.mpp.data.Sort
import my.mpp.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

class OrdersPresenter(
        private val uiContext: CoroutineContext,
        private val view: OrdersView,
        private val controller: OrderController
) : BasePresenter() {

    private var visibleOrders: List<Order>? = null

    override fun onCreate() {
        view.loading = true
        refreshList(1, 10)
    }

    fun refreshList(page: Int, pageSize: Int) {
        jobs += GlobalScope.launch(uiContext) {
            try {
                val response = controller.getList(ListRequest("", page - 1, pageSize, listOf(Sort("", ""))))
                val ordersData = response.list
                if (ordersData == visibleOrders) return@launch
                visibleOrders = ordersData

                view.showList(ordersData.sortedBy { it.id }, response.total)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }

    fun saveNewOrder(order: Order) {
        if (!order.check()) {
            view.showError(Error("Bad request"))
        }
        jobs += GlobalScope.launch(uiContext) {
            try {
                controller.saveNewOrder(order)
                refreshList(1, 10)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }

    fun deleteOrder(order: Order) {
        if (!order.check()) {
            view.showError(Error("Bad request"))
        }
        jobs += GlobalScope.launch(uiContext) {
            try {
                controller.deleteOrder(order)
                refreshList(1, 10)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }
}