package my.mpp.views

import my.mpp.components.table.TableComponent
import my.mpp.controllers.CustomerController
import my.mpp.data.Customer
import react.RBuilder
import ringui.table.Column

inline fun <reified T : TableComponent<Customer>> RBuilder.customerList(columns: Array<Column> = arrayOf()) = child(T::class) {
    attrs.columns = columns
    attrs.controller = CustomerController()
}