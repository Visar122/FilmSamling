import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner=new Scanner(System.in);
    private Controller controller = new Controller() ;

    public  void StartProgram(){
        boolean running=true;
        while (running) {

        System.out.println("Velkommen til filmsamling");
        System.out.println("1. Opret en film");
        System.out.println("2. Vis alle film");
        System.out.println("3. Søg film");
        System.out.println("4. Redigér en film");
        System.out.println("5. Slet en film");
        System.out.println("0. Afslut");
        System.out.print("Valg : ");

        String valg = scanner.nextLine().trim();
        switch (valg){
            case "1" -> CreateMovie();
            case "2" -> ShowAllMovies();
            case "3" -> SearchMovies();
            case "4" -> EditMovie();
            case "5" -> DeleteMovie();


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
        System.out.print("Director: ");
        String director=scanner.nextLine();
        System.out.print("Årstal: ");
        int year = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Er filmen i farver? (ja/nej): ");
        boolean inColor = scanner.nextLine().trim().equalsIgnoreCase("ja");
        System.out.print("Længde i minutter: ");
        int length = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Genre: ");
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
    private void SearchMovies(){
        System.out.println("Søg efter titel: ");
        String searchText=scanner.nextLine();
        ArrayList<Movie> result=controller.searchMovies(searchText);

        if(result.isEmpty()){
            System.out.println("Ingen gilm fundet.");
            return;
        }
        for (Movie movie:result){
            printMovie(movie);
        }

    }

     private Movie FindtheMovie(){
        System.out.println("Søg titel");
        ArrayList<Movie> result=controller.searchMovies(scanner.nextLine());
        if(result.isEmpty()){
            System.out.println("Ingeng film fundet ");
            return null;
        }

        for(int i=0;i<result.size();i++){
            System.out.println((i+1) + ". " + result.get(i).GetTitle()); ; // resultt.get(index) tager filmen på det sted: tager så  result.get(0) // The Godfather          result.get(1)   // Godzilla
                                                                             // i+1 fordi fx the Godfather index det 0= så brugeren  ser godfather 1 i stedet for 0
        }

        System.out.print("Vælg nummer: ");
        int number=Integer.parseInt(scanner.nextLine().trim());
         if (number < 1 || number > result.size()) {
             System.out.println("Ugyldigt nummer.");
             return null;
         }
        return result.get(number-1);

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

       System.out.print("Årstal (" + movie.getYearCreated() + "): ");
       String yearText = scanner.nextLine().trim();
       int year = yearText.isEmpty() ? movie.getYearCreated() : Integer.parseInt(yearText);

       System.out.print("I farver? ja/nej (" + colorText(movie) + "): ");
       String colorInput = scanner.nextLine().trim();
       boolean inColor = colorInput.isEmpty() ? movie.isInColor() : colorInput.equalsIgnoreCase("ja");

       System.out.print("Længde i minutter (" + movie.getLengthInMinutes() + "): ");
       String lengthText = scanner.nextLine().trim();
       int length = lengthText.isEmpty() ? movie.getLengthInMinutes() : Integer.parseInt(lengthText);

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

        System.out.println("Er du sikker på att du vil slette (" + movie.GetTitle() + " ) (ja/nej) :  " );
        String answer=scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("ja")) {
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
    }
    private String colorText(Movie movie) {
        return movie.isInColor() ? "farve" : "sort-hvid";
    }
}
