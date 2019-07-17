package kata.birthday.greetings;

import com.dumbster.smtp.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private BirthdayService birthdayService;
    private SimpleSmtpServer mailServer;

    @BeforeEach
    void setUp() throws Exception {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
        birthdayService = new BirthdayService();
    }

    @AfterEach
    void tearDown() throws Exception {
        mailServer.stop();
        Thread.sleep(200);
    }

    @Test
    void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        birthdayService.sendGreetings("employee_data.txt", LocalDate.of(2008, 10, 8), "localhost", NONSTANDARD_PORT);

        assertThat(mailServer.getReceivedEmails().size()).describedAs("message not sent?").isEqualTo(1);
        SmtpMessage message = mailServer.getReceivedEmails().get(0);
        assertThat(message.getBody()).isEqualTo("Happy Birthday, dear John!");
        assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!");
        List<String> recipients = message.getHeaderValues("To");
        assertThat(recipients.size()).isEqualTo(1);
        assertThat(recipients.get(0)).isEqualTo("john.doe@foobar.com");
    }

    @Test
    void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        birthdayService.sendGreetings("employee_data.txt", LocalDate.of(2008, 1, 1), "localhost", NONSTANDARD_PORT);

        assertThat(mailServer.getReceivedEmails().size()).describedAs("what? messages?").isEqualTo(0);
    }
}
