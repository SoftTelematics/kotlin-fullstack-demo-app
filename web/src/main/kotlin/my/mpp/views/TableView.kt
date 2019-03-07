package my.mpp.views

import my.mpp.components.table.TableComponent
import my.mpp.components.table.TableProps
import my.mpp.controllers.TableControllerImpl
import my.mpp.data.Table
import react.Component
import react.RBuilder
import react.RProps
import react.RState
import ringui.table.Column

inline fun <reified T: TableComponent<Table>> RBuilder.tableList(columns: Array<Column> = arrayOf()) = child(T::class) {
    attrs.columns = columns
    attrs.controller = TableControllerImpl()
}
