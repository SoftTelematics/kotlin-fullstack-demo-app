package my.mpp.data

import kotlinx.serialization.Serializable

@Serializable
class Product(
        override val id: Int,
        val name: String
) : Table()