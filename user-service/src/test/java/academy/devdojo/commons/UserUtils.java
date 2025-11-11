package academy.devdojo.commons;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    public List<User> newUserList() {
        var usr1 = User.builder().id(1L).firstName("Kakashi").lastName("Araujo").email("felipergfm@gmail.com").build();
        var usr2 = User.builder().id(1L).firstName("Pedro").lastName("Lima").email("lilima@gmail.com").build();
        var usr3 = User.builder().id(1L).firstName("Sasuke").lastName("Bento").email("joao@gmail.com").build();

        return new ArrayList<>(List.of(usr1, usr2, usr3));

    }

    public User newUserToSave() {
        return User.builder().id(99L).firstName("Ash").lastName("Ketchun").email("pokemon@gmail.com").build();
    }
}
