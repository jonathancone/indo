/*
 * Copyright 2017 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.github.ben-manes.versions") version "0.52.0"
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

dependencies {

    compileOnly("org.slf4j:slf4j-api:2.0.16")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    
    testCompileOnly("org.slf4j:slf4j-simple:2.0.16")
    testCompileOnly("com.h2database:h2:2.3.232")
    testCompileOnly("org.postgresql:postgresql:42.7.5")
    testCompileOnly("mysql:mysql-connector-java:8.0.33")
    testCompileOnly("org.apache.ant:ant:1.10.15")
    testCompileOnly("org.mockito:mockito-core:5.15.2")
    testCompileOnly("org.dbunit:dbunit:3.0.0")

}
