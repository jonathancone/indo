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
 * // Insert employee into a table called "Employee" binding all non-null fields on employee instance to the columns on Employee with the same name.
 * sql.insert(employee).run();
 *
 * // Same as above, but overriding table name.
 * sql.insert(employee).into("FullTimeEmployee").run();
 *
 * // Insert a single record and sets any generated key value back into a field titled employee.employeeId
 * sql.insert(employee).generate().run();
 *
 * // Same as above, but overring the generated key field name.
 * sql.insert(employee).generate("payrollId").run();
 *
 * // Insert a single record using only the specified columns.
 * sql.insert(employee).includingOnly("firstName", "lastName").run();
 *
 * // Insert a single record overriding the column names in the database
 * sql.insert(employee).mapping("firstName", "FIRST_NAME").run();
 *
 * // Perform a bulk insert (uses JDBC batch statement)
 * sql.insert(collectionOfEmployees).run();
 * </pre>
 */
public interface InsertWorker<T> {
    InsertWorker<T> insert(T object);

    InsertWorker<T> insert(Collection<T> objects);

    InsertWorker<T> in(String table);

    InsertWorker<T> includingOnly(String... fields);

    InsertWorker<T> excludingOnly(String... fields);

    InsertWorker<T> mapping(String... fieldColumn);

    InsertWorker<T> mapping(Map<String, String> fieldColumn);

    InsertWorker<T> generate(String... columns);

    Integer run();
}
