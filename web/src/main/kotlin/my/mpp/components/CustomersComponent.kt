package my.mpp.components

import my.mpp.components.table.TableComponent
import my.mpp.data.Customer
import my.mpp.views.customerList
import my.mpp.views.mainView
import react.RBuilder
import react.RProps
import react.dom.div
import ringui.table.Column

class CustomersComponent : BaseComponent<RProps, CustomersState>() {

    override fun RBuilder.render() {
        div {
            mainView("Customers") {
                div {
                    customerList<TableComponent<Customer>>(arrayOf(
                            Column("name", "name"),
                            Column("id", "id")
                    ))
                }
            }
        }
    }

}

external interface CustomersState : BaseState