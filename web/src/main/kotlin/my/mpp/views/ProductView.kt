package my.mpp.views

import my.mpp.components.table.TableComponent
import my.mpp.controllers.CustomerController
import my.mpp.controllers.ProductController
import my.mpp.data.Customer
import my.mpp.data.Product
import react.RBuilder
import ringui.table.Column

inline fun <reified T : TableComponent<Product>> RBuilder.productsList(columns: Array<Column> = arrayOf()) = child(T::class) {
    attrs.columns = columns
    attrs.controller = ProductController()
}