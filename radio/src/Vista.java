
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
            System.out.println("1. On / Off");
            System.out.println("2. AM / FM");
            System.out.println("3. Next" );
            System.out.println("4. Guardar emisora");
            System.out.println("5. Seleccionar emisora");
            System.out.println("6. Salir");


            
        

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

                case "4":
            
                    System.out.println(" \n");
                    
                    break;

                case "5":
            
                    System.out.println(" \n");
                    
                    break;



                case "6": 
                    System.out.println("----");
                    System.exit(0);
                
                // si no existe la opción ingresada
                default:
                    System.out.println("La opción ingresada no es válida.\n");
            }
        }
    }
    
}


