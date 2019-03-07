package my.mpp.presentation.table

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import my.mpp.controllers.TableController
import my.mpp.data.ListRequest
import my.mpp.data.Sort
import my.mpp.data.Table
import my.mpp.presentation.BasePresenter
import kotlin.coroutines.CoroutineContext

open class TablePresenter<T: Table>(
        private val uiContext: CoroutineContext,
        private val view: TableView<T>,
        private val controller: TableController<T>
) : BasePresenter() {

    private var visibleItems: List<T>? = null

    override fun onCreate() {
        view.loading = true
        refreshList(1, 10)
    }

    fun refreshList(page: Int, pageSize: Int) {
        jobs += GlobalScope.launch(uiContext) {
            try {
                val response = controller.getList(ListRequest("", page - 1, pageSize, listOf(Sort("", ""))))
                val tableData = response.list
                if (tableData == visibleItems) return@launch
                visibleItems = tableData
                view.showList(tableData.sortedBy { it.id }, response.total)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }

    fun saveNewElement(table: T) {
        if (!table.check()) {
            view.showError(Error("Bad request"))
        }
        jobs += GlobalScope.launch(uiContext) {
            try {
                controller.saveNewElement(table)
                refreshList(1, 10)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }

    fun deleteElement(table: T) {
        if (!table.check()) {
            view.showError(Error("Bad request"))
        }
        jobs += GlobalScope.launch(uiContext) {
            try {
                controller.deleteElement(table)
                refreshList(1, 10)
            } catch (e: Throwable) {
                view.showError(e)
            } finally {
                view.refresh = false
                view.loading = false
            }
        }
    }
}