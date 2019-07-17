package kata.birthday.greetings;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class EmployeeTest {

    @Test
    void testBirthday() throws Exception {
        Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");

        assertThat(employee.isBirthday(LocalDate.of(2008, 1, 30)))
                .describedAs("not his birthday").isFalse();
        assertThat(employee.isBirthday(LocalDate.of(2008, 1, 31)))
                .describedAs("his birthday").isTrue();
    }

    @Test
    void equality() throws Exception {
        Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
        Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
        Employee different = new Employee("First", "Last", "1999/09/01", "boom@boom.com");

        assertThat(base.equals(null)).isFalse();
        assertThat(base.equals("")).isFalse();
        assertThat(base.equals(same)).isTrue();
        assertThat(base.equals(different)).isFalse();
    }
}
