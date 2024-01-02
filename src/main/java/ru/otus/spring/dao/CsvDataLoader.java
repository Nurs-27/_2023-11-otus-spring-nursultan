package ru.otus.spring.dao;

import java.io.InputStream;

public interface CsvDataLoader {

    InputStream loadData(String path);
}
