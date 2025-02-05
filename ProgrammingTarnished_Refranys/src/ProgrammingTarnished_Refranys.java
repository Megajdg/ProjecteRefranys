import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

class PrimeraMeitat {
    int idPMeitat;  
    int respostaUsuari; 
    String primeraMeitat; 

    PrimeraMeitat(int id, String text) {
        this.idPMeitat = id;
        this.primeraMeitat = text;
    }
}

class SegonaMeitat {
    int idSMeitat; 
    String segonaMeitat; 

    SegonaMeitat(int id, String text) {
        this.idSMeitat = id;
        this.segonaMeitat = text;
    }
}

// Classe per mantenir les parelles correctes abans de separar-les
class Parell {
    PrimeraMeitat primera;
    SegonaMeitat segona;

    Parell(PrimeraMeitat primera, SegonaMeitat segona) {
        this.primera = primera;
        this.segona = segona;
    }
}

public class ProgrammingTarnished_Refranys {
    final public static int NR_REFRANYS = 5;
    final public static int NR_REFRANYS_TOTALS = 15;
    public static int encerts = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean jugarDeNou;
        do {
            encerts = 0;

            ArrayList<Parell> parells = new ArrayList<>();
            String[] primeresText = {
                "Qui no vulgui pols", "No diguis blat", "A la taula i al llit", "Tal faràs,", "Qui dia passa,", 
                "A l’estiu", "De porc i de senyor", "Hostes vingueren", "De mica en mica", "Al pot petit", 
                "Si vols estar ben servit,", "Qui de jove no treballa,", "A la taula d’en Bernat,", 
                "Qui canta a la taula i xiula al llit", "D’on no n’hi ha,"
            };
            String[] segonesText = {
                "que no vagi a l'era.", "fins que no el tinguis al sac i ben lligat.", "al primer crit.", 
                "tal trobaràs.", "any empeny.", "tota cuca viu.", "se n’ha de venir de mena.", 
                "que de casa ens tragueren.", "s’omple la pica.", "hi ha la bona confitura.", 
                "fes-te tu mateix el llit.", "quan és vell dorm a la palla.", "qui no hi és, no hi és comptat.", 
                "no té el seny gaire acomplit.", "no en raja."
            };

            // Creem les parelles correctes
            for (int i = 0; i < NR_REFRANYS_TOTALS; i++) {
                parells.add(new Parell(new PrimeraMeitat(i, primeresText[i]), new SegonaMeitat(i, segonesText[i])));
            }

            // Barregem les parelles
            Collections.shuffle(parells);

            // Seleccionem només NR_REFRANYS parelles
            ArrayList<PrimeraMeitat> primeres = new ArrayList<>();
            ArrayList<SegonaMeitat> segones = new ArrayList<>();
            for (int i = 0; i < NR_REFRANYS; i++) {
                primeres.add(parells.get(i).primera);
                segones.add(parells.get(i).segona);
            }

            // Tornem a barrejar només les segones per desordenar-les
            Collections.shuffle(segones);

            mostraMeitats(primeres, segones);
            demanaJugada(primeres, segones);
            mostraResultats(encerts);
            jugarDeNou = tornarAJugar();
        } while (jugarDeNou);
        System.out.println("Gràcies per jugar!");
    }

    public static void mostraMeitats(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones) {
        System.out.println("LLISTAT DE LES PARTS DELS REFRANYS SEPARATS I BARREGATS.");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%c - %-30s  | %d - %-30s \n", 
                (char) (65 + i), primeres.get(i).primeraMeitat, 
                (i + 1), segones.get(i).segonaMeitat);
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static void demanaJugada(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones) {
        for (int i = 0; i < NR_REFRANYS; i++) {
            int resposta;
            do {
                System.out.printf("Selecciona la segona meitat pel refrany '%s' (1-%d): ", primeres.get(i).primeraMeitat, NR_REFRANYS);
                resposta = scanner.nextInt();
            } while (resposta < 1 || resposta > NR_REFRANYS);

            primeres.get(i).respostaUsuari = resposta - 1;
            comprobaEncerts(primeres, segones, i, resposta - 1);
            System.out.println();
        }
    }

    public static void comprobaEncerts(ArrayList<PrimeraMeitat> primeres, ArrayList<SegonaMeitat> segones, int index, int resposta) {
        int idPrimera = primeres.get(index).idPMeitat;
        int idSegona = segones.get(resposta).idSMeitat;

        if (idPrimera == idSegona) {
            System.out.println("Correcte!");
            encerts++;
        } else {
            System.out.println("Incorrecte!");
        }
        System.out.println("Comparant " + idPrimera + " amb " + idSegona);
    }

    public static void mostraResultats(int numEncerts) {
        int numErrors = NR_REFRANYS - numEncerts;
        System.out.printf("Encerts: %d\nErrors: %d\n", numEncerts, numErrors);
    }

    public static boolean tornarAJugar() {
        System.out.println("\nVols jugar una altra partida? (sí/no)");
        String resposta = scanner.next().trim().toLowerCase();
        return resposta.equals("sí") || resposta.equals("si");
    }
}
