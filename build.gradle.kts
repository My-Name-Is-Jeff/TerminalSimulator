import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.CobbleSword.NachoSpigot:api:74ed0ea729:sources")
    compileOnly("com.github.CobbleSword.NachoSpigot:nachospigot:74ed0ea729")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
}

group = "net.dungeonsim"
version = "1.0"
description = "DungeonSim"

bukkit {
    name = "DungeonSimulator"
    version = "1.0.0"
    description = "Dungeon Simulator core plugin"
    website = "https://dungeonsim.net/"

    main = "net.dungeonsim.dungeonsimulator.DungeonSimulator"
    apiVersion = "1.8"

    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    authors = listOf("My-Name-Is-Jeff", "Nick", "Prince")
    prefix = "DSIM"
    defaultPermission = BukkitPluginDescription.Permission.Default.OP

    commands {
        register("locraw") {
            description = "Fake LocRaw command"
        }

        register("terminal") {
            description = "Open Terminal Simulator"
        }
    }
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        filteringCharset = "UTF-8"
    }

    javadoc {
        options.encoding = "UTF-8"
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs =
                listOf("-opt-in=kotlin.RequiresOptIn", "-Xjvm-default=all", "-Xrelease=17", "-Xbackend-threads=0")
        }
        kotlinDaemonJvmArguments.set(
            listOf(
                "-Xmx2G",
            )
        )
    }

    named<Jar>("jar") {
        enabled = false
        dependsOn(shadowJar)
    }

    withType<ShadowJar> {
        archiveFileName.set(jar.get().archiveFileName)
    }

    register<Copy>("copyPlugin") {
        from("$buildDir/libs/")
        include("*.jar")
        into("$projectDir/server/plugins/")
        dependsOn(shadowJar.get())
    }

    build.get().dependsOn(named("copyPlugin"))
}

kotlin {
    jvmToolchain {
        check(this is JavaToolchainSpec)
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}