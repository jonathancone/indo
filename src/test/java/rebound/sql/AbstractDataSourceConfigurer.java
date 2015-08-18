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

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created by jcone on 8/16/15.
 */
public abstract class AbstractDataSourceConfigurer {
    private static final Logger log = LoggerFactory.getLogger(AbstractDataSourceConfigurer.class);

    private DataSource dataSource;

    private String user;
    private String password;
    private String url;
    private String schemaSetupSql;
    private String driver;
    private IDatabaseTester databaseTester;

    public AbstractDataSourceConfigurer(String user, String password, String url, String schemaSetupSql, String driver) {
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

    protected final DataSource createDataSource() {
        log.info("Creating {} data source configuration...", getClass().getName());
        try {
            return doCreateDataSource();
        } catch (Exception e) {
            throw new UncheckedSQLException(e);
        }
    }

    protected final void createSchema() {
        log.info("Creating {} schema...", getClass().getName());
        try {
            doCreateSchema();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void populateSchema(String dataSetName) {
        try {

            FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(new URL(dataSetName));
            databaseTester = new JdbcDatabaseTester(getDriver(), getUrl(), getUser(), getPassword());
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        } catch (DataSetException dse) {
            if (dse.getCause() instanceof FileNotFoundException) {
                log.info("Skipping schema population since {} was not found.", dataSetName);
            } else {
                throw new RuntimeException(dse);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void assertSchema(String dataSetName) {

        try {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new URL(dataSetName));
            IDataSet actualDataSet = databaseTester.getConnection().createDataSet();

            Assertion.assertEquals(expectedDataSet, actualDataSet);
        } catch (DataSetException dse) {
            if (dse.getCause() instanceof FileNotFoundException) {
                log.info("Skipping schema assertion since {} was not found.", dataSetName);
            } else {
                throw new RuntimeException(dse);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        return getClass().getResource(getSchemaSetupSql()).toString();
    }
}
