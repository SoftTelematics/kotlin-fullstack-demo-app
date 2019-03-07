package my.mpp.components

import my.mpp.views.mainView
import react.RBuilder
import react.RProps
import react.dom.div
import ringui.button.*
import ringui.loader.loader

class DashboardComponent : BaseComponent<RProps, DashboardState>() {

    override fun RBuilder.render() {
        div {
            mainView("Dashboard") {
                //button("Login") {  }
                loader("loading")
            }
        }
    }
}

external interface DashboardState : BaseState {
    var message: String
}