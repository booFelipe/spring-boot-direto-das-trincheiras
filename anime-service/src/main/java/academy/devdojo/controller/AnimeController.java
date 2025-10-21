package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "v1/animes")
public class AnimeController {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AnimeController.class);

    @GetMapping
    public List<Anime> listAllAnimes(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimes();
        if (name == null) return Anime.getAnimes();

        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime findbyId(@PathVariable(required = false) Long id) {
        return Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst().orElse(null);

    }

    @PostMapping
    public Anime save(@RequestBody Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(100000));
        Anime.getAnimes().add(anime);
        return anime;
    }
}
