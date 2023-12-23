package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.QuestionAnswerPair;
import ru.otus.spring.model.User;
import ru.otus.spring.service.InteractiveService;
import ru.otus.spring.service.QuizService;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class InteractiveServiceImpl implements InteractiveService {

    private final QuizService quizService;

    @Override
    public void testUser() {
        final var scanner = new Scanner(System.in);
        final var user = requestInitialInformation(scanner);
        AtomicInteger correctAnswerCount = new AtomicInteger();

        quizService.loadQuestions()
                .forEach(qap -> {
                    System.out.println(qap.getQuestion());
                    final var enteredAnswer = scanner.nextLine();
                    checkEnteredAnswer(qap, enteredAnswer, correctAnswerCount);
                });

        announceResult(user, correctAnswerCount);
        scanner.close();
    }

    private User requestInitialInformation(Scanner scanner) {
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        return new User(firstName, lastName);
    }

    private void checkEnteredAnswer(QuestionAnswerPair qap, String enteredAnswer, AtomicInteger correctAnswerCount) {
        if (qap.getAnswer().equalsIgnoreCase(enteredAnswer)) {
            correctAnswerCount.getAndIncrement();
        }
    }

    private void announceResult(User user, AtomicInteger correctAnswerCount) {
        System.out.printf("User: %s %s has answered correctly for %s questions%n", user.getFirstName(), user.getLastName(), correctAnswerCount);
    }
}
