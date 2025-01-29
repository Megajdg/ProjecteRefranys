import java.util.Scanner;

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
    
    /**
     * Introdueix informació en el vector d'objectes de pimeres meitats
     * @param primeres vector d'objectes de primeres meitats
     */
    public static void omplePrimeres(PrimeraMeitat[] primeres){
        
        for(int i = 0; i < NR_REFRANYS; i++){
            primeres[i] = new PrimeraMeitat();
        }
        
        primeres[0].idPMeitat = 0;
        primeres[0].nrOrdre2aPart = 0;
        primeres[0].primeraMeitat = "Qui no vulgui pols";        
        
        primeres[1].idPMeitat = 1;
        primeres[1].nrOrdre2aPart = 1;
        primeres[1].primeraMeitat = "No diguis blat";
        
        primeres[2].idPMeitat = 2;
        primeres[2].nrOrdre2aPart = 2;
        primeres[2].primeraMeitat = "A la taula i al llit";
        
        primeres[3].idPMeitat = 3;
        primeres[3].nrOrdre2aPart = 3;
        primeres[3].primeraMeitat = "Tal faràs,";
        
        primeres[4].idPMeitat = 4;
        primeres[4].nrOrdre2aPart = 4;
        primeres[4].primeraMeitat = "Qui dia passa,";
    }
    
    /**
     * Introdueix informació en el vector d'objectes de segones meitats
     * @param segones vector d'objetes de segones meitats
     */
    public static void ompleSegones(SegonaMeitat[] segones){
        
        for(int i = 0; i < NR_REFRANYS; i++){
            segones[i] = new SegonaMeitat();
        }
        
        segones[0].idSMeitat = 0;
        segones[0].nrOrdreAleatori = 0;
        segones[0].segonaMeitat = "que no vagi a l'era";        
        
        segones[1].idSMeitat = 1;
        segones[1].nrOrdreAleatori = 1;
        segones[1].segonaMeitat = "fins que no el tinguis al sac i ben lligat.";
        
        segones[2].idSMeitat = 2;
        segones[2].nrOrdreAleatori = 2;
        segones[2].segonaMeitat = "al primer crit.";
        
        segones[3].idSMeitat = 3;
        segones[3].nrOrdreAleatori = 3;
        segones[3].segonaMeitat = "tal trobaràs.";
        
        segones[4].idSMeitat = 4;
        segones[4].nrOrdreAleatori = 4;
        segones[4].segonaMeitat = "any empeny.";
    }
    
    
    /**
     * Asigna ordre aleatori a les segones meitats per mostrar-les
     * a continuació a l'usuari; aquesta acció s'haurà de repetir en
     * cada execució del programa.
     * @param segones vector d'objectes de segones meitats
     */
    public static void ordreSegones(SegonaMeitat[] segones){
       //Codi mètode
    }

    /**
     * Asigna ordre aleatori a les primeres meitats per mostrar-les
     * a continuació a l'usuari; aquesta acció s'haurà de repetir en
     * cada execució del programa.
     * @param segones vector d'objectes de segones meitats
     */
    public static void ordrePrimeres(PrimeraMeitat[] segones){
       //Codi mètode
    }
    
    /**
     * Mostra els continguts les primeres i segones meitats en ordre aleatori
     * en un format apropiat per fer triar l'usuari
     * @param primeres 
     * @param segones 
     */
    public static void mostraMeitats(PrimeraMeitat[] primeres, SegonaMeitat[] segones){
        //Codi mètode
    }
    
    /**
     * Demana a l'usuari que indiqui les segones meitats (segons la 
     * numeració que apareix en pantalla), que corresponen a les 
     * primeres meitats (que es mostraran també en ordre aleatori)
     * @param primeres 
     */
    public static void demanaJugada(PrimeraMeitat[] primeres, SegonaMeitat[] segones){
        //Codi mètode
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
            sumaEncerts ();
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
        
        PrimeraMeitat[] primeres = new PrimeraMeitat[NR_REFRANYS];
        SegonaMeitat[] segones = new SegonaMeitat[NR_REFRANYS];
        
        omplePrimeres(primeres);
        ompleSegones(segones);
        
        ordreSegones(segones);
        mostraMeitats(primeres, segones);
        demanaJugada(primeres, segones);
        
    }    
}
