package ru.otus.spring.service;

import ru.otus.spring.model.User;

import java.util.concurrent.atomic.AtomicInteger;

public interface AnnounceService {

    void announceQuizResult(User user, int correctAnswerCount);
}
