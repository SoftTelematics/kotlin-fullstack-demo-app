package ringui.table

import react.RBuilder
import react.RElementBuilder
import ringui.RDynamicProps
import ringui.REventFunc

interface TableProps : RDynamicProps {

    //var className: String
    var loaderClassName: String
    var data: Array<dynamic>
    var columns: Array<Column>
    var caption: String
    var isItemSelectable: () -> Boolean
    var stickyHeader: Boolean
    var stickyHeaderOffset: String
    var loading: Boolean
    var getItemKey: REventFunc
    var onSort: (dynamic) -> Unit
    var onReorder: (dynamic) -> Unit
    var sortKey: String
    var sortOrder: Boolean
    //var draggable: Boolean
    var alwaysShowDragHandle: Boolean
    var getItemLevel: () -> Int
    var isItemCollapsible: () -> Boolean
    var isParentCollapsible: () -> Boolean
    var isItemCollapsed: () -> Boolean
    var onItemCollapse: REventFunc
    var onItemExpand: REventFunc
    var isDisabledSelectionVisible: () -> Boolean
    var getCheckboxTooltip: () -> Unit

    // focusSensorHOC
    var focused: Boolean
    var onFocusRestore: REventFunc

    // selectionShortcutsHOC
    var selection: Selection
    var selectable: Boolean
    //var onSelect: (Array<dynamic>) -> Unit
    var shortcutsMap: dynamic

    // disableHoverHOC
    var disabledHover: Boolean

    var remoteSelection: Boolean

}

interface RowProps : RDynamicProps {
    val propTypes: String
}

data class Column(
        val id: String,
        val title: String,
        val sortable: Boolean = false,
        val rightAlign: Boolean = false
)

typealias RHandler<P> = RElementBuilder<P>.() -> Unit

fun <T> RBuilder.table(
        data: Array<T> = arrayOf(),
        columns: Array<Column> = arrayOf(),
        selection: Selection = Selection(arrayOf()),
        block: RHandler<TableProps> = {}) = child(Table::class) {

    attrs.getItemKey = fun(item: dynamic) = item.id
    attrs.getItemLevel = fun() = 0
    attrs.isItemSelectable = fun() = true
    data.let { attrs.data = data }
    columns.let { attrs.columns = columns }
    selection.let { attrs.selection = selection }
    this.block()
}

