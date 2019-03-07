package my.mpp.components

import kotlinx.html.DIV
import my.mpp.views.mainApp
import my.mpp.views.mainView
import my.mpp.views.navigation
import react.RBuilder
import react.RProps
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.div

class MainComponent: BaseComponent<RProps, BaseState>() {

    override fun RBuilder.render() {
        div("row") {
            navigation()
            mainApp()
        }
    }

}

interface MainState: BaseState