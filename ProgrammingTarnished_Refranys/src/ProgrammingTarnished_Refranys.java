import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class PrimeraMeitat{
    //Identificador de la primera meitat
    int idPMeitat;
    //Identificador de la segona meitat que li correspon
    int nrOrdre2aPart;
    //Resposta que dona l'usuari per emparellar meitats en cada execució
    int respostaUsuari;
    //Text de la primera meitat
    String primeraMeitat;
}

class SegonaMeitat{
    //Identificador de la segona meitat
    int idSMeitat;
    //Nr d'ordre aleatori que s'assigna en cada execució
    int nrOrdreAleatori;
    //Text de la segona meitat
    String segonaMeitat;
}
/**
 *
 * @author S. González
 */
public class ProgrammingTarnished_Refranys {
    final public static int NR_REFRANYS = 5;   
    public static Scanner scanner = new Scanner(System.in);//Abrimos scanner para los inputs del usuario
    
    /**
     * Introdueix informació en el vector d'objectes de pimeres meitats
     * @param primeres vector d'objectes de primeres meitats
     */
    public static void omplePrimeres(ArrayList<PrimeraMeitat> primeres){
        
        primeres.get(0).idPMeitat = 0;
        primeres.get(0).nrOrdre2aPart = 0;
        primeres.get(0).primeraMeitat = "Qui no vulgui pols";
        
        primeres.get(1).idPMeitat = 1;
        primeres.get(1).nrOrdre2aPart = 1;
        primeres.get(1).primeraMeitat = "No diguis blat";
        
        primeres.get(2).idPMeitat = 2;
        primeres.get(2).nrOrdre2aPart = 2;
        primeres.get(2).primeraMeitat = "A la taula i al llit";
        
        primeres.get(3).idPMeitat = 3;
        primeres.get(3).nrOrdre2aPart = 3;
        primeres.get(3).primeraMeitat = "Tal faràs,";
        
        primeres.get(4).idPMeitat = 4;
        primeres.get(4).nrOrdre2aPart = 4;
        primeres.get(4).primeraMeitat = "Qui dia passa,";
    }
    
    /**
     * Introdueix informació en el vector d'objectes de segones meitats
     * @param segones vector d'objetes de segones meitats
     */
    public static void ompleSegones(ArrayList<SegonaMeitat> segones){
        
        
        segones.get(0).idSMeitat = 0;
        segones.get(0).nrOrdreAleatori = 0;
        segones.get(0).segonaMeitat = "que no vagi a l'era";
        
        segones.get(1).idSMeitat = 1;
        segones.get(1).nrOrdreAleatori = 1;
        segones.get(1).segonaMeitat = "fins que no el tinguis al sac i ben lligat.";
        
        segones.get(2).idSMeitat = 2;
        segones.get(2).nrOrdreAleatori = 2;
        segones.get(2).segonaMeitat = "al primer crit.";
        
        segones.get(3).idSMeitat = 3;
        segones.get(3).nrOrdreAleatori = 3;
        segones.get(3).segonaMeitat = "tal trobaràs.";
        
        segones.get(4).idSMeitat = 4;
        segones.get(4).nrOrdreAleatori = 4;
        segones.get(4).segonaMeitat = "any empeny.";
     
    }
    
    
    /**
     * Asigna ordre aleatori a les segones meitats per mostrar-les
     * a continuació a l'usuari; aquesta acció s'haurà de repetir en
     * cada execució del programa.
     * @param segones vector d'objectes de segones meitats
     */
    public static void ordreSegones(ArrayList<SegonaMeitat> segones){
        //Codi mètode
        Random rand = new Random();
        /* 
        En aquesta seccio del codi generem un arraylist per conservar les posicions
        aleatories i aixi prevenir que es generin posicions ja cambiades.
        Després dins del for ens assegurem que no hi hagin 4 valors per a que no es quedi
        generant un nombre aleatori.
        Després intercanviem els valors entre sí.
        */
        ArrayList<Integer> posicionsRepetides = new ArrayList<>(); 
        Integer posicioRandom;
        int foo_segona;
        for (int i = 0; i < segones.size(); i++) {
            posicioRandom = rand.nextInt(5);
            if (posicionsRepetides.size() != 4) {
                if ((posicionsRepetides.contains(posicioRandom) == false)) {
                    // intercambiem els valors de la posicio actual i la generada aleatoriament
                    foo_segona = segones.get(i).idSMeitat;
                    segones.get(i).idSMeitat = segones.get(posicioRandom).idSMeitat;
                    segones.get(posicioRandom).idSMeitat = foo_segona;
                    posicionsRepetides.add(posicioRandom);
                    posicionsRepetides.add(i);
                } else {
                    i--;
                }
            }
        }
    }

