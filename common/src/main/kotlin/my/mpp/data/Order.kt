package my.mpp.data

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
class Order(
        override val id: Int = 0,
        val customer: String = "",
        val product: String = "",
        val sum: Int = 0,
        @Optional
        val comment: String? = ""
) : Table() {
    override fun check(): Boolean {
        if (product.isEmpty()) {
            return false
        }
        if (sum <= 0) {
            return false
        }
        return true
    }
}