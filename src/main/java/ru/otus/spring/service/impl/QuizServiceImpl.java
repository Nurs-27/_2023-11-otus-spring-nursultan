package ru.otus.spring.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CsvDataLoader;
import ru.otus.spring.model.QuestionAnswerPair;
import ru.otus.spring.service.QuizService;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final CsvDataLoader csvDataLoader;

    private final String quizPath;

    public QuizServiceImpl(CsvDataLoader csvDataLoader, @Value("${quiz.path}") String quizPath) {
        this.csvDataLoader = csvDataLoader;
        this.quizPath = quizPath;
    }

    @Override
    public List<QuestionAnswerPair> loadQuestions() {
        final var inputStream = csvDataLoader.loadData(quizPath);
        return new CsvToBeanBuilder<QuestionAnswerPair>(new InputStreamReader(inputStream))
                .withType(QuestionAnswerPair.class)
                .build()
                .parse();
    }
}
