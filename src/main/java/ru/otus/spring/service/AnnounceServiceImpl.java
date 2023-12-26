package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;


@Service
public class AnnounceServiceImpl implements AnnounceService {

    @Override
    public void announceQuizResult(User user, int correctAnswerCount) {
        System.out.printf("User: %s %s has answered correctly for %s questions%n", user.getFirstName(), user.getLastName(), correctAnswerCount);
    }
}
