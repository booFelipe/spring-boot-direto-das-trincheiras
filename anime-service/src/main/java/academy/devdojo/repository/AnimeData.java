package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {

    private final List<Anime> animes = new ArrayList<Anime>();

     {
         var naruto = Anime.builder().id(1L).name("Naruto").build();
         var boruto = Anime.builder().id(2L).name("Boruto").build();
         var onePiece = Anime.builder().id(3L).name("One Piece").build();

        animes.addAll(List.of(naruto, boruto, onePiece));
    }

    public List<Anime> getAnimes() {
        return animes;
    }
}
