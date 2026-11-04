package domainmodel;

import datasource.FileHandler;
import domainmodel.Comparator.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {

    private MovieCollection movieCollection;
    private FileHandler filehandler;
    private boolean changed=false; // Us14:  har noget blevet  skiftet (changed)
    public Controller(){
        movieCollection=new MovieCollection();
        filehandler=new FileHandler("movies.csv");
    }


    public void AddMovie(Movie movie){
        movieCollection.addMovie(movie);
        changed=true;
    }
    public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
    }

    public ArrayList<Movie> searchMovies(String searchText){
        return movieCollection.searchMovies(searchText);
    }

    public boolean editMovie(Movie movie,String title, String director, int yearCreated,
                             boolean inColor, int lengthInMinutes, String genre){

        boolean ok= movieCollection.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);
        if(ok){changed=true;}
        return ok;
    }

    public boolean deleteMovie (Movie movie){
        boolean ok=  movieCollection.deleteMovie(movie);
        if(ok) {changed= true;}
        return ok;
    }

    public void markAsWatched(Movie movie,LocalDate date) {
        movie.markAsWatched(date);
        changed = true;
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days) {
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }


    public  int loadMovies() throws FileNotFoundException {
        ArrayList<Movie> loadedmovies=filehandler.loadMovies();
        for (Movie movie:loadedmovies){
            movieCollection.addMovie(movie);
        }
        return loadedmovies.size();
    }

    public boolean SaveMoviesifChanged()throws FileNotFoundException{
        if(!changed){
            return false;
        }
        filehandler.saveMovies(movieCollection.getMovies());
        changed=false;
        return true;
    }
    public int getSkippedLineCount() {
        return filehandler.getSkippedLineCount();
    }


    public ArrayList<Movie> getMoviesSortedByTitle() {
        return movieCollection.getMoviesSortedByTitle();
    }

    public ArrayList<Movie> getMoviesSortedBy(SortField field) {
        return movieCollection.getMoviesSorted(comparatorFor(field));
    }



    // Det ENESTE sted, hvor brugerens valg oversættes til en comparator
    private Comparator<Movie> comparatorFor(SortField field) {
        return switch (field) {
            case TITLE -> new TitleComparator();
            case DIRECTOR -> new DirectorComparator();
            case YEAR -> new YearComparator();
            case LENGTH -> new LengthComparator();
            case GENRE -> new GenreComparator();
        };
    }

}