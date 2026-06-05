plugins {
    java
    application
}

group = "it.unibo.agrobot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("it.unibo.agrobot.App")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        // Mostra gli eventi di test superati, falliti o saltati
        events("passed", "skipped", "failed")
        // Mostra le eccezioni in modo dettagliato se un test fallisce
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "it.unibo.agrobot.App"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.register<Copy>("copyFatJarToRoot") {
    dependsOn(tasks.jar)
    from(tasks.jar.flatMap { it.archiveFile })
    into(layout.projectDirectory)
    rename { "agro-bot.jar" }
    doNotTrackState("Destination is the project root, which contains unreadable/locked files")
}

tasks.build {
    finalizedBy("copyFatJarToRoot")
}
