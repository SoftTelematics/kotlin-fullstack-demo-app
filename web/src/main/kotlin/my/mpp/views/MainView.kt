package my.mpp.views

import kotlinx.html.DIV
import kotlinx.html.MAIN
import kotlinx.html.UL
import kotlinx.html.role
import my.mpp.components.main
import react.dom.*

fun RDOMBuilder<DIV>.mainView(name: String, component: RDOMBuilder<MAIN>.() -> Unit) {
    div("row") {
        navigation()
        main("col-md-9 ml-sm-auto col-lg-10 px-4") {
            attrs {
                role = "main"
            }
            div("d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom") {
                h1("h2") {
                    +name
                }
            }
            component()
        }
    }
}

fun RDOMBuilder<DIV>.navigation() {
    nav("col-md-2 d-none d-md-block bg-light sidebar") {
        div("sidebar-sticky") {
            ul("nav flex-column") {
                navigationItem("dashboard", "Dashboard", "home")
                navigationItem("orders", "Orders", "file")
                navigationItem("products", "Products", "shopping-cart")
                navigationItem("customers", "Customers", "users")
                navigationItem("reports", "Reports", "bar-chart-2")
                navigationItem("integrations", "Integrations", "layers")
            }
        }
    }
}

fun RDOMBuilder<UL>.navigationItem(link: String, text: String, icon: String) {
    li("nav-item") {
        a(href = "#/$link", classes = "nav-link", target = "") {
            span {
                attrs.set("data-feather", icon)
                +text
            }
        }
    }
}

fun RDOMBuilder<DIV>.mainApp() {
    div("main") {
        div {}
    }
}
