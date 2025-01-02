package org.example.pigeon.core.server

import com.google.inject.Inject
import com.google.inject.Singleton
import io.javalin.Javalin
import org.example.pigeon.core.server.httpMethod.Get
import org.example.pigeon.core.server.httpMethod.Patch
import org.example.pigeon.core.server.httpMethod.Post
import org.example.pigeon.core.server.httpMethod.Put
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions

@Singleton
class AppServer @Inject constructor(private val javalin: Javalin) {

    fun setUpControllers(controllers: List<BaseController>) {

        controllers.forEach { controller ->
            controller::class.memberFunctions.forEach { func ->
                func.findAnnotation<Get>()?.run {
                    javalin.get(path) { func.call(controller, it) }
                }
                func.findAnnotation<Post>()?.run {
                    javalin.post(path) { func.call(controller, it) }
                }

                func.findAnnotation<Put>()?.run {
                    javalin.put(path) { func.call(controller, it) }
                }

                func.findAnnotation<Patch>()?.run {
                    javalin.patch(path) { func.call(controller, it) }
                }
            }
        }

    }
}
