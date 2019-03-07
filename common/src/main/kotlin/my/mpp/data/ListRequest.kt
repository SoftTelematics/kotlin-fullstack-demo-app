package my.mpp.data

import kotlinx.serialization.Serializable

@Serializable
data class ListRequest(
        val order: String,
        val page: Int,
        val per: Int,
        val sort: List<Sort>
) {
    fun check(): Boolean {
        if (page < 0) {
            return false
        }
        if (per < 0) {
            return false
        }
        return true
    }
}

@Serializable
data class Sort(val key: String, val dir: String)