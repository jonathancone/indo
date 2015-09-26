/*
 * Copyright 2015 Indo Contributors
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

package indo.example;

import indo.sql.SqlRunner;

import javax.sql.DataSource;

/**
 * Examples of how to use the {@link SqlRunner} API.
 *
 * @author Jonathan Cone
 */
public class SqlRunnerExamples {
    private DataSource dataSource;

    public void queryExamples() {
        SqlRunner runner = new SqlRunner(dataSource);
    }
}
