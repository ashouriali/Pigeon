package org.example.pigeon.core.retrofit


import org.example.pigeon.core.exception.PigeonBaseException
import org.example.pigeon.core.log.PigeonServiceLogger
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

interface ValidatableResponse {
    val logger: PigeonServiceLogger
    fun handleUnder5xxError(
        response: Response<*>?,
        e: Throwable? = null
    ): Throwable = PigeonBaseException(response?.message() ?: "", response?.code() ?: 500)
    fun handle5xxError(response: Response<*>?, e: Throwable? = null): Throwable = PigeonBaseException(response?.message() ?: "", 500)
    fun handleNonHttpException(e: Throwable): Throwable = PigeonBaseException("A non HTTP error with empty message.", statusCode = 500)

    fun <T> validateResponse(requestName: String ,block: () -> Call<T>): RetrofitResult<T> {
        val response: Response<T>
        try {
            logger.info { "Request $requestName is being sent..." }
            response = block().execute()
            val body = response.body()
            if (response.isSuccessful) {
                logger.info { "Response received for request $requestName successfully." }
                return RetrofitResult(response.isSuccessful, body, null)
            }
        } catch (e: HttpException) {
            logger.error(e) { "Api invocation encountered an error for request $requestName with status code: ${e.code()} and message: ${e.message()}" }
            return RetrofitResult(
                false,
                null,
                if (e.code() < 500) handleUnder5xxError(e.response(), e) else handle5xxError(e.response(), e)
            )
        } catch (e: Throwable) {
            logger.error(e) { "Calling api with request $requestName encountered an error" }
            return RetrofitResult(false, null, handleNonHttpException(e))
        }

        logger.error { "Api invocation encountered an error for request $requestName with status code: ${response.code()} and message: ${response.message()}" }
        return RetrofitResult(
            false,
            null,
            if (response.code() < 500) handleUnder5xxError(response) else handle5xxError(response)
        )
    }

    fun <T> RetrofitResult<T>.getBodyOrThrowException(): T {
        return if (isSuccessful && body != null) body
        else if (body == null) error("Body is null!")
        else throwable?.let { throw it } ?: error("Unexpected error occurred!")
    }
}