package org.example.pigeon.core.server.exception

data class PigeonError(
    val title: String,
    val status: Int,
    val type: String,
    val details: Any,
)