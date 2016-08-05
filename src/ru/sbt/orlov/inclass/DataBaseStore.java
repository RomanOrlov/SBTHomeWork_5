package ru.sbt.orlov.inclass;

import java.sql.SQLException;
import java.util.List;

public class DataBaseStore implements Store {

    private final Db db;

    public DataBaseStore(Db db) {
        this.db = db;
    }

    @Override
    public List<String> getAll() {
        try (Db dbResource = db) {
            return db.selectAll();
        } catch (SQLException e) {
            throw new Dbxception("Error while trying to select all",e);
        }
    }

    @Override
    public void save(String s) {
        try (Db dbResource = db) {
            if (s.equals("I WANT EXCEPTION")) {
                throw new SQLException("GDS EXCEPTION err code 3256456");
            }
            db.insert(s);
        } catch (SQLException e) {
            throw new Dbxception("Error while trying to save "+s,e);
        }
    }
}
