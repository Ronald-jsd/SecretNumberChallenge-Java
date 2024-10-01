/**
 * <b>Ronald Jaime</b>
 */

/*¡Ahora te toca a ti!
Practica los conceptos que se enseñaron durante esta clase con el
siguiente desafío:

Crea un programa que simule un juego de adivinanzas. El programa
debe generar un número aleatorio entre 0 y 100, y pedir al usuario
que intente adivinar ese número en un máximo de 5 intentos. En cada
intento, el programa debe informar si el número ingresado por el
usuario es mayor o menor que el número generado.

Consejos:
Para generar un número aleatorio en Java: new Random().nextInt(100);
Utiliza el Scanner para obtener los datos del usuario.
Utiliza una variable para contar los intentos.
Utiliza un bucle para controlar los intentos.
Utiliza la instrucción ‘break;’ para salir del bucle.*/

import java.util.Random;
import java.util.Scanner;

public class ChallengeSecret {
    static Scanner sc = new Scanner(System.in);
    static int numeroAleatorio = new Random().nextInt(101);
    static int nIntentos = 1;
    static int nIntentosMax = 5;

    public static void main(String[] args) {
        imprimir("""
                *************************************************
                *------******JUEGO DEL NÚMERO SECRETO*****------*
                *         ¡Adivine el número secreto!           *
                *************************************************""");
        evaluarNumeroSecreto();
    }

    public static void evaluarNumeroSecreto() {
        imprimir("(Nro. Oportunidades: " + nIntentosMax + ")\n" +
                "|===> Escriba el número: "
        );

        while (true) {
            try {
                int numeroUsario = sc.nextInt();
                while (numeroUsario != numeroAleatorio) {
                    if (nIntentos < nIntentosMax) {
                        evaluarSiNumeroEsMayor(numeroUsario);
                        imprimir("Vuelva a intentarlo: ");
                        numeroUsario = sc.nextInt();
                        nIntentos++;
                    } else {
                        imprimir("**-¡Perdiste! Se terminaron los intentos. :( -**");
                        break;
                    }
                }

                String mensajeGanoOPerdio =     (numeroUsario == numeroAleatorio)
                        ?   ("¡Felicidades!  El número secreto era: "
                        + numeroAleatorio + " y usted acertó :D \n"
                        + imprimirPregunta())
                        :   imprimirPregunta();

                imprimir(mensajeGanoOPerdio);
                String respuesta = sc.next();
                evaluarSiDeseaJugar(respuesta);
                break;
            }   catch (Exception e) {
                imprimir("Ingrese un valor valido");
                sc.next();
            }
        }
    }

    //Metodos adicionales
    public static void imprimir(String cad) {
        System.out.println(cad);
    }

    public static String imprimirPregunta() {
        return """
                ¿Desea volver a jugar?
                *Si
                *No
                """;
    }

    public static void evaluarSiNumeroEsMayor(int numeroUsario) {
        String mensaje = numeroUsario > numeroAleatorio
                ? "El número ingresado es 'mayor' que el número 'aleatorio'"
                : "El número ingresado es 'menor' que el número 'secreto'";
        imprimir(mensaje);
    }

    public static void evaluarSiDeseaJugar(String respuesta) {
        while (!respuesta.equalsIgnoreCase("si")  &&  !respuesta.equalsIgnoreCase("no")) {
            imprimir("Debe escoger: Si o No ");
            respuesta = sc.next();
        }
        if (respuesta.equalsIgnoreCase("si")) {
            nIntentos = 0;
            numeroAleatorio = new Random().nextInt(101);
            imprimir("--**Nuevo Juego**--");
            evaluarNumeroSecreto();
        } else {
            imprimir("¡Gracias por jugar! Bonito dia. :) ");
        }
    }
}
