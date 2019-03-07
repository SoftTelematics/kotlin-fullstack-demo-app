package my.mpp.components

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.mpp.common.goBack
import my.mpp.presentation.BaseView
import my.mpp.presentation.Presenter

import react.RComponent
import react.setState

abstract class BaseComponent<P : react.RProps, S : BaseState> : RComponent<P, S>(), BaseView {

    protected fun <T : Presenter> presenter(init: () -> T) = lazy(init)
            .also { lazyPresenters += it }

    private var lazyPresenters: List<Lazy<Presenter>> = emptyList()

    override fun componentDidMount() {
        lazyPresenters.forEach { it.value.onCreate() }
    }

    override fun componentWillUnmount() {
        lazyPresenters.forEach { it.value.onDestroy() }
    }

    override fun showError(error: Throwable) {
        logError(error)
        setState { this.error = error }
    }

    override fun logError(error: Throwable) {
        console.log(error)
    }

    protected fun backAfterDelay(millis: Long) {
        GlobalScope.launch {
            delay(millis)
            goBack()
        }
    }
}