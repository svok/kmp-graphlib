import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest
import org.jetbrains.kotlin.gradle.tasks.KotlinTest

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.50"
}
repositories {
    mavenCentral()
}

group = "graphlib"
version = "0.1.1"

apply {
    plugin("maven-publish")
}

kotlin {
    metadata()
    jvm() {
        withJava()
        val main by compilations.getting {
            kotlinOptions.jvmTarget = "12"
        }
        val test by compilations.getting {
            kotlinOptions.jvmTarget = "12"
        }
    }
    js {
        browser {
        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    linuxX64() {
        binaries {
            sharedLib()
            staticLib()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.1")
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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("org.junit.jupiter:junit-jupiter:5.5.2")
                implementation("org.assertj:assertj-core:3.11.1")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.1")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val linuxX64Main by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.1")
            }
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
    getByName<KotlinJvmTest>("jvmTest") {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    getByName<KotlinTest>("linuxX64Test") {
        binaryResultsDirectory.set(binResultsDir)
    }

}
