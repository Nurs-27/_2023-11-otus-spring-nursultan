package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.impl.QuizServiceImpl;


public class Main {
    public static void main(String[] args) {

        var context = new ClassPathXmlApplicationContext("applicationContext.xml");
        var service = context.getBean(QuizServiceImpl.class);

        var questions = service.loadQuestions();

        questions.forEach(x -> System.out.println(x.getQuestion()));

        System.out.println(questions);
        context.close();
    }
}