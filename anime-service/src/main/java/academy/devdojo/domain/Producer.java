package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Producer {

    private Long id;
    @JsonProperty("name")
    private String name;
    private LocalDateTime createdAt;


}
