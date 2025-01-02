package org.example.pigeon.core.utils

import com.github.f4b6a3.tsid.TsidFactory
import com.google.inject.Inject
import com.google.inject.Singleton
import org.example.pigeon.core.config.AppConfig
import java.util.concurrent.ThreadLocalRandom


typealias TSID = Long

@Singleton
class IdGenerator @Inject constructor(config: AppConfig) {
    private val idFactory = TsidFactory.builder()
        .withNodeBits(8)
        .withNode(config.applicationNumber)
        .withRandomFunction { length: Int ->
            val bytes = ByteArray(length)
            ThreadLocalRandom.current().nextBytes(bytes)
            bytes
        }.build()

    fun next(): TSID {
        return idFactory.create().toLong()
    }
}