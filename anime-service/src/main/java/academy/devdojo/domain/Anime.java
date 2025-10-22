package academy.devdojo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Anime {

    private Long id;
    private String name;

    private static List<Anime> animes = new ArrayList<Anime>();


    static {
        var naruto = Anime.builder().id(1L).name("Naruto").build();
        var boruto = Anime.builder().id(2L).name("Boruto").build();
        var ondePiece = Anime.builder().id(3L).name("One Piece").build();

        animes.addAll(List.of(naruto, boruto, ondePiece));

    }

    public static List<Anime> getAnimes() {
        return animes;
    }

}
