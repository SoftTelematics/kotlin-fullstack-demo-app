package my.mpp.components

import kotlinx.html.role
import my.mpp.views.mainApp
import my.mpp.views.mainView
import my.mpp.views.navigation
import react.RBuilder
import react.RProps
import react.ReactElement
import react.dom.div
import react.dom.h1
import react.dom.style

class NotImplementedComponent : BaseComponent<RProps, NotImplementedState>() {

    override fun RBuilder.render() {
        div {
            mainView("Not implemented") {

            }
        }
    }

}


external interface NotImplementedState : BaseState