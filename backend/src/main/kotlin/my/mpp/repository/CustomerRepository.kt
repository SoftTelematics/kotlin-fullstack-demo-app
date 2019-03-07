package my.mpp.repository

import my.mpp.data.Customer
import my.mpp.data.ListRequest
import my.mpp.data.Order
import my.mpp.data.Table
import my.mpp.jooq.Tables
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(val dsl: DSLContext) : TableRepository<Customer> {

    override fun getList(request: ListRequest): List<Customer> {
        val records = dsl.select()
                .from(Tables.CUSTOMERS)
                .orderBy(Tables.CUSTOMERS.ID)
                .limit(request.per)

                .offset(request.page * request.per)

                .fetch()

        return records.map {
            Customer(
                    it[Tables.CUSTOMERS.ID],
                    it[Tables.CUSTOMERS.NAME])
        }
    }

    override fun getTotal(request: ListRequest): Int {
        return dsl.fetchCount(Tables.CUSTOMERS)
    }

    override fun saveNewList(customer: Customer) {
        dsl.insertInto(Tables.CUSTOMERS, Tables.CUSTOMERS.NAME)
                .values(customer.name)
                .execute()
    }

    override fun deleteOrder(customer: Customer) {
        dsl.deleteFrom(Tables.CUSTOMERS)
                .where(Tables.CUSTOMERS.ID.eq(customer.id))
                .execute()
    }

}