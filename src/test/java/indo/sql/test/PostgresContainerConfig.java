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

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * TestContainers configuration for PostgreSQL.
 * Manages a PostgreSQL container instance for integration testing.
 *
 * @author Jonathan Cone
 */
public class PostgresContainerConfig implements DatabaseContainerConfig {

    private static final String DEFAULT_IMAGE = "postgres:16-alpine";
    private static final String DEFAULT_DATABASE = "test";
    private static final String DEFAULT_USERNAME = "test";
    private static final String DEFAULT_PASSWORD = "test";

    private final PostgreSQLContainer<?> container;

    public PostgresContainerConfig() {
        this(DEFAULT_IMAGE);
    }

    public PostgresContainerConfig(String dockerImageName) {
        this.container = new PostgreSQLContainer<>(DockerImageName.parse(dockerImageName))
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
     * Gets the underlying PostgreSQL container instance.
     * Use this for advanced configuration if needed.
     *
     * @return the PostgreSQL container
     */
    public PostgreSQLContainer<?> getContainer() {
        return container;
    }
}
