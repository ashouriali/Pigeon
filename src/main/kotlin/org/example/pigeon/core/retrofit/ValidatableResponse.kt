package org.example.pigeon.core.retrofit


import org.example.pigeon.core.log.PigeonServiceLogger
import retrofit2.Call
import retrofit2.HttpException

interface ValidatableResponse {
    val logger: PigeonServiceLogger
    fun <T> validateResponse(block: () -> Call<T>): T? {
        try {
            val response = block().execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                logger.info { "Response received successfully from ${logger.label} service." }
                return body
            } else {
                logger.error { "Api invocation encountered an error with status code: ${response.code()} and message: ${response.message()}" }
            }
        } catch (e: HttpException) {
            logger.error(e) { "Api invocation encountered an error with status code: ${e.code()} and message: ${e.message()}" }
        } catch (e: Throwable) {
            logger.error(e) { "Calling api encountered an error" }
        }
        return null
    }
}