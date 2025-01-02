package org.example.pigeon

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Key
import io.javalin.Javalin
import org.example.pigeon.core.component.BaseComponent
import org.example.pigeon.core.config.AppMode
import org.example.pigeon.core.controller.BaseController
import org.example.pigeon.core.guice.Factory
import org.example.pigeon.core.utils.ReflectionUtils

fun main(args: Array<String>) {
    val params = PigeonParams.create(args)
    PigeonApp().setUp(AppMode.fromString(params.mode), params.port)
}

class PigeonApp {
    private lateinit var injector: Injector

    companion object {
        lateinit var appMode: AppMode
    }

    fun setUp(mode: AppMode, port: Int) {
        appMode = mode
        createGuiceModules()
        setUpWebServer(port)
        setUpControllers()
        setUpComponents()
    }

    private fun createGuiceModules() {
        val modules = ReflectionUtils.scanAnnotation(Factory::class.java) ?: error("")
        injector = Guice.createInjector(modules.map { it.getConstructor().newInstance() as AbstractModule })
    }

    private fun setUpWebServer(port: Int) {
        injector.getInstance(Javalin::class.java).start(port)
    }

    private fun setUpControllers() {
        val controllers = ReflectionUtils.scanType(BaseController::class.java) ?: error("")
        controllers.onEach { injector.getInstance(Key.get(it)).handleRequests() }
    }

    private fun setUpComponents() {
        val components = ReflectionUtils.scanType(BaseComponent::class.java) ?: error("")
        components.onEach { injector.getInstance(Key.get(it)) }
    }

}