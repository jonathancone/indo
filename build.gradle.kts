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
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}
repositories {
    mavenCentral()
}

// Test
dependencies {

    compileOnly("org.slf4j:slf4j-api:1.7.33")

    testCompileOnly("org.slf4j:slf4j-simple:1.7.33")
    testCompileOnly("com.h2database:h2:2.1.210")
    testCompileOnly("org.postgresql:postgresql:42.3.1")
    testCompileOnly("mysql:mysql-connector-java:8.0.25")
    testCompileOnly("org.apache.ant:ant:1.10.11")
    testCompileOnly("junit:junit:4.13.2")
    testCompileOnly("org.mockito:mockito-core:4.2.0")
    testCompileOnly("org.dbunit:dbunit:2.7.2")

}
