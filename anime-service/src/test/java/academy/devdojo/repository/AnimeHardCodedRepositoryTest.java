package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnimeHardCodedRepositoryTest {

    @InjectMocks
    private AnimeHardCodedRepository repository;

    @Mock
    private AnimeData animeData;
    private List<Anime> animeList;

    @BeforeEach
    void init(){
        var onePunch = Anime.builder().id(1L).name("One Punch").build();
        var onePiece = Anime.builder().id(2L).name("One Piece").build();
        var giantsOfGear = Anime.builder().id(3L).name("Giants Of Gear").build();

        animeList = new ArrayList<>(List.of(onePunch, onePiece, giantsOfGear));

    }

    @Test
    @DisplayName("findAll should return a list with all animes")
    @Order(1)
    void findAll_ReturnAllAnimes_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animes = repository.findAll();
        Assertions.assertThat(animes).isNotNull().hasSize(animes.size());
    }

    @Test
    @DisplayName("findById returns a anime with given id")
    @Order(2)
    void findAll_ReturnsAnimeById_WhenSuccessful(){
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var expectedAnime = animeList.getFirst();
        var animes = repository.findById(expectedAnime.getId());

        Assertions.assertThat(animes).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("findByName returns empty list when name is null")
    @Order(3)
    void findByName_ReturnsEmptyList_WhenNameIsNull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animes = repository.findByName(null);
        Assertions.assertThat(animes).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("findByName returns list with found object when name exists")
    @Order(4)
    void findByName_ReturnsFoundAnimeInList_WhenNameIsFound() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var expectedAnime = animeList.getFirst();
        var animes = repository.findByName(expectedAnime.getName());
        Assertions.assertThat(animes).hasSize(1).contains(expectedAnime);
    }

    @Test
    @DisplayName("save creates a anime")
    @Order(5)
    void save_CreatesAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToSave = Anime.builder().id(99L).name("Pokémon").build();
        var anime = repository.save(animeToSave);

        Assertions.assertThat(anime).isEqualTo(animeToSave).hasNoNullFieldsOrProperties();

        var animeSavedOptional = repository.findById(animeToSave.getId());

        Assertions.assertThat(animeSavedOptional).isPresent().contains(animeToSave);
    }

    @Test
    @DisplayName("delete removes a anime")
    @Order(6)
    void delete_RemoveAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToDelete = animeList.getFirst();
        repository.delete(animeToDelete);

        var animes = repository.findAll();

        Assertions.assertThat(animes).isNotEmpty().doesNotContain(animeToDelete);
    }

    @Test
    @DisplayName("update updates a anime")
    @Order(7)
    void update_UpdatesAnime_WhenSuccessful() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animeList);

        var animeToUpdate = this.animeList.getFirst();
        animeToUpdate.setName("Anime Alterado");

        repository.update(animeToUpdate);

        Assertions.assertThat(this.animeList).contains(animeToUpdate);

        var animeUpdatedOptional = repository.findById(animeToUpdate.getId());

        Assertions.assertThat(animeUpdatedOptional).isPresent();
        Assertions.assertThat(animeUpdatedOptional.get().getName()).isEqualTo(animeToUpdate.getName());
    }

  
}