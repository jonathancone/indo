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
 * No-op container configuration for H2.
 * H2 is an embedded database that doesn't require TestContainers.
 * This implementation allows H2 to work with the same container registry pattern
 * while using file-based configuration.
 *
 * @author Jonathan Cone
 */
public class H2ContainerConfig implements DatabaseContainerConfig {

    @Override
    public void start() {
        // No-op: H2 is embedded and doesn't need to be started
    }

    @Override
    public void stop() {
        // No-op: H2 is embedded and doesn't need to be stopped
    }

    @Override
    public boolean isRunning() {
        // H2 is always "running" as it's embedded
        return true;
    }

    @Override
    public String getJdbcUrl() {
        throw new UnsupportedOperationException("H2 uses file-based configuration from properties files");
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("H2 uses file-based configuration from properties files");
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("H2 uses file-based configuration from properties files");
    }

    @Override
    public String getDriverClassName() {
        throw new UnsupportedOperationException("H2 uses file-based configuration from properties files");
    }

    @Override
    public void applyToProperties(Properties properties) {
        // No-op: H2 uses the properties file as-is without container overrides
    }
}
