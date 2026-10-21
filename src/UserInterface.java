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

        int valg =scanner.nextInt();
        scanner.nextLine();
        switch (valg){
            case 1:{

                break;
            }
            case 2:{

                break;
            }
            case 3:{

                break;
            }
            case 4:{

                break;
            }
            case 5:{

                break;
            }


            case 0:{
                running=false;
                break;
            }
        }

    }
    }
}
