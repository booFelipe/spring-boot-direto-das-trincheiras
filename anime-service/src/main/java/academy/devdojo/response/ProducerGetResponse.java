package academy.devdojo.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProducerGetResponse {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private LocalDateTime createdAt;

}
