import com.github.kwhat.jnativehook.NativeHookException;

import java.util.Scanner;
import static Controllers.viewController.*;
import static View.view.*;

public class main {
    public static void main(String[] args) throws NativeHookException, InterruptedException {
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to TrustyBot! \n 0: Cerrar \n 1: iniciar aplicacion \n 2:Ingresar/editar teclas ");

            switch (sc.nextLine()) {
                case "0":
                    System.exit(0);

                case "1":
                    System.out.println("1: secuencial mode (safe) \n 2: paralelism mode (unsafe) ");
                    String option = sc.nextLine();
                    if(option.equals("1")){
                        secuencialMode();
                        break;
                    }
                    else if(option == "2"){
                        //paralelism mode
                        break;
                    }
                    break;
                case "2":
                    editKey();
                    break;
            }
        }
    }
}
