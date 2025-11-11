package academy.devdojo.service;

import academy.devdojo.commons.UserUtils;
import academy.devdojo.domain.User;
import academy.devdojo.repository.UserHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserHardCodedRepository repository;

    private List<User> userList;

    @InjectMocks
    private UserUtils userUtils;

    @BeforeEach
    void init() {
        userList = userUtils.newUserList();
    }

    @Test
    @DisplayName("findAll should return a list with all users when argument is null")
    @Order(1)
    void findAll_ReturnAllUsers_WhenArgumentIsNull() {
        BDDMockito.when(repository.findAll()).thenReturn(userList);

        var users = service.findAll(null);
        org.assertj.core.api.Assertions.assertThat(users).isNotNull().hasSize(users.size());


    }

    @Test
    @DisplayName("findByName returns list with found object when firstName exists")
    @Order(2)
    void findByName_ReturnsFoundProducerInList_WhenFirstNameIsFound() {
        var user = userList.getFirst();
        var expectedProducersFound = Collections.singletonList(user);
        BDDMockito.when(repository.findByFirstName(user.getFirstName())).thenReturn(expectedProducersFound);

        var users = service.findAll(user.getFirstName());
        org.assertj.core.api.Assertions.assertThat(users).containsAll(expectedProducersFound);
    }

    @Test
    @DisplayName("findAll returns empty list when name is not Found")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenFirstNameIsNull() {
        var firstName = "not-found";
        BDDMockito.when(repository.findByFirstName(firstName)).thenReturn(Collections.emptyList());

        var users = service.findAll(firstName);
        Assertions.assertThat(users).isNotNull().isEmpty();
    }

}