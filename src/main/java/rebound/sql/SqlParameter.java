package rebound.sql;

import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by jcone on 8/1/15.
 */
public class SqlParameter {

    private String name;

    /**
     * The actual value that is being bound, might be a String, Date, List, etc.
     */
    private Object value;

    /**
     * The type that this value should be bound as, can be inferred but is also able to be overridden.
     */
    private Class<?> type;

    private SortedSet<Integer> indexes;

    public SqlParameter(String name, Object value, Class<?> type) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.indexes = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public List<Integer> getIndexes() {
        return ListUtils.unmodifiableList(new ArrayList<>(indexes));
    }

    public void addIndex(Integer index) {
        indexes.add(index);
    }

    public void addIndexes(int start, int length) {
        for (int i = start; i < length; i++) {
            addIndex(i);
        }
    }

    public Integer getMaxIndex() {
        return indexes.last();
    }
}
