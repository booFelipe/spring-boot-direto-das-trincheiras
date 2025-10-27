package academy.devdojo.service;

import academy.devdojo.domain.Producer;
import academy.devdojo.repository.ProducerHardCodedRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProducerServiceTest {

    @InjectMocks
    private ProducerService service;

    @Mock
    private ProducerHardCodedRepository repository;

    private List<Producer> producerList;

    @BeforeEach
    void init(){
        var kyotoAnimation = Producer.builder().id(1L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        var madHouse = Producer.builder().id(2L).name("Made House").createdAt(LocalDateTime.now()).build();
        var netflix = Producer.builder().id(3L).name("Netflix").createdAt(LocalDateTime.now()).build();

        producerList = new ArrayList<>(List.of(kyotoAnimation, madHouse, netflix));

    }

    @Test
    @DisplayName("findAll should return a list with all producers when argument is null")
    @Order(1)
    void findAll_ReturnAllProducers_WhenArgumentIsNull(){
        BDDMockito.when(repository.findAll()).thenReturn(producerList);

        var producers = service.findAll(null);
        Assertions.assertThat(producers).isNotNull().hasSize(producers.size());


    }

    @Test
    @DisplayName("findByName returns list with found object when name exists")
    @Order(2)
    void findByName_ReturnsFoundProducerInList_WhenNameIsFound() {
        var producer = producerList.getFirst();
        var expectedProducersFound = Collections.singletonList(producer);
        BDDMockito.when(repository.findByName(producer.getName())).thenReturn(expectedProducersFound);

        var producers = service.findAll(producer.getName());
        Assertions.assertThat(producers).containsAll(expectedProducersFound);
    }

    @Test
    @DisplayName("findAll returns empty list when name is not Found")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        var name = "not-found";
        BDDMockito.when(repository.findByName(name)).thenReturn(Collections.emptyList());

        var producers = service.findAll(name);
        Assertions.assertThat(producers).isNotNull().isEmpty();
    }

}