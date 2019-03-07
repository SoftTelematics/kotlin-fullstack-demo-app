package my.mpp.presentation

interface BaseView {
    fun logError(error: Throwable)
    fun showError(error: Throwable)
}