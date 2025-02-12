import java.util.*;

class Refrany {
    private final int id;
    private final String primeraMeitat;
    private final String segonaMeitat;

    public Refrany(int id, String primera, String segona) {
        this.id = id;
        this.primeraMeitat = primera;
        this.segonaMeitat = segona;
    }

    public int getId() {
        return id;
    }

    public String getPrimeraMeitat() {
        return primeraMeitat;
    }

    public String getSegonaMeitat() {
        return segonaMeitat;
    }
}

public class ProgrammingTarnished_Refranys {
    private static final int NR_REFRANYS = 5;
    private static final int NR_REFRANYS_TOTALS = 15;
    private final Scanner scanner = new Scanner(System.in);
    private long tempsTotal;
    private int encerts;
    private ArrayList<Refrany> refranysSeleccionats;
    
    public void iniciarJoc() {
        boolean jugarDeNou;
        do {
            jugarDeNou = jugarPartida();
        } while (jugarDeNou);
        System.out.println("Gràcies per jugar!");
    }

    private boolean jugarPartida() {
        encerts = 0;
        tempsTotal = 0;
        if (refranysSeleccionats == null) {
            refranysSeleccionats = seleccionarRefranys();
        }
        
        ArrayList<String> primeres = new ArrayList<>();
        ArrayList<String> segones = new ArrayList<>();
        for (int i = 0; i < NR_REFRANYS; i++) {
            primeres.add(refranysSeleccionats.get(i).getPrimeraMeitat());
            segones.add(refranysSeleccionats.get(i).getSegonaMeitat());
        }
        Collections.shuffle(segones);

        mostrarRefranys(primeres, segones);
        demanarJugades(primeres, segones, refranysSeleccionats);
        mostrarResultats();
        return tornarAJugar();
    }

    private ArrayList<Refrany> seleccionarRefranys() {
        String[] primeresText = {
            "Qui no vulgui pols", "No diguis blat", "A la taula i al llit", "Tal faràs,", "Qui dia passa,",
            "A l'estiu", "De porc i de senyor", "Hostes vingueren", "De mica en mica", "Al pot petit",
            "Si vols estar ben servit,", "Qui de jove no treballa,", "A la taula d'en Bernat,",
            "Qui canta a la taula i xiula al llit", "D'on no n'hi ha,"
        };
        String[] segonesText = {
            "que no vagi a l'era.", "fins que no el tinguis al sac i ben lligat.", "al primer crit.",
            "tal trobaràs.", "any empeny.", "tota cuca viu.", "se n'ha de venir de mena.",
            "que de casa ens tragueren.", "s'omple la pica.", "hi ha la bona confitura.",
            "fes-te tu mateix el llit.", "quan és vell dorm a la palla.", "qui no hi és, no hi és comptat.",
            "no té el seny gaire acomplit.", "no en raja."
        };
        ArrayList<Refrany> refranys = new ArrayList<>();
        for (int i = 0; i < NR_REFRANYS_TOTALS; i++) {
            refranys.add(new Refrany(i, primeresText[i], segonesText[i]));
        }
        Collections.shuffle(refranys);
        
        ArrayList<Refrany> refranysPassador = new ArrayList<>(NR_REFRANYS);
        refranysPassador = refranys;
        return refranysPassador;
    }

    private void mostrarRefranys(ArrayList<String> primeres, ArrayList<String> segones) {
        System.out.printf("REFRANYS CATALANS\nRelaciona el inici d'aquests refranys catalans amb la seva part corresponent:\n-------------------------------------------------------------------------------------\n");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-36s  | %d - %-36s %n",
                    primeres.get(i), (i + 1), segones.get(i));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void demanarJugades(ArrayList<String> primeres, ArrayList<String> segones, ArrayList<Refrany> refranys) {
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona la segona meitat pel refrany '%s' (1 - %d): ", primeres.get(i), NR_REFRANYS);
            long tempsInici = System.currentTimeMillis();
            int resposta = scanner.nextInt() - 1;
            long tempsFinal = System.currentTimeMillis() - tempsInici;
            tempsTotal += tempsFinal / 1000.0;
            validarResposta(primeres.get(i), segones.get(resposta), refranys);
        }
    }

    private void validarResposta(String primera, String respostaUsuari, ArrayList<Refrany> refranys) {
        for (Refrany refrany : refranys) {
            if (refrany.getPrimeraMeitat().equals(primera) && refrany.getSegonaMeitat().equals(respostaUsuari)) {
                System.out.println("Correcte!");
                encerts++;
                return;
            }
        }
        System.out.println("Incorrecte!");
    }

    private void mostrarResultats() {
        int errors = NR_REFRANYS - encerts;
        System.out.printf("Encerts: %d%nErrors: %d%nTemps total: %.1f segons%n", encerts, errors, (double) tempsTotal);
    }

    private boolean tornarAJugar() {
        System.out.println("\nVols jugar una altra partida? (Sí / No)");
        String resposta = scanner.next().trim().toLowerCase();
        return resposta.equals("sí­") || resposta.equals("si");
    }

    public static void main(String[] args) {
        new ProgrammingTarnished_Refranys().iniciarJoc();
    }
}

