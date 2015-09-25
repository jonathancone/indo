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

package indo.sql;

import indo.util.Unchecked;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.DataSource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static indo.log.Logs.debug;
import static indo.log.Logs.info;

/**
 * Created by jcone on 8/16/15.
 */
public abstract class AbstractDbUnitConfigurer {

    private DataSource dataSource;

    private IDatabaseTester databaseTester;

    private String driver;
    private String password;
    private String schemaSetupSql;
    private String url;
    private String user;

    public AbstractDbUnitConfigurer(String user, String password, String url, String schemaSetupSql, String driver) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.schemaSetupSql = schemaSetupSql;
        this.driver = driver;
        this.dataSource = createDataSource();
        createSchema();
    }

    protected abstract DataSource doCreateDataSource() throws Exception;

    protected abstract void doCreateSchema() throws Exception;

    protected abstract DefaultDataTypeFactory getDataTypeFactory();

    protected final DataSource createDataSource() {
        info(this, "Creating %s data source configuration...", getClass().getName());
        try {
            return doCreateDataSource();
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    protected final void createSchema() {
        debug(this, "Creating %s schema...", getClass().getName());
        try {
            doCreateSchema();
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    private ReplacementDataSet createDataSet(String dataSetName) throws Exception {
        ReplacementDataSet dataSet = new ReplacementDataSet(
                new FlatXmlDataSetBuilder()
                        .setCaseSensitiveTableNames(false)
                        .build(new URL(dataSetName)));
        dataSet.addReplacementObject("[null]", null);
        return dataSet;
    }

    protected void populateSchema(String dataSetName) {
        try {
            databaseTester = new JdbcDatabaseTester(getDriver(), getUrl(), getUser(), getPassword());

            DatabaseConfig config = databaseTester.getConnection().getConfig();
            config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, Boolean.TRUE);
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, getDataTypeFactory());

            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setDataSet(createDataSet(dataSetName));
            databaseTester.onSetup();
        } catch (MalformedURLException m) {
            debug(this, "Skipping schema population since %s was not found.", dataSetName);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    protected void assertSchema(String dataSetName) {
        try {
            IDataSet expectedDataSet = createDataSet(dataSetName);
            IDataSet actualDataSet = databaseTester.getConnection().createDataSet();

            Assertion.assertEquals(expectedDataSet, actualDataSet);
        } catch (MalformedURLException m) {
            debug(this, "Skipping schema assertion since %s was not found.", dataSetName);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    public Object getValue(String tableName, String column, int row) {
        try {
            return databaseTester
                    .getConnection()
                    .createDataSet()
                    .getTable(tableName)
                    .getValue(row, column);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }


    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getSchemaSetupSql() {
        return schemaSetupSql;
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    protected String getFullSchemaSetupSqlPath() {
        return new File(getClass().getResource(getSchemaSetupSql()).getFile()).getAbsolutePath();
    }
}
