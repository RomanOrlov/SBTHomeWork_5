package ru.sbt.orlov.inclass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbImpl  implements Db {

    ArrayList<String> strings = new ArrayList<>();

    public void insert(String string) throws SQLException {
        strings.add(string);
    }

    public List<String> selectAll() throws SQLException {
        return strings;
    }

    @Override
    public void close() {
        // resource closed
        System.out.println("Resource closed");
    }
}
