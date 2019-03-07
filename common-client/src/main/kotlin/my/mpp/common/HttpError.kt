package my.mpp.common

class HttpError(val code: Int, override val message: String) : Throwable()