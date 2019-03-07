package my.mpp

import my.mpp.common.hashRouter
import my.mpp.common.route
import my.mpp.common.switch
import my.mpp.components.*
import my.mpp.components.table.TableComponent
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    window.onload = {
        render(document.getElementById("root")!!) {
            hashRouter {
                switch {
                    route("/", MainComponent::class, exact = true)
                    route("/dashboard", DashboardComponent::class)
                    route("/orders", OrdersComponent::class)
                    route("/products", ProductsComponent::class)
                    route("/customers", CustomersComponent::class)
                    route("/reports", NotImplementedComponent::class)
                    route("/integrations", NotImplementedComponent::class)
                }
            }
        }
    }
}