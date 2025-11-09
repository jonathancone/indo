/*
 * Copyright 2025 Indo Contributors
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

package indo.sql.test;

import java.util.Properties;

/**
 * Interface for database container configurations.
 * Implementations manage TestContainers lifecycle and provide connection details.
 *
 * @author Jonathan Cone
 */
public interface DatabaseContainerConfig {

    /**
     * Starts the container if not already running.
     */
    void start();

    /**
     * Stops the container if running.
     */
    void stop();

    /**
     * Checks if the container is currently running.
     *
     * @return true if the container is running
     */
    boolean isRunning();

    /**
     * Gets the JDBC URL for connecting to this container.
     *
     * @return the JDBC URL
     */
    String getJdbcUrl();

    /**
     * Gets the username for connecting to this container.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Gets the password for connecting to this container.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Gets the JDBC driver class name.
     *
     * @return the driver class name
     */
    String getDriverClassName();

    /**
     * Applies container connection details to the provided properties,
     * overriding file-based configuration with container details.
     *
     * @param properties the properties to update
     */
    default void applyToProperties(Properties properties) {
        properties.setProperty("url", getJdbcUrl());
        properties.setProperty("user", getUsername());
        properties.setProperty("password", getPassword());
        properties.setProperty("driver", getDriverClassName());
    }
}
