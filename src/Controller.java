import java.util.ArrayList;

public class Controller {

    private MovieCollection movieCollection;

    public Controller(){
        movieCollection=new MovieCollection();
    }
     public void AddMovie(String title,String director,int yearCreated,boolean inColor, int lengthsInMinutes,String genre){
        movieCollection.addMovie(title, director, yearCreated, inColor, lengthsInMinutes, genre);
     }
     public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
     }
     public ArrayList<Movie> searchMovie(String searchText){
        return movieCollection.searchMovies(searchText);
     }
     public boolean editMovie(Movie movie,String title, String director, int yearCreated,
                              boolean inColor, int lengthInMinutes, String genre){
        return movieCollection.editMovie(movie, title, director, yearCreated, inColor, lengthInMinutes, genre);
     }
     public boolean deleteMovie (Movie movie){
        return movieCollection.deleteMovie(movie);
     }
}
