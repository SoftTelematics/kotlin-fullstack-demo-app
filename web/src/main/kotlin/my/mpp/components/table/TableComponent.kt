package my.mpp.components.table

import kotlinx.coroutines.GlobalScope
import kotlinx.html.attributesMapOf
import kotlinx.serialization.json.Json
import my.mpp.components.BaseComponent
import my.mpp.components.BaseState
import my.mpp.controllers.TableController
import my.mpp.data.Table
import my.mpp.presentation.table.TablePresenter
import my.mpp.presentation.table.TableView
import org.w3c.dom.events.Event
import react.RBuilder
import react.RProps
import react.setState
import ringui.alert.AlertElement
import ringui.alert.AlertType
import ringui.alert.alert
import ringui.button.button
import ringui.buttongroup.buttonGroup
import ringui.dialog.content
import ringui.dialog.dialog
import ringui.dialog.header
import ringui.grid.grid
import ringui.grid.row
import ringui.pager.pager
import ringui.panel.panel
import ringui.table.Column
import ringui.table.Selection
import ringui.table.table
import kotlin.properties.Delegates

interface TableProps<T : Table> : RProps {
    var columns: Array<Column>
    var controller: TableController<T>
}

external interface TableState<T> : BaseState {
    var data: Array<T>
    var loading: Boolean?
    var columns: Array<Column>
    var selection: Selection
    var sortKey: String
    var sortOrder: Boolean
    var showDialog: Boolean
    var total: Int
    var pageSize: Int
    var currentPage: Int
    var alerts: Array<AlertElement>
}

class TableComponent<T : Table> : BaseComponent<TableProps<T>, TableState<T>>(), TableView<T> {

    private val presenter by presenter {
        TablePresenter(GlobalScope.coroutineContext, this, props.controller)
    }

    init {
        state.data = arrayOf()
        state.selection = Selection(state.data)
        state.sortKey = ""
        state.sortOrder = false
        state.showDialog = false
        state.total = 0
        state.pageSize = 10
        state.currentPage = 1
        state.alerts = arrayOf()
    }

    override fun RBuilder.render() {

        buttonGroup {
            button {
                attrs {
                    onClick = fun(event: Event) {
                        setState {
                            //                                    product = ""
//                                    sum = ""
//                                    comment = ""
                            this.showDialog = true
                        }
                    }
                }
                +"Add new"
            }
            button {
                attrs {
                    onClick = fun(event: Event) {
                        setState {
                            selection = selection.selectAll()
                        }
                    }
                }
                +"Select All"
            }
            button {
                attrs {
                    onClick = fun(event: Event) {
                        setState {
                            selection = selection.resetSelection()
                        }
                    }
                }
                +"Deselect All"
            }
            button {
                attrs {
                    onClick = fun(event: Event) { presenter.refreshList(state.currentPage, state.pageSize) }
                }
                +"Refresh"
            }
            button {
                attrs {
                    onClick = fun(event) {
                        state.data.forEach {
                            if (state.selection.isSelected(it)) presenter.deleteElement(it)
                        }
                        presenter.refreshList(state.currentPage, state.pageSize)
                    }
                }
                +"Delete"
            }
        }
        table<T>(
                data = state.data,
                columns = props.columns,
                selection = state.selection
        ) {
            attrs {
                selectable = true
                sortKey = state.sortKey
                sortOrder = state.sortOrder
                draggable = true
                onReorder = fun(event) {
                    setState { this.data = event.data }
                }
                onSelect = fun(thisSelection) { setState { selection = thisSelection } }
                onSort = fun(event) {
                    setState {
                        this.sortKey = event.column.id
                        this.sortOrder = event.order
                    }
                }
            }
        }
        grid {
            row {
                pager(total = state.total, currentPage = state.currentPage, disablePageSizeSelector = false) {
                    attrs {
                        pageSizes = arrayOf(10, 20, 50, 100)
                        pageSize = state.pageSize
                        onPageChange = fun(page: Int) {
                            setState {
                                currentPage = page
                            }
                            presenter.refreshList(page, state.pageSize)
                        }
                        onPageSizeChange = fun(size: Int) {
                            setState {
                                pageSize = size
                            }
                            presenter.refreshList(state.currentPage, size)
                        }
                    }
                }
            }
        }
        state.alerts.map { alert ->
            alert(alert.type, alert.message) {
                attrs {
                    showWithAnimation = true
                    onCloseRequest = fun() {
                        setState {
                            alerts = state.alerts.filter { it.key != alert.key }.toTypedArray()
                        }
                    }
                }
            }
        }

        newElementDialog()
    }

    override var loading: Boolean by Delegates.observable(false) { _, _, n ->
        setState { loading = n }
    }

    override var refresh: Boolean by Delegates.observable(false) { _, _, n ->
        setState { loading = n }
    }

    override fun showError(error: Throwable) {
        super.showError(error)
        setState {
            alerts[alerts.size] = AlertElement(alerts.size, error.message ?: "", AlertType.ERROR)
        }
    }

    override fun showList(table: List<T>, thisTotal: Int) {
        setState {
            total = thisTotal
            data = table.toTypedArray()
        }
    }

    private fun RBuilder.newElementDialog() {
        dialog(show = state.showDialog) {
            attrs {
                onCloseAttempt = { setState { this.showDialog = false } }
            }
            header { +"New element" }
            content {

            }
            panel {
                button {
                    attrs {
                        onClick = fun(event: Event) {
                            setState { this.showDialog = false }
                            presenter.refreshList(state.currentPage, state.pageSize)
                        }
                    }
                    +"OK"
                }
                button {
                    attrs {
                        onClick = fun(event: Event) { setState { this.showDialog = false } }
                    }
                    +"Cancel"
                }
            }
        }
    }

}

