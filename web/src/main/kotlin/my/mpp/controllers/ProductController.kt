package my.mpp.controllers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import my.mpp.common.httpPost
import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Product

class ProductController : TableController<Product> {

    override suspend fun getList(request: ListRequest): ListResponse<Product> {
        val response = httpPost(
                "/api/products/list",
                Json.indented.stringify(ListRequest.serializer(), request)
        )
        return Json.parse(ListResponse.serializer(Product.serializer()), response)
    }

    override suspend fun saveNewElement(table: Product) {
        GlobalScope.launch {
            httpPost("/api/products/save_new_order", Json.indented.stringify(Product.serializer(), table))
        }
    }

    override suspend fun deleteElement(table: Product) {
        GlobalScope.launch {
            httpPost("/api/products/delete_order", Json.indented.stringify(Product.serializer(), table))
        }
    }
}