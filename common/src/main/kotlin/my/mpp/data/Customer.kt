package my.mpp.data

import kotlinx.serialization.Serializable

@Serializable
class Customer(
        override val id: Int,
        val name: String
) : Table()