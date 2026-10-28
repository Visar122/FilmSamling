import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void neverWatchedMovieIsIncluded() {
        // Arrange
        MovieCollection collection = new MovieCollection();
        collection.addMovie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror");
        Movie psycho = collection.getMovies().get(0);

        // Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(LocalDate.of(2025, 10, 1));

        // Assert
        assertTrue(result.contains(psycho));
    }

    @Test
    void onlyMoviesWatchedBeforeTheDateAreIncluded() {
        // Arrange
        LocalDate limit = LocalDate.of(2025, 10, 1);
        MovieCollection collection = new MovieCollection();
        collection.addMovie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        collection.addMovie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror");
        collection.addMovie("Godzilla", "Ishiro Honda", 1954, false, 96, "Sci-fi");
        Movie before = collection.getMovies().get(0);
        Movie onTheDate = collection.getMovies().get(1);
        Movie after = collection.getMovies().get(2);
        before.markAsWatched(limit.minusDays(1));
        onTheDate.markAsWatched(limit);
        after.markAsWatched(limit.plusDays(1));

        // Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(limit);

        // Assert
        assertTrue(result.contains(before));
        assertFalse(result.contains(onTheDate));
        assertFalse(result.contains(after));
    }
    @Test
    void editWithInvalidValueChangesNothing() {
        MovieCollection collection = new MovieCollection();
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act: ny titel er fin, men årstallet er ugyldigt
        assertThrows(IllegalArgumentException.class,
                () -> collection.editMovie(casablanca, "Casablanca 2", "Michael Curtiz", 1800, false,
                        102, "Drama"));

        // Assert: titlen er IKKE ændret – filmen er ikke blevet halvt redigeret
        assertEquals("Casablanca", casablanca.getTitle());
        assertEquals(1942, casablanca.getYearCreated());
    }
}