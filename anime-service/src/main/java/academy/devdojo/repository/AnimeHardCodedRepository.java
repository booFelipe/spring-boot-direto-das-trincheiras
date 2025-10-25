package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeHardCodedRepository {

    private static final List<Anime> ANIMES = new ArrayList<Anime>();

    static {
        var naruto = Anime.builder().id(1L).name("Naruto").build();
        var boruto = Anime.builder().id(2L).name("Boruto").build();
        var onePiece = Anime.builder().id(3L).name("One Piece").build();

        ANIMES.addAll(List.of(naruto, boruto, onePiece));

    }

    public List<Anime> findAll() {
        return ANIMES;
    }

    public Optional<Anime> findById(Long id) {
        return ANIMES.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> findByName(String name) {
        return ANIMES.stream().filter(anime -> anime.getName().equals(name)).toList();
    }

    public Anime save(Anime anime) {
        ANIMES.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIMES.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }

}
