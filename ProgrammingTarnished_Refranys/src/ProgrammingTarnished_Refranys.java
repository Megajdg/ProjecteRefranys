import java.util.*;

class Refrany {
    private final int id;
    private final String primeraMeitat;
    private final String segonaMeitat;
    private final String significat;

    public Refrany(int id, String primera, String segona, String significat) {
        this.id = id;
        this.primeraMeitat = primera;
        this.segonaMeitat = segona;
        this.significat = significat;
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
    
    public String getSignificat() {
        return significat;
    }
}

class Jugador {
    private int idJugador;
    private int tempsPuntuacio;
    private int encertsPuntuacio;
    
    public Jugador(int idJugador, int tempsPuntuacio, int encertsPuntuacio) {
        this.idJugador = idJugador;
        this.tempsPuntuacio = tempsPuntuacio;
        this.encertsPuntuacio = encertsPuntuacio;
    }
    
    public int getId() {
        return idJugador;
    }
}


public class ProgrammingTarnished_Refranys {
    private static final int NR_REFRANYS = 5;
    private static final int NR_JUGADORS = 2;
    private static final int NR_REFRANYS_TOTALS = 15;
    private final Scanner scanner = new Scanner(System.in);
    private double tempsTotal;
    private int encerts;
    private int nPartides;
    private ArrayList<Refrany> refranysSeleccionats;
    
    public void iniciarJoc() {
        boolean jugarDeNou;
        nPartides = 0;
        if (jugarIndividual()) {
            
            System.out.println("--------------------------------");
            System.out.println("        MODE INDIVIDUAL");
            System.out.println("--------------------------------");
            Jugador j1 = new Jugador(1, 0,0);
            
            do {
                 jugarDeNou = jugarPartida(j1);
             } while (jugarDeNou);
             if (encerts != 5) {
                 mostrarSolucio();
             }
             if (passarASeguentFase()) {
                 do {
                     jugarDeNou = segonaFase();
                 } while (jugarDeNou);
                 if (encerts != 5) {
                     mostrarSolucioSig();
                 }
             } 
        } else {
            System.out.println("--------------------------------");
            System.out.println("        MODE COOPERATIU");
            System.out.println("--------------------------------");
            ArrayList<Jugador> jugadors = new ArrayList<>();
            for (int i = 0; i < NR_JUGADORS; i++) {
                jugadors.add(new Jugador((i+1), 0, 0));
            }
            
            for (int i = 0; i < NR_JUGADORS; i++) {
                jugarPartida(jugadors.get(0));
            }
        }
        
        System.out.println("Gràcies per jugar!");
    }
    
    private boolean jugarIndividual() {
        int resposta;
        
        System.out.print("\nVols jugar de manera individual o amb parelles? (1/2): ");
        resposta = 0;
        do {
            resposta = scanner.nextInt();
            if (resposta!=1 && resposta!=1 && resposta!=2) {
                System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
            }
        } while (resposta!=1 && resposta!=1 && resposta!=2);
        return resposta == 1;
    }
    
