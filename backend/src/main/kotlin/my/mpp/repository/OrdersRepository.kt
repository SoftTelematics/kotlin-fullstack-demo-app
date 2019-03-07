package my.mpp.repository

import my.mpp.data.ListRequest
import my.mpp.data.Order
import my.mpp.jooq.Tables.ORDERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class OrdersRepository(val dsl: DSLContext) : TableRepository<Order> {

    override fun getList(request: ListRequest): List<Order> {
        val records = dsl.select()
                .from(ORDERS)
                .orderBy(ORDERS.ID)
                .limit(request.per)

                .offset(request.page * request.per)

                .fetch()

        return records.map {
            Order(
                    it[ORDERS.ID],
                    it[ORDERS.CUSTOMER],
                    it[ORDERS.PRODUCT],
                    it[ORDERS.SUM],
                    it[ORDERS.COMMENT])
        }
    }

    override fun getTotal(request: ListRequest): Int {
        return dsl.fetchCount(ORDERS)
    }

    override fun saveNewList(order: Order) {
        dsl.insertInto(ORDERS, ORDERS.CUSTOMER, ORDERS.PRODUCT, ORDERS.SUM, ORDERS.COMMENT)
                .values(order.customer, order.product, order.sum, order.comment)
                .execute()
    }

    override fun deleteOrder(order: Order) {
        dsl.deleteFrom(ORDERS)
                .where(ORDERS.ID.eq(order.id))
                .execute()
    }

}