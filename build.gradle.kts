import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
}

allprojects {
    group = "net.spacetivity.entity"
    version = "1.0-SNAPSHOT"

    repositories {
        maven {
            url = uri("https://nexus.spacetivity.net/repository/maven-public/")
            credentials {
                username = property("nexusUsername") as String
                password = property("nexusPassword") as String
            }
        }
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

	val gsonVersion: String by project
	val paperServerVersion: String by project	
    val protocolLibVersion: String by project

    dependencies {
		compileOnly("com.google.code.gson:gson:$gsonVersion")
		compileOnly("io.papermc.paper:paper-api:$paperServerVersion-R0.1-SNAPSHOT")
        compileOnly("com.comphenix.protocol:ProtocolLib:$protocolLibVersion-SNAPSHOT")
    }
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}