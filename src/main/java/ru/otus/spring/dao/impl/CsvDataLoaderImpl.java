package ru.otus.spring.dao.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.CsvDataLoader;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class CsvDataLoaderImpl implements CsvDataLoader {

    @Override
    public InputStream loadData(String path) {
        ClassPathResource resource = new ClassPathResource(path);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
