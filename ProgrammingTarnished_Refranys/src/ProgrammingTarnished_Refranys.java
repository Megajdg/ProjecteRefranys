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
    private List<Refrany> refranysSeleccionats;
    
    public void iniciarJoc() {
        boolean jugarDeNou;
        do {
            jugarDeNou = jugarPartida();
        } while (jugarDeNou);
        System.out.println("GrÃ cies per jugar!");
    }

    private boolean jugarPartida() {
        encerts = 0;
        tempsTotal = 0;
        if (refranysSeleccionats == null) {
            refranysSeleccionats = seleccionarRefranys();
        }
        
        List<String> primeres = new ArrayList<>();
        List<String> segones = new ArrayList<>();
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

    private List<Refrany> seleccionarRefranys() {
        String[] primeresText = {
            "Qui no vulgui pols", "No diguis blat", "A la taula i al llit", "Tal farÃ s,", "Qui dia passa,",
            "A lâ€™estiu", "De porc i de senyor", "Hostes vingueren", "De mica en mica", "Al pot petit",
            "Si vols estar ben servit,", "Qui de jove no treballa,", "A la taula dâ€™en Bernat,",
            "Qui canta a la taula i xiula al llit", "Dâ€™on no nâ€™hi ha,"
        };
        String[] segonesText = {
            "que no vagi a l'era.", "fins que no el tinguis al sac i ben lligat.", "al primer crit.",
            "tal trobarÃ s.", "any empeny.", "tota cuca viu.", "se nâ€™ha de venir de mena.",
            "que de casa ens tragueren.", "sâ€™omple la pica.", "hi ha la bona confitura.",
            "fes-te tu mateix el llit.", "quan Ã©s vell dorm a la palla.", "qui no hi Ã©s, no hi Ã©s comptat.",
            "no tÃ© el seny gaire acomplit.", "no en raja."
        };
        List<Refrany> refranys = new ArrayList<>();
        for (int i = 0; i < NR_REFRANYS_TOTALS; i++) {
            refranys.add(new Refrany(i, primeresText[i], segonesText[i]));
        }
        Collections.shuffle(refranys);
        return refranys.subList(0, NR_REFRANYS);
    }

    private void mostrarRefranys(List<String> primeres, List<String> segones) {
        System.out.println("LLISTAT DE LES PARTS DELS REFRANYS SEPARATS I BARREGATS.");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%c - %-30s  | %d - %-30s %n",
                    (char) (65 + i), primeres.get(i), (i + 1), segones.get(i));
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void demanarJugades(List<String> primeres, List<String> segones, List<Refrany> refranys) {
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona la segona meitat pel refrany '%s' (1-%d): ", primeres.get(i), NR_REFRANYS);
            long tempsInici = System.currentTimeMillis();
            int resposta = scanner.nextInt() - 1;
            long tempsFinal = System.currentTimeMillis() - tempsInici;
            tempsTotal += tempsFinal / 1000.0;
            validarResposta(primeres.get(i), segones.get(resposta), refranys);
        }
    }

    private void validarResposta(String primera, String respostaUsuari, List<Refrany> refranys) {
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
        System.out.println("\nVols jugar una altra partida? (sÃ­/no)");
        String resposta = scanner.next().trim().toLowerCase();
        return resposta.equals("sÃ­") || resposta.equals("si");
    }

    public static void main(String[] args) {
        new ProgrammingTarnished_Refranys().iniciarJoc();
    }
}

