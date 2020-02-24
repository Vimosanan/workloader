package com.vimosanan.workloaderapplication.util

data class Resource<out T>(val status: Status, val data: T?, val msg: String?, val statusCode:Int) {
    companion object {
        fun <T> success(data: T?, statusCode: Int): Resource<T> {
            return Resource(Status.SUCCESS, data, null, statusCode)
        }

        fun <T> error(msg: String, data: T? = null, statusCode: Int): Resource<T> {
            return Resource(Status.ERROR, data, msg, statusCode)
        }

        fun <T> loading(data: T? = null, statusCode: Int): Resource<T> {
            return Resource(Status.LOADING, data, null, statusCode)
        }
    }
}