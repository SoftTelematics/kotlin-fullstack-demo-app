package my.mpp.presentation

import my.mpp.controllers.TableController
import my.mpp.data.Customer
import my.mpp.data.Product
import my.mpp.presentation.table.TablePresenter
import my.mpp.presentation.table.TableView
import kotlin.coroutines.CoroutineContext

class ProductPresenter(
        uiContext: CoroutineContext,
        view: TableView<Product>,
        controller: TableController<Product>
) : TablePresenter<Product>(
        uiContext,
        view,
        controller
)