package academy.devdojo.repository;

import academy.devdojo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {

    private final List<User> users = new ArrayList<User>();

    {
        var usr1 = User.builder().id(1L).firstName("Felipe").lastName("Araujo").email("felipergfm@gmail.com").build();
        var usr2 = User.builder().id(2L).firstName("Magali").lastName("Lima").email("lilima@gmail.com").build();
        var usr3 = User.builder().id(3L).firstName("João").lastName("Bento").email("joao@gmail.com").build();
        users.addAll(List.of(usr1, usr2, usr3));
    }

    public List<User> getUsers() {
        return users;
    }
}
