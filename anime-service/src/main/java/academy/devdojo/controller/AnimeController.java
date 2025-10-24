package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.repository.AnimeHardCodedRepository;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.response.AnimePostResponse;
import academy.devdojo.service.AnimeService;
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

    private AnimeService service;

    public AnimeController() {
        this.service = new AnimeService();
    }

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAllAnimes(@RequestParam(required = false) String name) {
        log.debug("Request to find all animes, param name '{}'", name);

        var animes = service.findAll(name);
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);

        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findbyId(@PathVariable(required = false) Long id) {
        log.debug("Request to find anime by id: {}", id);

        var anime =  service.findByIdOrThrowNotFound(id);
        var animeGetResponse = MAPPER.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest animePostRequest, @RequestHeader HttpHeaders headers) {
        log.debug("Request to save anime : {}", animePostRequest);

        var anime = MAPPER.toAnime(animePostRequest);
        var animeSaved = service.save(anime);

        var response = MAPPER.toAnimePostResponse(animeSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest animePutRequest) {
        log.debug("Request to update anime by body: {}", animePutRequest);

        var animeToUpdate = MAPPER.toAnime(animePutRequest);

        service.update(animeToUpdate);

        return ResponseEntity.noContent().build();
    }

}
