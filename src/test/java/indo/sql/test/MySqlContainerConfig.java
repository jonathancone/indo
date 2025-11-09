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

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * TestContainers configuration for MySQL.
 * Manages a MySQL container instance for integration testing.
 *
 * @author Jonathan Cone
 */
public class MySqlContainerConfig implements DatabaseContainerConfig {

    private static final String DEFAULT_IMAGE = "mysql:8.0";
    private static final String DEFAULT_DATABASE = "test";
    private static final String DEFAULT_USERNAME = "test";
    private static final String DEFAULT_PASSWORD = "test";

    private final MySQLContainer<?> container;

    public MySqlContainerConfig() {
        this(DEFAULT_IMAGE);
    }

    public MySqlContainerConfig(String dockerImageName) {
        this.container = new MySQLContainer<>(DockerImageName.parse(dockerImageName))
                .withDatabaseName(DEFAULT_DATABASE)
                .withUsername(DEFAULT_USERNAME)
                .withPassword(DEFAULT_PASSWORD)
                .withReuse(true); // Reuse container across test runs for faster execution
    }

    @Override
    public void start() {
        if (!container.isRunning()) {
            container.start();
        }
    }

    @Override
    public void stop() {
        if (container.isRunning()) {
            container.stop();
        }
    }

    @Override
    public boolean isRunning() {
        return container.isRunning();
    }

    @Override
    public String getJdbcUrl() {
        return container.getJdbcUrl();
    }

    @Override
    public String getUsername() {
        return container.getUsername();
    }

    @Override
    public String getPassword() {
        return container.getPassword();
    }

    @Override
    public String getDriverClassName() {
        return container.getDriverClassName();
    }

    /**
     * Gets the underlying MySQL container instance.
     * Use this for advanced configuration if needed.
     *
     * @return the MySQL container
     */
    public MySQLContainer<?> getContainer() {
        return container;
    }
}
