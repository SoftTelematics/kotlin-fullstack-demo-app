package my.mpp

import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Order
import my.mpp.data.Table
import my.mpp.repository.OrdersRepository
import my.mpp.repository.TableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class TableController<T: Table> {

    @Autowired
    lateinit var tableRepository: TableRepository<T>

    @PostMapping("/list")
    fun getList(@RequestBody request: ListRequest): ResponseEntity<ListResponse<T>> {
        if (request.check()) {
            ResponseEntity("Bad request", HttpStatus.BAD_REQUEST)
        }
        val total = tableRepository.getTotal(request)
        val list = tableRepository.getList(request)
        return ResponseEntity(ListResponse(total, list), HttpStatus.OK)
    }

    @PostMapping("/save_new_order")
    fun saveNewOrder(@RequestBody table: T) {
        if (table.check()) {
            tableRepository.saveNewList(table)
        }
    }

    @PostMapping("/delete_order")
    fun deleteOrder(@RequestBody table: T) {
        if (table.check()) {
            tableRepository.deleteOrder(table)
        }
    }

}