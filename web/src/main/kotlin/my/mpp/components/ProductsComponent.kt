package my.mpp.components

import my.mpp.components.table.TableComponent
import my.mpp.data.Customer
import my.mpp.data.Product
import my.mpp.views.customerList
import my.mpp.views.mainView
import my.mpp.views.productsList
import react.RBuilder
import react.RProps
import react.dom.div
import ringui.table.Column

class ProductsComponent : BaseComponent<RProps, ProductsState>() {

    override fun RBuilder.render() {
        div {
            mainView("Products") {
                div {
                    productsList<TableComponent<Product>>(arrayOf(
                            Column("id", "id"),
                            Column("name", "name")
                    ))
                }
            }
        }
    }

}

external interface ProductsState : BaseState