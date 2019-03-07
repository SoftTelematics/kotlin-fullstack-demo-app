package my.mpp.repository

import my.mpp.data.ListRequest
import my.mpp.data.Order
import my.mpp.jooq.Tables.ORDERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
interface TableRepository<T> {

    fun getList(request: ListRequest): List<T>

    fun getTotal(request: ListRequest): Int

    fun saveNewList(table: T)

    fun deleteOrder(table: T)
}