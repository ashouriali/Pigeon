package org.example.pigeon.base

import io.restassured.RestAssured.port
import org.example.pigeon.PigeonApp
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext



class SetupAppTest: BeforeAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        val appAddress = PigeonApp().setUp()
        port = appAddress.port
    }
}