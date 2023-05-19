plugins {
    `java-library`
}

group = "sk.uniza.fri"
version = "1.0-SNAPSHOT"
var gdxVersion = "1.11.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    api("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
    api("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
    api("com.badlogicgames.gdx:gdx:$gdxVersion")
}

tasks.test {
    useJUnitPlatform()
}