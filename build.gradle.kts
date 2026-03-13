plugins {
    java
    id("me.champeau.jmh") version "0.7.3"
}

group = "com.overminddl1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.speiger.com/repository/main")
}

dependencies {
    jmh("de.speiger:Primitive-Collections:0.9.0")
    jmh("it.unimi.dsi:fastutil:8.5.18")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jmh {
//    includes.empty().add("put_over")
    includes.empty().add("PutsObject")
    excludes.add("Hashers")
//    excludes.add("_pc_")
//    excludes.add("_java_")
    threads.set(1)
    benchmarkMode.empty().add("avgt")
    timeUnit.set("ns")
    warmupIterations.set(1)
    iterations.set(2)
    fork.set(2)
    warmup.set("1s")
    timeOnIteration.set("1s")
}
