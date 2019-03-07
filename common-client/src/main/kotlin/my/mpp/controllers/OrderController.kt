package my.mpp.controllers

import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Order

interface OrderController {

    suspend fun getList(request: ListRequest): ListResponse<Order>

    suspend fun saveNewOrder(order: Order)

    suspend fun deleteOrder(order: Order)
}