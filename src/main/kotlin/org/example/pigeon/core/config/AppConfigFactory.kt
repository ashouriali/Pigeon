package org.example.pigeon.core.config

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.typesafe.config.Config
import com.typesafe.config.ConfigBeanFactory
import org.example.pigeon.core.guice.Factory

@Factory
class AppConfigFactory : AbstractModule() {

    @Provides
    @Singleton
    fun createAppConfig(config: Config): AppConfig {
        return ConfigBeanFactory.create(config, AppConfig::class.java)
    }
}