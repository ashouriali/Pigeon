package org.example.pigeon.core.log

import mu.KotlinLogging
import kotlin.reflect.KClass

class PigeonServiceLogger(val label: String? = null, func: () -> Unit = {}) {

    private var logger =
        if (label != null)
            KotlinLogging.logger(label)
        else
            KotlinLogging.logger(func)

    constructor(klass: Class<*>) : this(klass.simpleName)

    constructor(kClass: KClass<*>) : this(kClass.simpleName!!)

    fun info(message: () -> String?) {
        if (!logger.isInfoEnabled)
            return

        val msg = message()
        logger.info(msg)
    }

    fun info(throwable: Throwable, message: () -> String?) {
        if (!logger.isInfoEnabled)
            return

        val msg = message()
        logger.info(msg, throwable)
    }

    fun error(message: () -> String?) {
        if (!logger.isErrorEnabled)
            return

        val msg = message()
        logger.error(msg)
    }

    fun error(throwable: Throwable, message: () -> String?) {
        if (!logger.isErrorEnabled)
            return

        val msg = message()
        logger.error(msg, throwable)
    }

    fun warn(message: () -> String?) {
        if (!logger.isWarnEnabled)
            return

        val msg = message()
        logger.warn(msg)
    }

    fun warn(throwable: Throwable, message: () -> String?) {
        if (!logger.isWarnEnabled)
            return

        val msg = message()
        logger.warn(msg, throwable)
    }

    fun debug(message: () -> String?) {
        if (!logger.isDebugEnabled)
            return

        val msg = message()
        logger.debug(msg)
    }

    fun debug(throwable: Throwable, message: () -> String?) {
        if (!logger.isDebugEnabled)
            return

        val msg = message()
        logger.debug(msg, throwable)
    }

    fun trace(message: () -> String?) {
        if (!logger.isTraceEnabled)
            return

        val msg = message()
        logger.trace(msg)
    }

    fun trace(throwable: Throwable, message: () -> String?) {
        if (!logger.isTraceEnabled)
            return

        val msg = message()
        logger.trace(msg, throwable)
    }
}