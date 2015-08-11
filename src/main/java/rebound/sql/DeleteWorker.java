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
 * // Delete employee from a table called "Employee" binding all non-null fields on employee instance to the columns on Employee with the same name.
 * sql.delete(employee).run();
 *
 * // Same as above, but overriding table name.
 * sql.delete(employee).from("FullTimeEmployee").run();
 *
 * // Delete a single record using only the specified columns.
 * sql.delete(employee).includingOnly("firstName", "lastName").run();
 *
 * // Delete a single record overriding the column names in the database
 * sql.delete(employee).mapping("firstName", "FIRST_NAME").run();
 *
 * // Perform a bulk delete (uses JDBC batch statement)
 * sql.delete(collectionOfEmployees).run();
 * </pre>
 */
public interface DeleteWorker<T> {

    DeleteWorker<T> delete(T object);

    DeleteWorker<T> delete(Collection<T> objects);

    DeleteWorker<T> in(String table);

    DeleteWorker<T> includingOnly(String... fields);

    DeleteWorker<T> excludingOnly(String... fields);

    DeleteWorker<T> mapping(String... fieldColumn);

    DeleteWorker<T> mapping(Map<String, String> fieldColumn);

    Integer run();

}
