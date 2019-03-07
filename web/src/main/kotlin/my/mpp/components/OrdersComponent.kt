package my.mpp.components

import kotlinx.coroutines.GlobalScope
import my.mpp.OrderController
import my.mpp.data.Order
import my.mpp.presentation.orders.OrdersPresenter
import my.mpp.presentation.orders.OrdersView
import my.mpp.views.mainView
import org.w3c.dom.events.Event
import react.RBuilder
import react.RProps
import react.dom.div
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
import ringui.input.input
import ringui.pager.pager
import ringui.panel.panel
import ringui.table.Column
import ringui.table.Selection
import ringui.table.table
import kotlin.properties.Delegates

class OrdersComponent : BaseComponent<RProps, OrdersState>(), OrdersView {

    private val orderController = OrderController()

    private val presenter by presenter {

        OrdersPresenter(GlobalScope.coroutineContext, this, orderController)
    }

    init {
        state.data = arrayOf()
        state.columns = arrayOf(
                Column("customer", "customer"),
                Column("id", "id"),
                Column("product", "product"),
                Column("sum", "sum"),
                Column("comment", "comment"))
        state.selection = Selection(state.data)
        state.sortKey = ""
        state.sortOrder = false
        state.showDialog = false
        state.customer = ""
        state.product = ""
        state.sum = ""
        state.comment = ""
        state.total = 0
        state.pageSize = 10
        state.currentPage = 1
        state.alerts = arrayOf()
    }

    override fun RBuilder.render() {
        div {
            mainView("Orders") {
                buttonGroup {
                    button {
                        attrs {
                            onClick = fun(event: Event) {
                                setState {
                                    product = ""
                                    sum = ""
                                    comment = ""
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
                                    if (state.selection.isSelected(it)) presenter.deleteOrder(it)
                                }
                                presenter.refreshList(state.currentPage, state.pageSize)
                            }
                        }
                        +"Delete"
                    }
                }
                table<Order>(
                        data = state.data,
                        columns = state.columns,
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

            }
        }
        newOrderDialog()
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

    override fun showList(orders: List<Order>, thisTotal: Int) {
        setState {
            total = thisTotal
            data = orders.toTypedArray()
        }
    }

    private fun RBuilder.newOrderDialog() {
        dialog(show = state.showDialog) {
            attrs {
                onCloseAttempt = { setState { this.showDialog = false } }
            }
            header { +"New order" }
            content {
                input {
                    attrs {
                        label = "Customer"
                        onChange = fun(event) {
                            val tempCustomer = event.target.asDynamic().value
                            setState {
                                customer = tempCustomer
                            }
                        }
                    }
                }
                input {
                    attrs {
                        label = "Product"
                        onChange = fun(event) {
                            val tempProduct = event.target.asDynamic().value
                            setState {
                                product = tempProduct
                            }
                        }
                    }
                }
                input {
                    attrs {
                        type = "number"
                        label = "Sum"
                        value = state.sum
                        onChange = fun(event) {
                            val tempSum = event.target.asDynamic().value
                            setState {
                                sum = tempSum
                            }
                        }
                    }
                }
                input {
                    attrs {
                        label = "Comment"
                        value = state.comment
                        onChange = fun(event) {
                            val tempComment = event.target.asDynamic().value
                            setState {
                                comment = tempComment
                            }
                        }
                    }
                }
            }
            panel {
                button {
                    attrs {
                        onClick = fun(event: Event) {
                            setState { this.showDialog = false }
                            presenter.saveNewOrder(Order(
                                    0,
                                    state.customer,
                                    state.product,
                                    state.sum.toIntOrNull() ?: 0,
                                    state.comment))
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

external interface OrdersState : BaseState {
    var data: Array<Order>
    var loading: Boolean?
    var columns: Array<Column>
    var selection: Selection
    var sortKey: String
    var sortOrder: Boolean
    var showDialog: Boolean
    var customer: String
    var product: String
    var sum: String
    var comment: String
    var total: Int
    var pageSize: Int
    var currentPage: Int
    var alerts: Array<AlertElement>
}
