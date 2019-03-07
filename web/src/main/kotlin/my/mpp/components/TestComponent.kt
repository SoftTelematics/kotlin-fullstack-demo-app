package my.mpp.components

import kotlinx.html.DIV
import kotlinx.html.body
import kotlinx.html.div
import my.mpp.common.RouteComponent
import my.mpp.components.table.TableComponent
import my.mpp.controllers.TableControllerImpl
import react.RBuilder
import react.RProps
import react.dom.RDOMBuilder
import react.dom.div
import ringui.table.Column

class TestComponent : BaseComponent<RProps, TestState>() {

    override fun RBuilder.render() {
        +"Test"
    }

}

fun RDOMBuilder<DIV>.testView(component: RDOMBuilder<DIV>.() -> Unit) {


    childList.add(TestComponent().render())

    component.invoke(this)

}

external interface TestState : BaseState