import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.hibernate:hibernate-gradle-plugin:5.6.1.Final")
    }
}

apply(plugin = "org.hibernate.orm")

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.jpa") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.experimental.aot") version "0.11.0-RC1"
}

group = "br.com.silveira.raphael"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    maven { url = uri("https://repo.spring.io/milestone") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.microutils:kotlin-logging:2.1.15")
    implementation("mysql:mysql-connector-java")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

tasks.withType<org.hibernate.orm.tooling.gradle.EnhanceTask>().configureEach {
    options.enableLazyInitialization = true
    options.enableDirtyTracking = true
    options.enableAssociationManagement = true
    options.enableExtendedEnhancement = false
}
