import org.jetbrains.kotlin.gradle.tasks.KotlinTest

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.50"
}
repositories {
    mavenCentral()
}

group = "graphlib"
version = "0.0.1"

apply {
    plugin("maven-publish")
}

kotlin {
    val metadata = metadata()
    val jvm = jvm()
    val js = js {
        browser {
        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    val linuxX64 = linuxX64() {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val linuxX64Main by getting {

        }
        val linuxX64Test by getting {
        }
    }
}

tasks {
    getByName<KotlinTest>("jsBrowserTest") {
        binaryResultsDirectory.set(binResultsDir)
    }
    getByName<KotlinTest>("jsNodeTest") {
        binaryResultsDirectory.set(binResultsDir)
    }
    getByName<KotlinTest>("linuxX64Test") {
        binaryResultsDirectory.set(binResultsDir)
    }
}
