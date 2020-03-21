package ru.liga.intership.badcode;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.liga.intership.badcode.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadcodeApplicationTests {
    PersonService personService;

    @Before
    public void init() {
        personService = new PersonService();
    }

    @Test
    public void wrongConnectToDataBaseTest1() {
        Assertions.assertThatThrownBy(() -> personService.connectToDataBase("blablabla", "sa", ""));
    }

    @Test
    public void wrongConnectToDataBaseTest2() {
        Assertions.assertThatThrownBy(() -> personService.connectToDataBase("jdbc:hsqldb:mem:test", "blablabla", ""));
    }

    @Test
    public void wrongConnectToDataBaseTest3() {
        Assertions.assertThatThrownBy(() -> personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "blablabla"));
    }

    @Test
    public void wrongSQL_Query1() {
        personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        Assertions.assertThatThrownBy(() -> personService.doQuery("blablabla"));
    }

    @Test
    public void wrongSQL_Query2() {
        personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        Assertions.assertThatThrownBy(() -> personService.doQuery("SELECT * FROM person WHERE sex = blablabla AND age > 18"));
    }

    @Test
    public void wrongSQL_Query3() {
        personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        Assertions.assertThatThrownBy(() -> personService.doQuery("SELECT * FROM blablabla WHERE sex = 'male' AND age > 18"));
    }

    @Test
    public void doSQL_QueryBeforeConnect() {
        Assertions.assertThatThrownBy(() -> {
            personService.doQuery("SELECT * FROM person WHERE sex = 'male' AND age > 1800");
            personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        });
    }

    @Test
    public void methodMainTest() {
        personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        personService.doQuery("SELECT * FROM person WHERE sex = 'male' AND age > 18");
        Assertions.assertThat(personService.getSelectedPersonsAverageBMI()).isEqualTo(25.774209960992707d);
    }

    @Test
    public void zeroPersonTest() {
        personService.connectToDataBase("jdbc:hsqldb:mem:test", "sa", "");
        personService.doQuery("SELECT * FROM person WHERE sex = 'male' AND age > 1800");
        Assertions.assertThat(personService.getSelectedPersonsAverageBMI()).isEqualTo(0);
    }
}
