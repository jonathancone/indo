/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

import java.util.Collection;
import java.util.Map;

/**
 * <pre>
 * Employee employee = new Employee("Bill", "Gates");
 *
 * // Update employee in a table called "Employee" binding all non-null fields on employee instance to the columns on Employee with the same name.
 * sql.update(employee).run();
 *
 * // Same as above, but overriding table name.
 * sql.update(employee).in("FullTimeEmployee").run();
 *
 * // Update a single record using only the specified columns.
 * sql.update(employee).includingOnly("firstName", "lastName").run();
 *
 * // Update a single record overriding the column names in the database
 * sql.update(employee).mapping("firstName", "FIRST_NAME").run();
 *
 * // Perform a bulk update (uses JDBC batch statement)
 * sql.update(collectionOfEmployees).run();
 * </pre>
 */
public interface UpdateWorker<T> {
    UpdateWorker<T> update(T object);

    UpdateWorker<T> update(Collection<T> objects);

    UpdateWorker<T> in(String table);

    UpdateWorker<T> includingOnly(String... fields);

    UpdateWorker<T> excludingOnly(String... fields);

    UpdateWorker<T> mapping(String... fieldColumn);

    UpdateWorker<T> mapping(Map<String, String> fieldColumn);

    Integer run();
}
