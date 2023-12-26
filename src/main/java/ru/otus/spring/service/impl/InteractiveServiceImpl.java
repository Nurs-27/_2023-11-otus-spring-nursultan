package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.handler.ConsoleInputHandler;
import ru.otus.spring.model.QuestionAnswerPair;
import ru.otus.spring.model.User;
import ru.otus.spring.service.AnnounceService;
import ru.otus.spring.service.InteractiveService;
import ru.otus.spring.service.QuizService;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

    private final QuizService quizService;

    private final AnnounceService announceService;

    private final ConsoleInputHandler consoleInputHandler;

    @Override
    public void testUser() {
        final var user = requestInitialUserInformation();
        AtomicInteger correctAnswerCount = new AtomicInteger();

        quizService.loadQuestions()
                .forEach(qap -> {
                    final var enteredAnswer = consoleInputHandler.readLine(qap.getQuestion());
                    checkEnteredAnswer(qap, enteredAnswer, correctAnswerCount);
                });

        announceService.announceQuizResult(user, Integer.parseInt(correctAnswerCount.toString()));
    }

    private User requestInitialUserInformation() {
        String firstName = consoleInputHandler.readLine("Enter your first name:");
        String lastName = consoleInputHandler.readLine("Enter your last name:");

        return new User(firstName, lastName);
    }

    private void checkEnteredAnswer(QuestionAnswerPair qap, String enteredAnswer, AtomicInteger correctAnswerCount) {
        if (qap.getAnswer().equalsIgnoreCase(enteredAnswer)) {
            correctAnswerCount.getAndIncrement();
        }
    }
}
