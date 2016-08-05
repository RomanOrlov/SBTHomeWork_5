package ru.sbt.orlov.inclass;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataBaseStore dataBaseStore = new DataBaseStore(new DbImpl());
        dataBaseStore.save("wow");
        dataBaseStore.save("suchFun");
        dataBaseStore.save("MuchHate");
        System.out.println(dataBaseStore.getAll());
        //dataBaseStore.save("I WANT EXCEPTION");

        FileStore fileStore = new FileStore("C:\\Users\\Roman\\Desktop\\temp.txt");
        fileStore.save("WOW");
        fileStore.save("LOVE DOGE");
        fileStore.save("DOGE SO CUTE");
    }
}
