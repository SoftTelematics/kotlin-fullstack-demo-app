package my.mpp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import my.mpp.common.httpPost
import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Order

class OrderController : my.mpp.controllers.OrderController {

    override suspend fun getList(request: ListRequest): ListResponse<Order> {
        return Json.parse(ListResponse.serializer(Order.serializer()), httpPost(
                "/api/orders/list",
                Json.indented.stringify(ListRequest.serializer(), request))
        )
    }

    override suspend fun saveNewOrder(order: Order) {
        GlobalScope.launch {
            httpPost("/api/orders/save_new_order", Json.indented.stringify(Order.serializer(), order))
        }
    }

    override suspend fun deleteOrder(order: Order) {
        GlobalScope.launch {
            httpPost("/api/orders/delete_order", Json.indented.stringify(Order.serializer(), order))
        }
    }
}