@file:JsModule("@jetbrains/ring-ui/components/table/draggable-row")

package ringui.table

import react.Component
import react.RState
import react.ReactElement

@JsName("default")
external class DraggableRow : Component<RowProps, RState> {
    override fun render(): ReactElement?
}