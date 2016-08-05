package ru.sbt.orlov.inclass;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileStore implements Store {
    private final File file;

    public FileStore(File file) {
        this.file = file;
    }

    public FileStore(String name) {
        file = new File(name);
        System.out.println("is exist "+file.exists());
    }

    @Override
    public List<String> getAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            throw new StoreException("Exception while trying to GET strings from NOT EXISTING FILE with name"+file.getName(),ex);
        } catch (IOException ex) {
            throw new StoreException("Exception while trying to GET strings from "+file.getName(),ex);
        }
    }

    @Override
    public void save(String s) {
        try(FileWriter fileWriter = new FileWriter(file,true)) {
            fileWriter.write(s);
            fileWriter.flush();
        } catch (FileNotFoundException ex) {
            throw new StoreException("Exception while trying to SAVE strings from NOT EXISTING FILE with name"+file.getName(),ex);
        } catch (IOException ex) {
            throw new StoreException("Exception while trying to SAVE strings from "+file.getName(),ex);
        }
    }
}
