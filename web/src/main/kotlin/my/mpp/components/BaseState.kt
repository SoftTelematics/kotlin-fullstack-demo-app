package my.mpp.components

external interface  BaseState: react.RState {
    var error: Throwable?
}