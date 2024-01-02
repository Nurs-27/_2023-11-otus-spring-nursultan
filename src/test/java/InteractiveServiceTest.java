import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.handler.ConsoleInputHandler;
import ru.otus.spring.model.QuestionAnswerPair;
import ru.otus.spring.model.User;
import ru.otus.spring.service.AnnounceService;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.service.impl.InteractiveServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InteractiveServiceTest {

    @Mock
    private QuizService quizService;

    @Mock
    private AnnounceService announceService;

    @Mock
    private ConsoleInputHandler consoleInputHandler;


    @InjectMocks
    private InteractiveServiceImpl interactiveService;


    private final User testUser = new User("TestFirstName", "TestLastName");
    private final List<QuestionAnswerPair> qapList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        final var qap1 = new QuestionAnswerPair("Сколько букв в русском алфавите", "33");
        final var qap2 = new QuestionAnswerPair("Сколько букв в английском алфавите", "26");

        qapList.add(qap1);
        qapList.add(qap2);
    }


    @Test
    void shouldCallAnnounceMethodWith1CorrectAnswer() {
        when(consoleInputHandler.readLine("Enter your first name:")).thenReturn(testUser.getFirstName());
        when(consoleInputHandler.readLine("Enter your last name:")).thenReturn(testUser.getLastName());

        when(quizService.loadQuestions())
                .thenReturn(qapList);

        when(consoleInputHandler.readLine("Сколько букв в русском алфавите")).thenReturn("32");
        when(consoleInputHandler.readLine("Сколько букв в английском алфавите")).thenReturn("26");

        interactiveService.testUser();

        verify(announceService, times(1)).announceQuizResult(testUser, 1);
    }
}
