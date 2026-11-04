package ComparatorTest;

import domainmodel.Comparator.*;
import domainmodel.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ComparatorTest {

    private Movie casablanca;
    private Movie psycho;
    private Movie jaws;
    private Movie vertigo;

    @BeforeEach
    void setUp() {
        casablanca = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        psycho = new Movie("Psycho", "Alfred Hitchcock", 1960, false, 109, "Horror");
        jaws = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        vertigo = new Movie("Vertigo", "Alfred Hitchcock", 1958, true, 128, "Thriller");
    }

    @Test
    void titleComparatorSortsAlphabetically() {
        TitleComparator comparator = new TitleComparator();
        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(vertigo, jaws) > 0);
    }

    @Test
    void titleComparatorIgnoresCase() {
        TitleComparator comparator = new TitleComparator();
        Movie lowerCase = new Movie("casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        Movie apocalypse = new Movie("apocalypse Now", "Francis Ford Coppola", 1979, true, 147, "Krig");

        assertEquals(0, comparator.compare(casablanca, lowerCase));
        assertTrue(comparator.compare(apocalypse, casablanca) < 0);
    }

    @Test
    void directorComparatorSortsAlphabetically() {
        DirectorComparator comparator = new DirectorComparator();
        assertTrue(comparator.compare(psycho, casablanca) < 0);
        assertTrue(comparator.compare(jaws, casablanca) > 0);
        assertEquals(0, comparator.compare(psycho, vertigo));
    }

    @Test
    void yearComparatorSortsOldestFirst() {
        YearComparator comparator = new YearComparator();
        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(jaws, psycho) > 0);
        assertEquals(0, comparator.compare(psycho,
                new Movie("Spartacus", "Stanley Kubrick", 1960, true, 197, "Drama")));
    }

    @Test
    void lengthComparatorSortsShortestFirst() {
        LengthComparator comparator = new LengthComparator();
        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(vertigo, jaws) > 0);
    }

    @Test
    void genreComparatorSortsAlphabetically() {
        GenreComparator comparator = new GenreComparator();
        assertTrue(comparator.compare(casablanca, psycho) < 0);
        assertTrue(comparator.compare(jaws, psycho) > 0);
        assertEquals(0, comparator.compare(jaws, vertigo));
    }

    @Test
    void naturalOrderIsByTitle() {
        assertTrue(casablanca.compareTo(psycho) < 0);
        assertTrue(vertigo.compareTo(jaws) > 0);
        assertEquals(0, casablanca.compareTo(
                new Movie("CASABLANCA", "Michael Curtiz", 1942, false, 102, "Drama")));
    }
}