    private boolean passarASeguentFase() {
        String resposta;
        
        System.out.println("\nVols passar a la seguent fase? (Sí/No)");
        resposta = "";
        do {
            resposta = scanner.next().trim().toLowerCase();
            if (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no")) {
                System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
            }
        } while (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no"));
        return resposta.equals("sí­") || resposta.equals("si");
    }
    
    private void mostrarSolucio() {
        System.out.printf("\nREFRANYS SOLUCIONATS\n-------------------------------------------------------------------------------------\n");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%s %n",
                    refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
    
    private void mostrarSolucioSig() {
        System.out.printf("\nREFRANYS I SIGNIFICATS SOLUCIONATS\n-------------------------------------------------------------------------------------\n");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%s: %s %n",
                    refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat(), refranysSeleccionats.get(i).getSignificat());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
    
    private boolean segonaFase () {
        encerts = 0;
        boolean resultat;
        
        ArrayList<String> significats = new ArrayList<>(NR_REFRANYS);
        
        for (int i = 0; i < NR_REFRANYS ; i++) {
            significats.add(refranysSeleccionats.get(i).getSignificat());
        }
        
        Collections.shuffle(significats);
        
        System.out.printf("\nLLISTAT DELS SIGNIFICATS DELS REFRANYS BARREGATS.\nRelaciona el refrany amb el significat corresponent:\n-------------------------------------------------------------------------------------\n");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-70s | %d - %s %n",
                    refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat(),(i+1), significats.get(i));
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        demanarJugadesSig(significats);
        
        if (encerts != 5) {
            resultat = tornarAJugar();
        } else {
            resultat = false;
        }
        
        return resultat;
    }
    
    private void demanarJugadesSig(ArrayList<String> significats) {
        double tempsInici;
        int resposta;
        double tempsFinal;
        
        resposta = 0;
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona el significat del refrany -> (%s): ", refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat());
            tempsInici = System.currentTimeMillis();
            do {
                boolean error;
                do {
                    try {
                        error = false;
                        resposta = scanner.nextInt() - 1;
                    } catch (InputMismatchException ex) {
                        System.out.println("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
                        scanner.next();
                        error = true;
                    }
                } while (error);
                
                if (resposta < 0 || resposta > NR_REFRANYS - 1) {
                    System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
                }
            } while (resposta < 0 || resposta > NR_REFRANYS - 1);
            tempsFinal = System.currentTimeMillis() - tempsInici;
            tempsTotal += tempsFinal / 1000.0;
            validarRespostaSig(significats.get(resposta), refranysSeleccionats.get(i).getSignificat());
        }
        
    }
    
    private void validarRespostaSig(String significat, String refrany_significat) {
        if (refrany_significat.equals(significat)) {
            System.out.println("Correcte!");
            encerts++;
        } else {
            System.out.println("Incorrecte!");
        }
    }
    
    private boolean jugarPartida(Jugador jugador) {
        boolean resultat;
        encerts = 0;
        tempsTotal = 0;
        
        if (refranysSeleccionats == null) {
            refranysSeleccionats = seleccionarRefranys();
        }
        
        if (nPartides == 0) {
            System.out.println("---------------");
            System.out.printf("   JUGADOR: (%) \n");
            System.out.println("---------------");
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
        if (encerts != 5 && nPartides == 0) {
            resultat = tornarAJugar();
        } else {
            resultat = false;
        }
        nPartides++;
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
            "Quan no es vulguin afrontar les conseqüències d?una acció o situació, el millor és evitar-la des del principi.",
            "No cal donar res per fet fins que no estigui totalment assegurat.",
            "Reflecteix la importància de ser puntual, de la disciplina i d?estar sempre preparat per respondre amb celeritat.",
            "Transmet una lliçó de responsabilitat i ètica, recordant-nos que les nostres accions, bones o dolentes, ens acabaran retornant en forma de resultats o situacions similars.",
            "El temps avança, fins i tot quan sembla que no estem fent res d'especial per accelerar-lo; les coses van canviant de forma natural a mesura que vivim el present.",
            "En condicions favorables o en èpoques de prosperitat, fins i tot aquelles persones que normalment viuen en condicions difícils poden beneficiar-se d'aquestes circumstàncies positives.",
            "Les persones, per molt que ho intentin, no poden escapar completament del seu origen o condicions inicials.",
            "La presència de certes persones o circumstàncies pot canviar la dinàmica habitual d?un lloc o una situació.",
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
            refranys.add(new Refrany(i, primeresText[i], segonesText[i], significatsText[i]));
        }
        Collections.shuffle(refranys);
        
        ArrayList<Refrany> refranysPassador = new ArrayList<>(NR_REFRANYS);
        refranysPassador = refranys;
        return refranysPassador;
    }

    private void mostrarRefranys(ArrayList<String> primeres, ArrayList<String> segones) {
        System.out.printf("\nREFRANYS CATALANS\nRelaciona l'inici d'aquests refranys catalans amb la seva part corresponent:\n-------------------------------------------------------------------------------------\n");
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-36s  | %d - %-36s %n",
                    primeres.get(i), (i + 1), segones.get(i));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void demanarJugades(ArrayList<String> primeres, ArrayList<String> segones, ArrayList<Refrany> refranys) {
        double tempsInici;
        int resposta;
        double tempsFinal;
        
        resposta = 0;
        for (int i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona la segona meitat pel refrany '%s' (1 - %d): ", primeres.get(i), NR_REFRANYS);
            tempsInici = System.currentTimeMillis();
            do {
                boolean error;
                do {
                    try {
                        error = false;
                        resposta = scanner.nextInt() - 1;
                    } catch (InputMismatchException ex) {
                        System.out.println("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
                        scanner.next();
                        error = true;
                    }
                } while (error);
                
                if (resposta < 0 || resposta > NR_REFRANYS - 1) {
                    System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
                }
            } while (resposta < 0 || resposta > NR_REFRANYS - 1);
            tempsFinal = System.currentTimeMillis() - tempsInici;
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
        int errors;
        errors = NR_REFRANYS - encerts;
        System.out.printf("\nEncerts: %d%nErrors: %d%nTemps total: %.2f segons%n", encerts, errors, tempsTotal);
    }

    private boolean tornarAJugar() {
        String resposta;
        
        System.out.println("\nVols jugar una altra partida? (Sí / No)");
        resposta = "";
        do {
            resposta = scanner.next().trim().toLowerCase();
            if (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no")) {
                System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
            }
        } while (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no"));
        return resposta.equals("sí­") || resposta.equals("si");
    }

    public static void main(String[] args) {
        new ProgrammingTarnished_Refranys().iniciarJoc();
    }
}