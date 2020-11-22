package me.rubl.lesson_two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContentValues {

    private ArrayList<String> columnNames;
    private Map<String, Object> values;

    public ContentValues() {
        columnNames = new ArrayList<String>();
        values = new HashMap<String, Object>();
    }

    public boolean isEmpty() {
        return columnNames == null || columnNames.size() == 0;
    }

    public boolean contains(String columnName) {
        return columnNames.contains(columnName);
    }

    public void put(String columnName, Object value) {
        columnNames.add(columnName);
        values.put(columnName, value);
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public Object getValue(String columnName) {
        return values.get(columnName);
    }

    public void remove(String columnName) {
        columnNames.remove(columnName);
        values.remove(columnName);
    }
}
