import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void StartProgram() {
        boolean running = true;
        while (running) {

            System.out.println("Velkommen til filmsamling");
            System.out.println("1. Opret en film");
            System.out.println("2. Vis alle film");
            System.out.println("3. Søg film");
            System.out.println("4. Redigér en film");
            System.out.println("5. Slet en film");
            System.out.println("6. Markér en film som set i dag");
            System.out.println("7. Find film, du ikke har set længe");
            System.out.println("0. Afslut");
            System.out.print("Valg : ");

            String valg = scanner.nextLine().trim();
            switch (valg) {
                case "1" -> CreateMovie();
                case "2" -> ShowAllMovies();
                case "3" -> SearchMovies();
                case "4" -> EditMovie();
                case "5" -> DeleteMovie();
                case "6" -> markMovieAsWatched();
                case "7" -> showMoviesNotWatched();


                case "0" -> {
                    System.out.println("Farvel!");
                    running = false;
                }

                default -> System.out.println(" Ugyldigt valg,prøv igen.");

            }

        }


    }

    private void CreateMovie() {
        System.out.print("Titel: ");
        String title = scanner.nextLine();
        System.out.print("Director: ");
        String director = scanner.nextLine();
        int year = readInt("Årstal: ");
        boolean inColor = ReadYesNo("Er Filmen i farver ja/nej : ", null);
        int length = readInt("Længde i minutter: ");
        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        controller.AddMovie(title, director, year, inColor, length, genre);
        System.out.println("Filmen -" + title + "- er tilføjet");
    }

    private void ShowAllMovies() {
        ArrayList<Movie> movies = controller.getMovies();
        if (movies.isEmpty()) {
            System.out.println("Der er ingen film");
            return;
        }
        for (Movie movie : movies) {
            printMovie(movie);
        }
    }

    private void SearchMovies() {
        System.out.println("Søg efter titel: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> result = controller.searchMovies(searchText);

        if (result.isEmpty()) {
            System.out.println("Ingen gilm fundet.");
            return;
        }
        for (Movie movie : result) {
            printMovie(movie);
        }

    }

    private Movie FindtheMovie() {
        System.out.println("Søg titel");
        ArrayList<Movie> result = controller.searchMovies(scanner.nextLine());
        if (result.isEmpty()) {
            System.out.println("Ingeng film fundet ");
            return null; // Gør at programmet ikke kører ned
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ". " + result.get(i).GetTitle());
            ; // resultt.get(index) tager filmen på det sted: tager så  result.get(0) // The Godfather          result.get(1)   // Godzilla
            // i+1 fordi fx the Godfather index det 0= så brugeren  ser 1.godfather  i stedet for 0.godfather
        }

         while(true){
        int number = readInt("Vælg nummer  ( (0) til at fortrud) : ");


        if (number == 0) {
            System.out.println("Fortrudt");
            return null;
        }
        if (number >= 1 && number <= result.size()) {
            return result.get(number - 1);
        }
        System.out.println("Nummeret -" + number + "er ikke på listen - Prøv igen ");
         }
    }

    private void EditMovie(){
        Movie movie=FindtheMovie();
        if (movie==null)return;

        System.out.println("Tryk Enter for at beholde den nuværende værdi.");

        System.out.print("Title (" +movie.GetTitle() +") : ");
        String title=scanner.nextLine();
        if (title.isBlank()) title = movie.GetTitle();

        System.out.print("Director (" +movie.getDirector() +") : ");
        String director=scanner.nextLine();
        if (director.isBlank()) director = movie.getDirector();

        int year = readIntOrDefault("Årstal (" + movie.getYearCreated() + "): ", movie.getYearCreated());


        boolean inColor = ReadYesNo("i farver ? ja/nej("+ colorText(movie)+ "):",movie.isInColor());

        int length = readIntOrDefault("Længde i minutter (" + movie.getLengthInMinutes() + "): ", movie.getLengthInMinutes());

        System.out.print("Genre (" + movie.getGenre() + "): ");
        String genre=scanner.nextLine();
        if(genre.isBlank())genre=movie.getGenre();

        if(controller.editMovie(movie,title,director,year,inColor,length,genre)){
            System.out.println("Filmen er opdateret");
            printMovie(movie);
        }
        else {
            System.out.println("Filmen kunne ikke findes.");
        }




    }

    private void DeleteMovie(){
        Movie movie=FindtheMovie();

        if(movie==null)return;


        if (ReadYesNo("Er  du sikker på at du vil slette ("+ movie.GetTitle() +") ja/nej : " ,null )) {
            if (controller.deleteMovie(movie)) {
                System.out.println("Filmen er slettet.");
            } else {
                System.out.println("Filmen kunne ikke findes.");
            }
        } else {
            System.out.println("Sletning annulleret.");
        }

    }

    private void printMovie(Movie movie) {
        System.out.println("----------------------------");
        System.out.println("Titel:      " + movie.GetTitle());
        System.out.println("Instruktør: " + movie.getDirector());
        System.out.println("Årstal:     " + movie.getYearCreated());
        System.out.println("Farve:      " + colorText(movie));
        System.out.println("Længde:     " + movie.getLengthInMinutes() + " minutter");
        System.out.println("Genre:      " + movie.getGenre());
        System.out.println("Sidst set:  " + lastWatchedText(movie));
    }
    private String colorText(Movie movie) {
        return movie.isInColor() ? "farve" : "sort-hvid";
    }

    // markér en film som set i dag
    private void markMovieAsWatched() {
        Movie movie = FindtheMovie();
        if (movie == null) return;

        controller.markAsWatched(movie);
        System.out.println(movie.GetTitle() + " er markeret som set " + movie.getLastWatched().format(DATE_FORMAT) + ".");
    }

    // vis film, der ikke er set i et antal dage
    private void showMoviesNotWatched() {
        int days = readInt("Hvor mange dage siden mindst? ");

        ArrayList<Movie> movies = controller.getMoviesNotWatchedForDays(days);
        if (movies.isEmpty()) {
            System.out.println("Du har set alle film inden for de sidste " + days + " dage.");
            return;
        }
        System.out.println("Film, du ikke har set i " + days + " dage eller aldrig har set:");
        for (Movie movie : movies) {
            System.out.println("  " + movie.GetTitle() + " – sidst set: " + lastWatchedText(movie));
        }
    }


    // "aldrig" eller fx "26-10-2026 (for 0 dage siden)"
    private String lastWatchedText(Movie movie) {
        if (!movie.hasBeenWatched()) {
            return "aldrig";
        }
        long days = movie.getDaysSinceWatched(LocalDate.now());
        return movie.getLastWatched().format(DATE_FORMAT) + " (for " + days + " dage siden)";
    }

    // spørger igen, indtil brugeren skriver et helt tal
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et tal. Prøv igen.");
            }
        }
    }

    // som readInt, men Enter beholder den nuværende værdi
    private int readIntOrDefault(String prompt, int defaultValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return defaultValue;
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et tal. Prøv igen.");
            }
        }
    }

    public  boolean ReadYesNo(String prompt,Boolean Value){
        while (true){
            System.out.print(prompt);
            String input=scanner.nextLine().trim().toLowerCase();

            try {
                if (input.isEmpty() && Value!=null)return Value;
                if (input.equals("ja")||input.equals("j"))return true;
                if (input.equals("nej")||input.equals("n"))return false;
                throw new IllegalArgumentException("\"" + input + "\" er ikke ja eller nej.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " prøv igen.");
            }

        }
    }

}