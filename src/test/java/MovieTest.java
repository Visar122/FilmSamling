import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void newMovieHasNotBeenWatched() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        // Act + Assert
        assertFalse(movie.hasBeenWatched());
        assertNull(movie.getLastWatched());
    }

    @Test
    void markAsWatchedRemembersTheDate() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        LocalDate date = LocalDate.of(2025, 9, 30);

        // Act
        movie.markAsWatched(date);

        // Assert
        assertTrue(movie.hasBeenWatched());
        assertEquals(date, movie.getLastWatched());
    }

    @Test
    void daysSinceWatchedCountsAcrossMonths() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");
        movie.markAsWatched(LocalDate.of(2025, 9, 30));

        // Act
        long days = movie.getDaysSinceWatched(LocalDate.of(2025, 10, 26));

        // Assert
        assertEquals(26, days);
    }
}