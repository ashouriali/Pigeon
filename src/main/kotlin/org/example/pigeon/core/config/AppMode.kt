package org.example.pigeon.core.config

enum class AppMode {
    DEV, PROD, TEST;

    companion object {
        fun fromString(value: String): AppMode {
            return AppMode.valueOf(value.uppercase())
        }
    }
}