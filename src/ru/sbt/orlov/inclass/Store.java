package ru.sbt.orlov.inclass;

import java.util.List;

public interface Store {

    void save(String s);

    List<String> getAll();
}
