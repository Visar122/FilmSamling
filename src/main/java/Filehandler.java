import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {
    private  static final String HEADER="title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched";

    private String fileName;
    private int skippedLineCount;

    public Filehandler(String fileName){
        this.fileName=fileName;
    }
    // ---------- SAVE ----------
    public void SaveMovies(ArrayList<Movie> movies)throws FileNotFoundException{
        PrintStream out=new PrintStream(new File(fileName));
        out.println(HEADER);

        for(Movie movie:movies){
            out.println(toCsvLine(movie));
        }
        out.close();
    }

     private String toCsvLine(Movie movie){  // laver  film til text
        String lastWatched="";

        if(movie.hasBeenWatched()){
            lastWatched=movie.getLastWatched().toString();
        }
        return movie.getTitle() +";"
                +movie.getDirector() +";"
                + movie.getYearCreated() + ";"
                + movie.isInColor() + ";"
                + movie.getLengthInMinutes() + ";"
                + movie.getGenre() + ";"
                + lastWatched;
     }
    // ---------- Load ----------

    public ArrayList<Movie> loadMovies() throws FileNotFoundException{
        ArrayList<Movie> movies=new ArrayList<>();
        skippedLineCount=0;

        File file=new File(fileName);
        if(!file.exists()){
            return movies;   // første run: ingne  file  -> empty list, ingne  crash
        }

        Scanner in=new Scanner(file);
        if(in.hasNextLine()){
            in.nextLine();
        }
        while (in.hasNextLine()){
            String line =in.nextLine();
            if (line.isBlank())continue;

            Movie movie=parseMovie(line);
            if(movie==null){
                skippedLineCount++;
            }else {
                movies.add(movie);
            }
        }
        in.close();
        return movies;
    }

    private Movie parseMovie(String line){
        String[] parts=line.split(";",-1);
        if (parts.length!=7){
            return  null;
        }
        try {
            String title=parts[0];
            String director=parts[1];
            int year = Integer.parseInt(parts[2]);
            if (!parts[3].equals("true") && !parts[3].equals("false")) {
                return null;
            }
            boolean inColor = Boolean.parseBoolean(parts[3]);
            int length = Integer.parseInt(parts[4]);
            String genre = parts[5];

            Movie movie = new Movie(title, director, year, inColor, length, genre); // kan  throw IllegalArgumentException
            if (!parts[6].isEmpty()) {
                movie.markAsWatched(LocalDate.parse(parts[6]));
            }
            return movie;
        }catch (IllegalArgumentException e){
            return null;
        }catch (DateTimeException e){ // bad date
            return null;
        }
    }
    public int getSkippedLineCount() {
        return skippedLineCount;
    }

}
