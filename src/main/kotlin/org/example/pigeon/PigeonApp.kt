package org.example.pigeon

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Key
import io.javalin.Javalin
import org.example.pigeon.core.guice.Factory
import org.example.pigeon.core.server.AppServer
import org.example.pigeon.core.server.BaseController
import org.example.pigeon.core.utils.ReflectionUtils

fun main(args: Array<String>) {
    PigeonApp().setUp()
}

data class PigeonAppAddress(val host: String, val port: Int)

class PigeonApp {
    private lateinit var injector: Injector


    fun setUp(): PigeonAppAddress {
        createGuiceModules()
        val appAddress = startWebServer()
        setUpControllers()
        return appAddress
    }

    private fun createGuiceModules() {
        val modules = ReflectionUtils.scanAnnotation(Factory::class.java) ?: error("")
        injector = Guice.createInjector(modules.map { it.getConstructor().newInstance() as AbstractModule })
    }

    private fun startWebServer(): PigeonAppAddress {
        val javalin = injector.getInstance(Javalin::class.java)
        javalin.start()
        val serverUri = javalin.jettyServer().server().uri
        return PigeonAppAddress(serverUri.host, serverUri.port)
    }

    private fun setUpControllers() {
        val controllers: List<BaseController> = ReflectionUtils.scanType(BaseController::class.java)?.map {
            injector.getInstance(Key.get(it))
        } ?: error("There is no controller!")

        val appServer = injector.getInstance(AppServer::class.java)
       appServer.setUpControllers(controllers)
    }

}