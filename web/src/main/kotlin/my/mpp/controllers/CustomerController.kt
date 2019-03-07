package my.mpp.controllers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import my.mpp.common.httpPost
import my.mpp.data.Customer
import my.mpp.data.ListRequest
import my.mpp.data.ListResponse

class CustomerController : TableController<Customer> {

    override suspend fun getList(request: ListRequest): ListResponse<Customer> {
        val response = httpPost(
                "/api/customers/list",
                Json.indented.stringify(ListRequest.serializer(), request)
        )
        return Json.parse(ListResponse.serializer(Customer.serializer()), response)
    }

    override suspend fun saveNewElement(table: Customer) {
        GlobalScope.launch {
            httpPost("/api/customers/save_new_order", Json.indented.stringify(Customer.serializer(), table))
        }
    }

    override suspend fun deleteElement(table: Customer) {
        GlobalScope.launch {
            httpPost("/api/customers/delete_order", Json.indented.stringify(Customer.serializer(), table))
        }
    }

}