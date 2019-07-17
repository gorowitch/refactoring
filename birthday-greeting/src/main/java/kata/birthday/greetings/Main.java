package kata.birthday.greetings;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
        BirthdayService service = new BirthdayService();
        service.sendGreetings("employee_data.txt", LocalDate.now(), "localhost", 25);
    }
}
