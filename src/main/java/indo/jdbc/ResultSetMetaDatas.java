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
            rsm.getColumnCount();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static boolean isAutoIncrement(ResultSetMetaData rsm, int column) {
        try {
            rsm.isAutoIncrement(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static boolean isCaseSensitive(ResultSetMetaData rsm, int column) {
        try {
            rsm.isCaseSensitive(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static boolean isSearchable(ResultSetMetaData rsm, int column) {
        try {
            rsm.isSearchable(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static boolean isCurrency(ResultSetMetaData rsm, int column) {
        try {
            rsm.isCurrency(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static int isNullable(ResultSetMetaData rsm, int column) {
        try {
            rsm.isNullable(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static boolean isSigned(ResultSetMetaData rsm, int column) {
        try {
            rsm.isSigned(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static int getColumnDisplaySize(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnDisplaySize(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static String getColumnLabel(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnLabel(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static String getColumnName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static String getSchemaName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getSchemaName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static int getPrecision(ResultSetMetaData rsm, int column) {
        try {
            rsm.getPrecision(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static int getScale(ResultSetMetaData rsm, int column) {
        try {
            rsm.getScale(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static String getTableName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getTableName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static String getCatalogName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getCatalogName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static int getColumnType(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnType(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return 0;
    }

    public static String getColumnTypeName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnTypeName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static boolean isReadOnly(ResultSetMetaData rsm, int column) {
        try {
            rsm.isReadOnly(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static boolean isWritable(ResultSetMetaData rsm, int column) {
        try {
            rsm.isWritable(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static boolean isDefinitelyWritable(ResultSetMetaData rsm, int column) {
        try {
            rsm.isDefinitelyWritable(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }

    public static String getColumnClassName(ResultSetMetaData rsm, int column) {
        try {
            rsm.getColumnClassName(column);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static <T> T unwrap(ResultSetMetaData rsm, Class<T> iface) {
        try {
            rsm.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return null;
    }

    public static boolean isWrapperFor(ResultSetMetaData rsm, Class<?> iface) {
        try {
            rsm.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return false;
    }
}
