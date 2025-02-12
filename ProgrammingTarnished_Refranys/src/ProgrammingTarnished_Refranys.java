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
        boolean resultat;
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
        if (encerts != 5) {
            resultat = tornarAJugar();
        } else {
            resultat = false;
        }
        return resultat;
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
        String[] significatsText = {
            "Quan no es vulguin afrontar les conseqüències d’una acció o situació, el millor és evitar-la des del principi.",
            "No cal donar res per fet fins que no estigui totalment assegurat.",
            "Reflecteix la importància de ser puntual, de la disciplina i d’estar sempre preparat per respondre amb celeritat.",
            "Transmet una lliçó de responsabilitat i ètica, recordant-nos que les nostres accions, bones o dolentes, ens acabaran retornant en forma de resultats o situacions similars.",
            "El temps avança, fins i tot quan sembla que no estem fent res d'especial per accelerar-lo; les coses van canviant de forma natural a mesura que vivim el present.",
            "En condicions favorables o en èpoques de prosperitat, fins i tot aquelles persones que normalment viuen en condicions difícils poden beneficiar-se d'aquestes circumstàncies positives.",
            "Les persones, per molt que ho intentin, no poden escapar completament del seu origen o condicions inicials.",
            "La presència de certes persones o circumstàncies pot canviar la dinàmica habitual d’un lloc o una situació.",
            "Transmet una lliçó de paciència i persistència: a vegades cal temps i esforç continu per aconseguir grans resultats.",
            "No cal jutjar les coses només pel seu aspecte extern o la seva mida, ja que el que realment importa és el contingut.",
            "Transmet la importància de ser autònom i responsable de les pròpies accions i decisions, en lloc de deixar-ho tot en mans d'altres.",
            "Transmet una lliçó de responsabilitat i planificació per al futur: és important treballar i estalviar quan som joves per poder viure millor quan siguem grans.",
            "Per formar part d'alguna cosa, cal estar-hi present i involucrat. És una crida a la responsabilitat i a la participació activa.",
            "És una crítica a la falta de discerniment i a la falta d'harmonia entre el comportament i el context.",
            "Si no hi ha els mitjans, els recursos o les condicions necessàries per obtenir alguna cosa, simplement no es pot aconseguir. Les expectatives han de ser realistes segons les circumstàncies."
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

