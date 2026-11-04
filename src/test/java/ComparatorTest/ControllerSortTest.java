package ComparatorTest;
import domainmodel.Controller;
import domainmodel.Movie;
import domainmodel.SortField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ControllerSortTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.AddMovie(new Movie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror"));
        controller.AddMovie(new Movie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Drama"));
        controller.AddMovie(new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama"));
        controller.AddMovie(new Movie("The Godfather Part II", "Francis Ford Coppola", 1974, true, 202, "Drama"));
        controller.AddMovie(new Movie("Vertigo", "Alfred Hitchcock", 1958, true, 128, "Thriller"));
    }

    @Test
    void moviesSortedByTitle() {
        ArrayList<Movie> sorted = controller.getMoviesSortedByTitle();
        assertEquals("Casablanca", sorted.get(0).getTitle());
        assertEquals("Psycho", sorted.get(1).getTitle());
        assertEquals("The Godfather", sorted.get(2).getTitle());
        assertEquals("The Godfather Part II", sorted.get(3).getTitle());
        assertEquals("Vertigo", sorted.get(4).getTitle());
    }

    @Test
    void sortingDoesNotChangeTheCollection() {
        controller.getMoviesSortedByTitle();
        controller.getMoviesSortedBy(SortField.YEAR);

        ArrayList<Movie> movies = controller.getMovies();
        assertEquals("Psycho", movies.get(0).getTitle());
        assertEquals("The Godfather", movies.get(1).getTitle());
        assertEquals("Casablanca", movies.get(2).getTitle());
        assertEquals("The Godfather Part II", movies.get(3).getTitle());
        assertEquals("Vertigo", movies.get(4).getTitle());
    }

    @Test
    void moviesSortedByYear() {
        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.YEAR);
        assertEquals(1942, sorted.get(0).getYearCreated());
        assertEquals(1958, sorted.get(1).getYearCreated());
        assertEquals(1974, sorted.get(4).getYearCreated());
    }

    @Test
    void moviesSortedByLength() {
        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.LENGTH);
        assertEquals("Casablanca", sorted.get(0).getTitle());
        assertEquals("The Godfather Part II", sorted.get(4).getTitle());
    }

    @Test
    void moviesSortedByDirectorAndThenYear() {
        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.DIRECTOR);
        assertEquals("Vertigo", sorted.get(0).getTitle());
        assertEquals("Psycho", sorted.get(1).getTitle());
        assertEquals("The Godfather", sorted.get(2).getTitle());
        assertEquals("The Godfather Part II", sorted.get(3).getTitle());
        assertEquals("Casablanca", sorted.get(4).getTitle());
    }
}

