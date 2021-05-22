package com.bigsteptech.deazzle.common


enum class Status(val value: Int) {
    SUCCESS(1 shl 1), ERROR(1 shl 2), LOADING(1)
}

data class Resource<out T>(val status: Status, val data: T?, val message: String) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }
}