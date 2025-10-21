package academy.devdojo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Anime {

    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<Anime>();

    static {
        var naruto = new Anime(1L, "Naruto");
        var boruto = new Anime(2L, "Boruto");
        var dragonBallZ = new Anime(3L, "Dragon Ball Z");

        animes.addAll(List.of(naruto, boruto, dragonBallZ));
    }

    public static List<Anime> getAnimes() {
        return animes;
    }

}
