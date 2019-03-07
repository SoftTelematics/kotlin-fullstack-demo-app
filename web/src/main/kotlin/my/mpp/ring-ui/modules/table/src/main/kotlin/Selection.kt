@file:JsModule("@jetbrains/ring-ui/components/table/selection")

package ringui.table

@JsName("default")
external class Selection() {

    constructor(
            data: Array<dynamic>,
            selected: dynamic = definedExternally,
            focused: dynamic = definedExternally,
            getKey: (dynamic) -> dynamic = definedExternally,
            getChildren: () -> Array<dynamic> = definedExternally,
            isItemSelectable: () -> Boolean = definedExternally
    )
    fun _buildData(data: Array<dynamic>): Set<Array<dynamic>>
    fun _buildSelected(data: Array<dynamic>, selected: Set<dynamic>): dynamic
    fun cloneWith(sameData: dynamic): Selection
    fun focus(value: dynamic)
    fun moveUp(): Selection
    fun moveDown(): Selection
    fun moveStart(): Selection
    fun moveEnd(): Selection
    fun select(value:dynamic): Selection
    fun deselect(value:dynamic)
    fun toggleSelection(value: dynamic)
    fun selectAll(): Selection
    fun resetFocus(): Selection
    fun resetSelection(): Selection
    fun reset(): Selection
    fun isFocused(value: dynamic): Boolean
    fun isSelected(value: dynamic): Boolean
    fun getFocused(): dynamic
    fun getSelected(): dynamic
    fun getActive(): dynamic
}