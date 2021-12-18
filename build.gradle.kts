plugins {
    java
    id("me.champeau.jmh") version "0.6.6"
}

group = "com.overminddl1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.speiger.com/repository/main")
}

dependencies {
    jmh("de.speiger:Primitive-Collections:0.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jmh {
//    includes.empty().add("put_over")
    excludes.add("Hashers")
//    excludes.add("_pc_")
//    excludes.add("_java_")
    benchmarkMode.empty().add("avgt")
    timeUnit.set("ns")
    warmupIterations.set(1)
    iterations.set(1)
    fork.set(0)
    warmup.set("2s")
    timeOnIteration.set("2s")
}