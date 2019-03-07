package my.mpp.controllers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import my.mpp.common.httpPost
import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Table

class TableControllerImpl : my.mpp.controllers.TableController<Table> {

    override suspend fun getList(request: ListRequest): ListResponse<Table> {
        return Json.parse(ListResponse.serializer(Table.serializer()), httpPost(
                "/api/orders/list",
                Json.indented.stringify(ListRequest.serializer(), request))
        )
    }

    override suspend fun saveNewElement(table: Table) {
        GlobalScope.launch {
            httpPost("/api/orders/save_new_order", Json.indented.stringify(Table.serializer(), table))
        }
    }

    override suspend fun deleteElement(table: Table) {
        GlobalScope.launch {
            httpPost("/api/orders/delete_order", Json.indented.stringify(Table.serializer(), table))
        }
    }
}