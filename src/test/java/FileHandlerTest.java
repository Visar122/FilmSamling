import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHandlerTest {
    private static final String TEST_FILE="test-movies.csv";

    @AfterEach
    void deleteTestFile(){
        new File(TEST_FILE).delete();
    }

    @Test
    void saveAndLoadGivesSameMovies() throws FileNotFoundException {
        Filehandler fileHandler = new Filehandler(TEST_FILE);

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller"));
        movies.add(new Movie("Adams æbler", "Anders Thomas Jensen", 2005, true, 94, "Komedie"));
        movies.get(0).markAsWatched(LocalDate.of(2025, 12, 24));

        fileHandler.saveMovies(movies);
        ArrayList<Movie> loaded = fileHandler.loadMovies();

        assertEquals(2, loaded.size());

        Movie first = loaded.get(0);
        assertEquals("Jaws", first.getTitle());
        assertEquals("Steven Spielberg", first.getDirector());
        assertEquals(1975, first.getYearCreated());
        assertTrue(first.isInColor());
        assertEquals(124, first.getLengthInMinutes());
        assertEquals("Thriller", first.getGenre());

        // checks that æ ø å survive the round trip
        assertEquals("Adams æbler", loaded.get(1).getTitle());
    }
}
