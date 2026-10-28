import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class MovieCollection {
    private ArrayList<Movie> movies;


    public MovieCollection(){
        movies = new ArrayList<>();
    }


    public void addMovie(String title,String director,int yearCreated,boolean inColor,int lengthInMinutes,String genre){
        Movie movie= new Movie(title,director,yearCreated,inColor,lengthInMinutes,genre);
        movies.add(movie);
    }

    public ArrayList<Movie>getMovies(){
        return movies;
    }

    public int getNumberofMovies(){
        return  movies.size();
    }

    public ArrayList<Movie> searchMovies(String searchText){
        ArrayList<Movie>result= new ArrayList<>();
        String search=searchText.toLowerCase();
        for(Movie movie :movies){
            if(movie.getTitle().toLowerCase().contains(search)){
                result.add(movie);
            }
        }
        return  result;
    }

    public boolean editMovie(Movie movie,String title,String director,int yearCreated,boolean inColor,int lengthInMinutes,String genre){
        if(!movies.contains(movie)){
            return false;
        }
        new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);
        return true;


    }

    public boolean deleteMovie(Movie movie){

        return movies.remove(movie);
    }

    // Finder alle film, der aldrig er set, eller som sidst blev set før date
    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.hasBeenWatched() || movie.getLastWatched().isBefore(date)) {
                result.add(movie);
            }
        }
        return result;
    }

}