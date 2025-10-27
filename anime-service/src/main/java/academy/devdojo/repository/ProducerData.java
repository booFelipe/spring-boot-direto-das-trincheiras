package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {

    private final List<Producer> producers = new ArrayList<Producer>();

     {
        var kyotoAnimation = Producer.builder().id(1L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        var madHouse = Producer.builder().id(2L).name("Made House").createdAt(LocalDateTime.now()).build();
        var mappa = Producer.builder().id(3L).name("Mappa").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(kyotoAnimation, madHouse, mappa));
    }

    public List<Producer> getProducers() {
        return producers;
    }
}
