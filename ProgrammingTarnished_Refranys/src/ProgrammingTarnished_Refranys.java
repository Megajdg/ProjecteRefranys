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
    final public static int NR_REFRANYS_TOTALS = 15;
    public static int encerts  = 0;
    public static Scanner scanner = new Scanner(System.in);//Abrimos scanner para los inputs del usuario
    
    /**
     * Introdueix informació en el vector d'objectes de pimeres meitats
     * @param primeres vector d'objectes de primeres meitats
     */
    public static void omplePrimeres(ArrayList<PrimeraMeitat> primeres){
        int i;
        for (i = 0; i < NR_REFRANYS_TOTALS; i++) {
            primeres.add(new PrimeraMeitat());
        }
        
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
        
        primeres.get(5).idPMeitat = 5;
        primeres.get(5).nrOrdre2aPart = 5;
        primeres.get(5).primeraMeitat = "A l’estiu";
        
        primeres.get(6).idPMeitat = 6;
        primeres.get(6).nrOrdre2aPart = 6;
        primeres.get(6).primeraMeitat = "De porc i de senyor";
        
        primeres.get(7).idPMeitat = 7;
        primeres.get(7).nrOrdre2aPart = 7;
        primeres.get(7).primeraMeitat = "Hostes vingueren";
        
        primeres.get(8).idPMeitat = 8;
        primeres.get(8).nrOrdre2aPart = 8;
        primeres.get(8).primeraMeitat = "De mica en mica";
        
        primeres.get(9).idPMeitat = 9;
        primeres.get(9).nrOrdre2aPart = 9;
        primeres.get(9).primeraMeitat = "Al pot petit";
        
        primeres.get(10).idPMeitat = 10;
        primeres.get(10).nrOrdre2aPart = 10;
        primeres.get(10).primeraMeitat = "Si vols estar ben servit,";
        
        primeres.get(11).idPMeitat = 11;
        primeres.get(11).nrOrdre2aPart = 11;
        primeres.get(11).primeraMeitat = "Qui de jove no treballa,";
        
        primeres.get(12).idPMeitat = 12;
        primeres.get(12).nrOrdre2aPart = 12;
        primeres.get(12).primeraMeitat = "A la taula d’en Bernat,";
        
        primeres.get(13).idPMeitat = 13;
        primeres.get(13).nrOrdre2aPart = 13;
        primeres.get(13).primeraMeitat = "Qui canta a la taula i xiula al llit";
        
        primeres.get(14).idPMeitat = 14;
        primeres.get(14).nrOrdre2aPart = 14;
        primeres.get(14).primeraMeitat = "D’on no n’hi ha,";
    }
    
    /**
     * Introdueix informació en el vector d'objectes de segones meitats
     * @param segones vector d'objetes de segones meitats
     */
    public static void ompleSegones(ArrayList<SegonaMeitat> segones){
        int i;
        for (i = 0; i < NR_REFRANYS_TOTALS; i++) {
            segones.add(new SegonaMeitat());
        }
        
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
        
        segones.get(5).idSMeitat = 5;
        segones.get(5).nrOrdreAleatori = 5;
        segones.get(5).segonaMeitat = "tota cuca viu.";
        
        segones.get(6).idSMeitat = 6;
        segones.get(6).nrOrdreAleatori = 6;
        segones.get(6).segonaMeitat = "se n’ha de venir de mena.";
        
        segones.get(7).idSMeitat = 7;
        segones.get(7).nrOrdreAleatori = 7;
        segones.get(7).segonaMeitat = "que de casa ens tragueren.";
        
        segones.get(8).idSMeitat = 8;
        segones.get(8).nrOrdreAleatori = 8;
        segones.get(8).segonaMeitat = "s’omple la pica.";
        
        segones.get(9).idSMeitat = 9;
        segones.get(9).nrOrdreAleatori = 9;
        segones.get(9).segonaMeitat = "hi ha la bona confitura.";
        
        segones.get(10).idSMeitat = 10;
        segones.get(10).nrOrdreAleatori = 10;
        segones.get(10).segonaMeitat = "fes-te tu mateix el llit.";
        
        segones.get(11).idSMeitat = 11;
        segones.get(11).nrOrdreAleatori = 11;
        segones.get(11).segonaMeitat = "quan és vell dorm a la palla.";
        
        segones.get(12).idSMeitat = 12;
        segones.get(12).nrOrdreAleatori = 12;
        segones.get(12).segonaMeitat = "qui no hi és, no hi és comptat.";
        
        segones.get(13).idSMeitat = 13;
        segones.get(13).nrOrdreAleatori = 13;
        segones.get(13).segonaMeitat = "no té el seny gaire acomplit.";
        
        segones.get(14).idSMeitat = 14;
        segones.get(14).nrOrdreAleatori = 14;
        segones.get(14).segonaMeitat = "no en raja.";
     
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
        
        Integer posicioRandom;
        int foo_segona;
        
        for (int i = 0; i < NR_REFRANYS; i++) {
            posicioRandom = rand.nextInt(15);
            
            while(posicioRandom == i){
                posicioRandom = rand.nextInt(15);
            }   
            
            // intercambiem els valors de la posicio actual i la generada aleatoriament
            foo_segona = segones.get(i).nrOrdreAleatori;
            segones.get(i).nrOrdreAleatori = segones.get(posicioRandom).nrOrdreAleatori;
            segones.get(posicioRandom).nrOrdreAleatori = foo_segona;
        }
    }

    /**
     * Asigna ordre aleatori a les primeres meitats per mostrar-les
     * a continuació a l'usuari; aquesta acció s'haurà de repetir en
     * cada execució del programa.
     * @param primeres vector d'objectes de segones meitats
     */
    public static void ordrePrimeres(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones){
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
        Integer posicioRandom;
        int foo_primera;
        for (int i = 0; i < NR_REFRANYS; i++) {
            posicioRandom = rand.nextInt(15);
            
            while (posicioRandom == i) {
                posicioRandom = rand.nextInt(15);
            }
                
            // intercambiem els valors de la posicio actual i la generada aleatoriament
                    
            foo_primera = primeres.get(i).nrOrdre2aPart;
            primeres.get(i).nrOrdre2aPart = primeres.get(posicioRandom).nrOrdre2aPart;
            primeres.get(posicioRandom).nrOrdre2aPart = foo_primera;
                
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
        System.out.println("LLISTAT DE LES PARTS DELS REFRANYS SEPARATS I BARREGATS.");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%c - %-30s  | %5d - %-30s \n", (char)(65 + i), (primeres.get(primeres.get(i).nrOrdre2aPart).primeraMeitat), (i+1), (segones.get(segones.get(i).nrOrdreAleatori).segonaMeitat));
        }
        System.out.println("-----------------------------------------------------------------------------------------");
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
            comprobaEncerts(primeres, segones, i, primeres.get(i).respostaUsuari);
            System.out.println(); // Espacio para separar las jugadas
        }
        
    }
    
    public static void sumaEncerts () {
        encerts++;
    }
    
    /**
     * Comproba si la resposta introduida per l'usuari és correcta
     * @param primeres
     * @param segones
     * @param aux
     * @param resposta
     */

    public static void comprobaEncerts (ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones, int aux, int resposta) {
        if (primeres.get(aux).nrOrdre2aPart == segones.get(resposta).nrOrdreAleatori) {
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
    
    
    
    // Funció per preguntar si volen jugar de nou
    public static boolean tornarAJugar() {
        System.out.println("\nVols jugar una altra partida? (sí/no)"); //Pregunta al usuari
        String resposta = scanner.next().trim().toLowerCase(); // Ignora tot nomes detecta si si o no
        return resposta.equals("sí") || resposta.equals("si");//Return amb la resposta
    }
    
    public static void main(String[] args) {
        boolean jugarDeNou;
        do{
            ArrayList<PrimeraMeitat> primeres = new ArrayList<>();
            ArrayList<SegonaMeitat> segones = new ArrayList<>();

            omplePrimeres(primeres);
            ompleSegones(segones);

            ordreSegones(segones);
            ordrePrimeres(primeres, segones);
            mostraMeitats(primeres, segones);
            demanaJugada(primeres, segones);
            mostraResultats(encerts);

            jugarDeNou = tornarAJugar();
        }while (jugarDeNou);
        System.out.println("Gràcies per jugar!");
    }    
}
