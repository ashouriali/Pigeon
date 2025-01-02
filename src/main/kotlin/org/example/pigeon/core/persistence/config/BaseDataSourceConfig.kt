package org.example.pigeon.core.persistence.config

abstract class BaseDataSourceConfig {
    lateinit var url: String
    lateinit var name: String
    lateinit var username: String
    lateinit var password: String
}