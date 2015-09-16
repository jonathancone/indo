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

package indo.jdbc;

import indo.util.Unchecked;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by jcone on 9/11/2015.
 */
public class ResultSetMetaDatas {

    public static int getColumnCount(ResultSetMetaData rsm) {
        try {
            return rsm.getColumnCount();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isAutoIncrement(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isAutoIncrement(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isCaseSensitive(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isCaseSensitive(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isSearchable(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isSearchable(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isCurrency(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isCurrency(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int isNullable(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isNullable(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isSigned(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isSigned(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getColumnDisplaySize(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnDisplaySize(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getColumnLabel(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnLabel(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getColumnName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getSchemaName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getSchemaName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getPrecision(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getPrecision(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getScale(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getScale(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getTableName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getTableName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getCatalogName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getCatalogName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getColumnType(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnType(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getColumnTypeName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnTypeName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isReadOnly(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isReadOnly(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isWritable(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isWritable(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isDefinitelyWritable(ResultSetMetaData rsm, int column) {
        try {
            return rsm.isDefinitelyWritable(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getColumnClassName(ResultSetMetaData rsm, int column) {
        try {
            return rsm.getColumnClassName(column);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static <T> T unwrap(ResultSetMetaData rsm, Class<T> iface) {
        try {
            return rsm.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isWrapperFor(ResultSetMetaData rsm, Class<?> iface) {
        try {
            return rsm.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }
}
