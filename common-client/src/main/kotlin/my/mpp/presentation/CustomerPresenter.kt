package my.mpp.presentation

import my.mpp.controllers.TableController
import my.mpp.data.Customer
import my.mpp.presentation.table.TablePresenter
import my.mpp.presentation.table.TableView
import kotlin.coroutines.CoroutineContext

class CustomerPresenter(
        uiContext: CoroutineContext,
        view: TableView<Customer>,
        controller: TableController<Customer>
) : TablePresenter<Customer>(
        uiContext,
        view,
        controller
)