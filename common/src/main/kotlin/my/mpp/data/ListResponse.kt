package my.mpp.data

import kotlinx.serialization.Serializable

@Serializable
data class ListResponse<T>(val total: Int, val list: List<T>)