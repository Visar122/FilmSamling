import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner=new Scanner(System.in);
    Controller controller ;

    public  void StartProgram(){
        boolean running=true;
        while (running) {

        System.out.println("Velkommen til filmsamling");
        System.out.println("1. Opret en film");
        System.out.println("2. Vis alle film");
        System.out.println("4. Redigér en film");
        System.out.println("5. Slet en film");
        System.out.println("0. Afslut");
        System.out.print("Valg");

        String valg = scanner.nextLine().trim();
        switch (valg){
            case "1" -> CreateMovie();
            case "2" -> ShowAllMovies();

            case "0" -> {
                System.out.println("Farvel!");
                running = false;
            }

            default -> System.out.println(" Ugyldigt valg,prøv igen.");

        }

    }


    }

    private  void  CreateMovie(){
        System.out.print("Titel: ");
        String title=scanner.nextLine();
        System.out.println("Director: ");
        String director=scanner.nextLine();
        System.out.println("Årstal: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Er filmen i farver? (ja/nej): ");
        boolean inColor = scanner.nextLine().trim().equalsIgnoreCase("ja");
        System.out.print("Længde i minutter: ");
        int length = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Genre: ");
        String genre=scanner.nextLine();

        controller.AddMovie(title,director,year,inColor,length,genre);
        System.out.println("Filmen -" + title + "- er tilføjet");
    }

    private void ShowAllMovies(){
        ArrayList<Movie> movies=controller.getMovies();
        if(movies.isEmpty()){
            System.out.println("Der er ingen film");
            return;
        }
        for(Movie movie:movies){
            printMovie(movie);
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
    }
    private String colorText(Movie movie) {
        return movie.isInColor() ? "farve" : "sort-hvid";
    }
}
