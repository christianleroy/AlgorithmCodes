package com.theclcode.simulation.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Premium LeetCode
// https://leetcode.com/problems/design-sql/description/
public class SQL {

    Map<String, List<String[]>> tables = new HashMap<>();

    public SQL(List<String> names, List<Integer> columns) {

        for (int i = 0; i < names.size(); i++) {
            List<String[]> row = new ArrayList<>();
            row.add(new String[columns.get(i)]);
            tables.put(names.get(i), row);
        }

    }

    public boolean ins(String name, List<String> row) {
        List<String[]> table = tables.get(name);

        if (table == null || table.get(0).length != row.size()) {
            return false;
        }

        String[] insertedRow = row.toArray(new String[0]);
        table.add(insertedRow);
        return true;

    }

    public void rmv(String name, int rowId) {
        List<String[]> table = tables.get(name);

        if (table != null && rowId > 0 && rowId < table.size()) {
            table.set(rowId, null);
        }
    }

    public String sel(String name, int rowId, int columnId) {
        List<String[]> table = tables.get(name);
        if (table != null && rowId > 0 && rowId < table.size()) {
            if(table.get(rowId) != null) {
                String[] row = table.get(rowId);
                if(columnId >= 0 && columnId-1 < row.length) {
                    return row[columnId-1];
                }
            }
        }

        return null;
    }

    public List<String> exp(String name) {
        List<String> allRows = new ArrayList<>();
        List<String[]> rows = tables.get(name);

        if(rows != null) {
            for(int i = 1; i < rows.size(); i++ ) {
                if(rows.get(i) != null) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(i);
                    for(String cols : rows.get(i)) {
                        builder.append(",").append(cols);
                    }
                }
            }
        }

        return allRows;
    }
}
