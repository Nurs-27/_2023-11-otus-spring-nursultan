package ru.otus.spring.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.model.QuestionAnswerPair;
import ru.otus.spring.service.QuizService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final String resourcePath;

    @Override
    public List<QuestionAnswerPair> loadQuestions() {
        try {
            ClassPathResource resource = new ClassPathResource(resourcePath);
            return new CsvToBeanBuilder<QuestionAnswerPair>(new InputStreamReader(resource.getInputStream()))
                    .withType(QuestionAnswerPair.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
