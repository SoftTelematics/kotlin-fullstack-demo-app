package my.mpp

import my.mpp.data.Customer
import my.mpp.data.ListRequest
import my.mpp.data.ListResponse
import my.mpp.data.Order
import my.mpp.repository.CustomerRepository
import my.mpp.repository.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController: TableController<Customer>()