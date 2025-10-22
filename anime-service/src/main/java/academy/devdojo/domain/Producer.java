package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Producer {

    private Long id;
    @JsonProperty("name")
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<Producer>();

    static {
        var kyotoAnimation = Producer.builder().id(1L).name("Kyoto Animation").createdAt(LocalDateTime.now()).build();
        var madHouse = Producer.builder().id(2L).name("Made House").createdAt(LocalDateTime.now()).build();
        var mappa = Producer.builder().id(3L).name("Mappa").createdAt(LocalDateTime.now()).build();

        producers.addAll(List.of(kyotoAnimation, madHouse, mappa));
    }

    public static List<Producer> getProducers() {
        return producers;
    }

}
