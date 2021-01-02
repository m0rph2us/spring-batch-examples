import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.serialization")
}

group = "com.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven/")
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation("org.hsqldb:hsqldb:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.21")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.21")
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}