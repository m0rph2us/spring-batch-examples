repositories {
    mavenCentral()
    maven( url = "https://packages.confluent.io/maven/" )
    maven( url = "https://kotlin.bintray.com/kotlinx" )
}

plugins {
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.jpa") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
}