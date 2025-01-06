package org.example.pigeon.core.retrofit

data class RetrofitResult<T>(val isSuccessful: Boolean, val body: T?, val throwable: Throwable?)