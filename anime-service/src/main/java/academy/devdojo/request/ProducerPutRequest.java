package academy.devdojo.request;

import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@ToString
public class ProducerPutRequest {
    private Long id;
    private String name;

}
