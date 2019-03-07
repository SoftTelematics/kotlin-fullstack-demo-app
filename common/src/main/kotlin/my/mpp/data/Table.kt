package my.mpp.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class Table {
    @Transient
    abstract val id: Int

    open fun check(): Boolean {
        return true
    }
}