package my.mpp.controllers

import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Order

interface TableController<T> {

    suspend fun getList(request: ListRequest): ListResponse<T>

    suspend fun saveNewElement(table: T)

    suspend fun deleteElement(table: T)
}