    /**
     * Asigna ordre aleatori a les primeres meitats per mostrar-les
     * a continuació a l'usuari; aquesta acció s'haurà de repetir en
     * cada execució del programa.
     * @param segones vector d'objectes de segones meitats
     */
    public static void ordrePrimeres(ArrayList<PrimeraMeitat> primeres){
       //Codi mètode
       //Codi mètode
        Random rand = new Random();
        /* 
        En aquesta seccio del codi generem un arraylist per conservar les posicions
        aleatories i aixi prevenir que es generin posicions ja cambiades.
        Després dins del for ens assegurem que no hi hagin 4 valors per a que no es quedi
        generant un nombre aleatori.
        Després intercanviem els valors entre sí.
        */
        ArrayList<Integer> posicionsRepetides = new ArrayList<>(); 
        Integer posicioRandom;
        int foo_primera;
        for (int i = 0; i < primeres.size(); i++) {
            posicioRandom = rand.nextInt(5);
            if (posicionsRepetides.size() != 4) {
                if ((posicionsRepetides.contains(posicioRandom) == false)) {
                    // intercambiem els valors de la posicio actual i la generada aleatoriament
                    foo_primera = primeres.get(i).idPMeitat;
                    primeres.get(i).idPMeitat = primeres.get(posicioRandom).idPMeitat;
                    primeres.get(posicioRandom).idPMeitat = foo_primera;
                    posicionsRepetides.add(posicioRandom);
                    posicionsRepetides.add(i);
                } else {
                    i--;
                }
            }
        }
    }
    
    /**
     * Mostra els continguts les primeres i segones meitats en ordre aleatori
     * en un format apropiat per fer triar l'usuari
     * @param primeres 
     * @param segones 
     */
    public static void mostraMeitats(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones){
        //Codi mètode
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.println("LLISTAT DE LES PARTS DELS REFRANYS SEPARATS I BARREGATS.");
            System.out.printf("%c - %s | %d - %s \n", (char)(65 + i), (primeres.get(i).primeraMeitat), (i+1), (segones.get(i).segonaMeitat));
            System.out.println("--------------------------------------------------------");
        }
    }
    
    /**
     * Demana a l'usuari que indiqui les segones meitats (segons la 
     * numeració que apareix en pantalla), que corresponen a les 
     * primeres meitats (que es mostraran també en ordre aleatori)
     * @param primeres 
     * @param segones
     */
    public static void demanaJugada(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones){

        for (int i = 0; i < NR_REFRANYS; i++) { //Bucle para ir introduciendo cada una de las respuesta
  
            int respuesta = -1; //Esta variable es para guardar la respuesta del usuario en cada bucle
            while (respuesta < 1 || respuesta > NR_REFRANYS) { 
                respuesta = scanner.nextInt();
            } //Este while es para asegurarse que el usuario da una respuesta dentro del rango

            // Guardamos la respuesta del usuario
            primeres.get(i).respostaUsuari = respuesta - 1; // Restamos 1 porque los índices en el ArrayList empiezan desde 0
            System.out.println(); // Espacio para separar las jugadas
        }
    }
    
    /**
     * Comproba si la resposta introduida per l'usuari és correcta
     * @param primeres
     * @param segones
     * @param aux
     * @param resposta
     */

    public static void comprobaEncerts (ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones, int aux, int resposta) {
        if (primeres.get(aux).nrOrdre2aPart == segones.get(resposta).idSMeitat) {
            System.out.printf("Correcte!");
            sumaEncerts();
        }
        else System.out.printf("Incorrecte");
    }
    
    /**
     * Mostra el nombre d'encerts i d'errors en la partida
     * @param numEncerts
     */
    public static void mostraResultats(int numEncerts){

        //Codi mètode
        String nomJugador = "1";
        int numErrors = NR_REFRANYS - numEncerts;
        System.out.printf("Jugador %s:\nEncerts: %d\nErrors: %d",  nomJugador, numEncerts, numErrors);
    }
    
    public static void main(String[] args) {
        
        ArrayList<PrimeraMeitat> primeres = new ArrayList<>();
        ArrayList<SegonaMeitat> segones = new ArrayList<>();
        
        omplePrimeres(primeres);
        ompleSegones(segones);
        
        ordreSegones(segones);
        mostraMeitats(primeres, segones);
        demanaJugada(primeres, segones);
        
    }    
}
