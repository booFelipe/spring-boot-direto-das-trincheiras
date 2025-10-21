package academy.devdojo.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/heroes")
public class HeroController {
    private static final List<String> HEROES = List.of("Guts", "Zoro", "Kakashi", "Goku");

    @GetMapping
    private List<String> getAllHeroes() {
        return HEROES;
    }

    @GetMapping("filter")
    private List<String> getAllHeroesParam(@RequestParam(required = false) String name) {
        return HEROES.stream().filter(hero -> hero.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterList")
    private List<String> getAllHeroesParamList(@RequestParam(defaultValue = "") List<String> names) {
        return HEROES.stream().filter(names::contains).toList();
    }

    @GetMapping("{nameHero}")
    private String findbyName(@PathVariable(name = "nameHero") String name) {
        return HEROES
                .stream()
                .filter(hero -> hero.equalsIgnoreCase(name))
                .findFirst().orElse("");
    }
}
