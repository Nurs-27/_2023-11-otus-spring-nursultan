package ru.otus.spring.service;

import ru.otus.spring.model.QuestionAnswerPair;

import java.util.List;

public interface QuizService {


    List<QuestionAnswerPair> loadQuestions();

}
