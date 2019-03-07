package my.mpp.repository

import my.mpp.data.ListRequest
import my.mpp.data.Product
import my.mpp.jooq.Tables
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(val dsl: DSLContext) : TableRepository<Product> {

    override fun getList(request: ListRequest): List<Product> {
        val records = dsl.select()
                .from(Tables.PRODUCTS)
                .orderBy(Tables.PRODUCTS.ID)
                .limit(request.per)

                .offset(request.page * request.per)

                .fetch()

        return records.map {
            Product(
                    it[Tables.PRODUCTS.ID],
                    it[Tables.PRODUCTS.NAME])
        }
    }

    override fun getTotal(request: ListRequest): Int {
        return dsl.fetchCount(Tables.PRODUCTS)
    }

    override fun saveNewList(product: Product) {
        dsl.insertInto(Tables.PRODUCTS, Tables.PRODUCTS.NAME)
                .values(product.name)
                .execute()
    }

    override fun deleteOrder(product: Product) {
        dsl.deleteFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.ID.eq(product.id))
                .execute()
    }

}