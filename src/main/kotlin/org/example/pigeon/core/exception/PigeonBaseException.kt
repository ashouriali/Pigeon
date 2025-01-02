package org.example.pigeon.core.exception

open class PigeonBaseException : Exception {
    val statusCode: Int
    var errors: Map<String, String>? = null

    constructor(message: String, statusCode: Int = 400) : super(message) {
        this.statusCode = statusCode
    }

    constructor(message: String, statusCode: Int, cause: Throwable) : super(message, cause) {
        this.statusCode = statusCode
    }

    constructor(errors: Map<String, String>, statusCode: Int = 400) : super() {
        this.statusCode = statusCode
        this.errors = errors
    }
}