@file:JsModule("@jetbrains/ring-ui/components/table/table")

package ringui.table

import react.Component
import react.RState
import react.ReactElement

@JsName("default")
external class Table(data: Array<Any>, columns: Array<Column>, selection: Selection) : Component<TableProps, RState> {
    override fun render(): ReactElement?
}