package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "v1/animes")
public class AnimeController {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AnimeController.class);
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAllAnimes(@RequestParam(required = false) String name) {
        log.debug("Request to find all animes, param name '{}'", name);

        var animes = Anime.getAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        if (name == null) return ResponseEntity.ok(animeGetResponseList);

        var response = animeGetResponseList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findbyId(@PathVariable(required = false) Long id) {
        log.debug("Request to find anime by id: {}", id);

        var animeGetResponse = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest animePostRequest, @RequestHeader HttpHeaders headers) {
        log.debug("Request to save anime : {}", animePostRequest);

        var anime = MAPPER.toAnime(animePostRequest);
        Anime.getAnimes().add(anime);

        var response = MAPPER.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);
        var animeToDelete = Anime.getAnimes()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));

        log.debug("anime: {}", animeToDelete);
        Anime.getAnimes().remove(animeToDelete);

        return ResponseEntity.noContent().build();
    }
}
