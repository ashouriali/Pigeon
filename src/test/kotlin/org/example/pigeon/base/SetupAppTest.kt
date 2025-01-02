package org.example.pigeon.base

import org.example.pigeon.PigeonApp
import org.example.pigeon.core.config.AppMode
import io.restassured.RestAssured.port
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.ThreadLocalRandom


class SetupAppTest: BeforeAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        port = ThreadLocalRandom.current().nextInt(10000, 65000)
        PigeonApp().setUp(AppMode.TEST, port)
    }
}