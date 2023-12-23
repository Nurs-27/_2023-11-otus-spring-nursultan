package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.InteractiveService;

@Component
@ComponentScan("ru.otus.spring")
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(Main.class);
        var service = context.getBean(InteractiveService.class);

        service.testUser();

        context.close();
    }
}

