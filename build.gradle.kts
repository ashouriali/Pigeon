import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "org.example.pigeon"
description = "pigeon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jcenter.bintray.com")
    }

    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {

    implementation("args4j:args4j:2.37")
    implementation("io.javalin:javalin:6.1.6")
    implementation("com.google.inject:guice:7.0.0")
    implementation("com.typesafe:config:1.4.2")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
//    implementation("org.slf4j:slf4j-api:2.0.9")
//    implementation("ch.qos.logback:logback-classic:1.5.15")
//    implementation("ch.qos.logback:logback-core:1.5.15")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.22.0")
    implementation("org.apache.logging.log4j:log4j-api:2.22.0")
    implementation("org.apache.logging.log4j:log4j-core:2.22.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.11.0")

    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.41.1")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.github.f4b6a3:tsid-creator:5.2.6")
    implementation("com.auth0:java-jwt:4.4.0")


    //test
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    target {
        compilerOptions {
            optIn.add("kotlin.RequiresOptIn")
            jvmTarget = JvmTarget.JVM_17
        }

    }
}

application {

    println(group)
    mainClass.set("$group.PigeonAppKt")
}