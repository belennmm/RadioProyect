
import java.util.Scanner;


/**
 * Representa la vista del programa.
 */
public class Vista {
    
    Scanner scan = new Scanner(System.in);


    // menú del programa
    public void menu() {

        while (true) {
            System.out.println("----- Radio ----- \n");
        

            String option = scan.next();
            scan.nextLine(); 

            switch (option){
                case "1":
            
                    System.out.println(" \n");
                    
                    break;

                case "2":
            
                    System.out.println(" \n");
                    
                    break;

                case "3":
            
                    System.out.println(" \n");
                    
                    break;



                case "8": 
                    System.out.println("----");
                    System.exit(0);
                
                // si no existe la opción ingresada
                default:
                    System.out.println("La opción ingresada no es válida.\n");
            }
        }
    }
    
}

