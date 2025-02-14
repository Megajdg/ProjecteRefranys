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
    private double tempsPuntuacio;
    private int encertsPuntuacio;
    
    public Jugador(int idJugador, double tempsPuntuacio, int encertsPuntuacio) {
        this.idJugador = idJugador;
        this.tempsPuntuacio = tempsPuntuacio;
        this.encertsPuntuacio = encertsPuntuacio;
    }
    
    public int getId() {
        return idJugador;
    }
    
    public void setEncerts(int encerts) {
        this.encertsPuntuacio += encerts;
    }
    
    public void setTemps(double temps) {
        this.tempsPuntuacio += temps;
    }
    
    public int getEncerts() {
        return encertsPuntuacio;
    }
    
    public double getTemps() {
        return tempsPuntuacio;
    }
}


public class ProgrammingTarnished_Refranys {
    private static final int NR_REFRANYS = 5;
    private static final int NR_JUGADORS = 2;
    private static final int NR_REFRANYS_TOTALS = 15;
    private final Scanner scanner = new Scanner(System.in);
    private double tempsTotal;
    private int encerts;
    private boolean mode2Jugadors;
    private int jugadorActual = 0;
    private int nPartides;
    private ArrayList<Refrany> refranysSeleccionats;
    
    private boolean jugarIndividual() {
        int resposta;
        
        System.out.printf("REFRANYS CATALANS\n\nModes de Joc:\n1.Un jugador\n2.Dos jugadors\nIntrodueixi el mode que vol jugar (1 / 2): ");
        resposta = 0;
        do {
            boolean error;
            do {
                try {
                    error = false;
                    resposta = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("La resposta introdu�da no �s v�lida. Si us plau, torni a provar: ");
                    scanner.next();
                    error = true;
                }
            } while (error);
                
            if (resposta != 1 && resposta != 2) {
                System.out.printf("La resposta introdu�da no �s v�lida. Si us plau, torni a provar: ");
            }
        } while (resposta != 1 && resposta != 2);
        return resposta == 1;
    }
    
    public void iniciarJoc() {
        boolean jugarDeNou;
        int i;
        nPartides = 0;
        ArrayList<Jugador> jugadors = new ArrayList<>();
        if (jugarIndividual()) {
            mode2Jugadors = false;
            System.out.println("\n--------------------------------");
            System.out.println("        MODE UN JUGADOR");
            System.out.println("--------------------------------");
            jugadors.add(new Jugador(1, 0, 0));
            jugadorActual = 1;
            do {
                jugarDeNou = jugarPartida(jugadors);
            } while (jugarDeNou);
            if (encerts != 5) {
                mostrarSolucio();
            }
            nPartides = 0;
            if (passarASeguentFase()) {
                do {
                    jugarDeNou = segonaFase(jugadors);
                } while (jugarDeNou);
            } 
        } else {
            mode2Jugadors = true;
            System.out.println("--------------------------------");
            System.out.println("        MODE DOS JUGADORS");
            System.out.println("--------------------------------");

            
            for (i = 0; i < NR_JUGADORS; i++) {
                jugadors.add(new Jugador((i+1), 0, 0));
            }
            
            while (jugadorActual < 2) {
                jugadorActual++;
                nPartides = 0;
                jugarPartida(jugadors);
                jugadors.get(jugadorActual-1).setEncerts(encerts);
                jugadors.get(jugadorActual-1).setTemps(tempsTotal);
                segonaFase(jugadors);
                jugadors.get(jugadorActual-1).setEncerts(encerts);
                jugadors.get(jugadorActual-1).setTemps(tempsTotal);
            }
            
            mostrarResultats2Jug(jugadors);
            
            
        }
        System.out.println("\nGr�cies per jugar!");
    }

        
    private boolean jugarPartida(ArrayList<Jugador> jugadors) {
        boolean resultat = false;
        encerts = 0;
        tempsTotal = 0;
        int i;
        
        if (refranysSeleccionats == null) {
            refranysSeleccionats = seleccionarRefranys();
        }
        
        if (nPartides == 0 && mode2Jugadors) {
            System.out.printf("\n---------------\n");
            System.out.printf(" JUGADOR: (%d) \n", jugadorActual);
            System.out.println("---------------");
        }
        
        
        
        ArrayList<String> primeres = new ArrayList<>();
        ArrayList<String> segones = new ArrayList<>();
        for (i = 0; i < NR_REFRANYS; i++) {
            primeres.add(refranysSeleccionats.get(i).getPrimeraMeitat());
            segones.add(refranysSeleccionats.get(i).getSegonaMeitat());
        }
        Collections.shuffle(segones);

        mostrarRefranys(primeres, segones);
        demanarJugades(primeres, segones, refranysSeleccionats);
        mostrarResultats();
        if (jugadors.size() == 1) {
            if (encerts != 5 && nPartides == 0) {
                resultat = tornarAJugar();
            } else {
                resultat = false;
            }
        } 
         
        
        nPartides++;
        return resultat;
    }

    private ArrayList<Refrany> seleccionarRefranys() {
        int i;
        String[] primeresText = {
            "Qui no vulgui pols", "No diguis blat", "A la taula i al llit", "Tal far�s,", "Qui dia passa,",
            "A l'estiu", "De porc i de senyor", "Hostes vingueren", "De mica en mica", "Al pot petit",
            "Si vols estar ben servit,", "Qui de jove no treballa,", "A la taula d'en Bernat,",
            "Qui canta a la taula i xiula al llit", "D'on no n'hi ha,"
        };
        String[] segonesText = {
            "que no vagi a l'era.", "fins que no el tinguis al sac i ben lligat.", "al primer crit.",
            "tal trobar�s.", "any empeny.", "tota cuca viu.", "se n'ha de venir de mena.",
            "que de casa ens tragueren.", "s'omple la pica.", "hi ha la bona confitura.",
            "fes-te tu mateix el llit.", "quan �s vell dorm a la palla.", "qui no hi �s, no hi �s comptat.",
            "no t� el seny gaire acomplit.", "no en raja."
        };
        String[] significatsText = {
            "Quan no es vulguin afrontar les conseq��ncies d?una acci� o situaci�, el millor �s evitar-la des del principi.",
            "No cal donar res per fet fins que no estigui totalment assegurat.",
            "Reflecteix la import�ncia de ser puntual, de la disciplina i d?estar sempre preparat per respondre amb celeritat.",
            "Transmet una lli�� de responsabilitat i �tica, recordant-nos que les nostres accions, bones o dolentes, ens acabaran retornant en forma de resultats o situacions similars.",
            "El temps avan�a, fins i tot quan sembla que no estem fent res d'especial per accelerar-lo; les coses van canviant de forma natural a mesura que vivim el present.",
            "En condicions favorables o en �poques de prosperitat, fins i tot aquelles persones que normalment viuen en condicions dif�cils poden beneficiar-se d'aquestes circumst�ncies positives.",
            "Les persones, per molt que ho intentin, no poden escapar completament del seu origen o condicions inicials.",
            "La pres�ncia de certes persones o circumst�ncies pot canviar la din�mica habitual d?un lloc o una situaci�.",
            "Transmet una lli�� de paci�ncia i persist�ncia: a vegades cal temps i esfor� continu per aconseguir grans resultats.",
            "No cal jutjar les coses nom�s pel seu aspecte extern o la seva mida, ja que el que realment importa �s el contingut.",
            "Transmet la import�ncia de ser aut�nom i responsable de les pr�pies accions i decisions, en lloc de deixar-ho tot en mans d'altres.",
            "Transmet una lli�� de responsabilitat i planificaci� per al futur: �s important treballar i estalviar quan som joves per poder viure millor quan siguem grans.",
            "Per formar part d'alguna cosa, cal estar-hi present i involucrat. �s una crida a la responsabilitat i a la participaci� activa.",
            "�s una cr�tica a la falta de discerniment i a la falta d'harmonia entre el comportament i el context.",
            "Si no hi ha els mitjans, els recursos o les condicions necess�ries per obtenir alguna cosa, simplement no es pot aconseguir. Les expectatives han de ser realistes segons les circumst�ncies."
        };
        ArrayList<Refrany> refranys = new ArrayList<>();
        for (i = 0; i < NR_REFRANYS_TOTALS; i++) {
            refranys.add(new Refrany(i, primeresText[i], segonesText[i], significatsText[i]));
        }
        Collections.shuffle(refranys);
        
        ArrayList<Refrany> refranysPassador = new ArrayList<>(NR_REFRANYS);
        refranysPassador = refranys;
        return refranysPassador;
    }
    
    private void mostrarRefranys(ArrayList<String> primeres, ArrayList<String> segones) {
        int i;
        
        System.out.printf("\nREFRANYS CATALANS\nRelaciona l'inici d'aquests refranys catalans amb la seva part corresponent:\n-------------------------------------------------------------------------------------\n");
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-36s  | %d - %-36s %n",
                    primeres.get(i), (i + 1), segones.get(i));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void demanarJugades(ArrayList<String> primeres, ArrayList<String> segones, ArrayList<Refrany> refranys) {
        int resposta, i;
        
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona la segona meitat pel refrany '%s' (1 - %d): ", primeres.get(i), NR_REFRANYS);
            resposta = Jugada();
            validarResposta(primeres.get(i), segones.get(resposta), refranys);
        }
    }
    
    private void demanarJugadesSig(ArrayList<String> significats) {
        int resposta, i;
        
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona el significat del refrany '%s' (1 - %d): ", refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat(), NR_REFRANYS);
            resposta = Jugada();
            validarRespostaSig(significats.get(resposta), refranysSeleccionats.get(i).getSignificat());
        }
    }
    
    private int Jugada() {
        double tempsInici;
        double tempsFinal;
        int resposta;
        tempsInici = System.currentTimeMillis();
        
        resposta = 0;
        do {
            boolean error;
            do {
                try {
                    error = false;
                    resposta = scanner.nextInt() - 1;
                } catch (InputMismatchException ex) {
                    System.out.println("La resposta introdu�da no �s v�lida. Si us plau, torni a provar: ");
                    scanner.next();
                    error = true;
                }
            } while (error);
                
            if (resposta < 0 || resposta > NR_REFRANYS - 1) {
                System.out.printf("La resposta introdu�da no �s v�lida. Si us plau, torni a provar: ");
            }
        } while (resposta < 0 || resposta > NR_REFRANYS - 1);
        tempsFinal = System.currentTimeMillis() - tempsInici;
        tempsTotal += tempsFinal / 1000.0;
            
        return resposta;
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
    
    private void validarRespostaSig(String significat, String refrany_significat) {
        if (refrany_significat.equals(significat)) {
            System.out.println("Correcte!");
            encerts++;
        } else {
            System.out.println("Incorrecte!");
        }
    }

    private void mostrarResultats() {
        int errors;
        errors = NR_REFRANYS - encerts;
        System.out.printf("\nEncerts: %d%nErrors: %d%nTemps total: %.2f segons%n", encerts, errors, tempsTotal);
    }
    
    private void mostrarResultats2Jug(ArrayList<Jugador> jugadors) {
        System.out.println("------------------------------------------------------");
        System.out.printf("%-25s | %-25s \n", "JUGADOR (1)", "JUGADOR (2)");
        System.out.println("------------------------------------------------------");
        System.out.printf("%-25s | %-25s\n", "Encerts: "+jugadors.get(0).getEncerts(), "Encerts: "+jugadors.get(1).getEncerts());
        System.out.printf("%-25s | %-25s\n", "Errors: "+(NR_REFRANYS*2 - jugadors.get(0).getEncerts()), "Errors: "+(NR_REFRANYS*2 - jugadors.get(1).getEncerts()));
        System.out.printf("%-25s | %-25s\n", "Temps total: "+ (float) jugadors.get(0).getTemps(), "Temps total: "+ (float) jugadors.get(1).getTemps());

        
        System.out.println("");
        if (jugadors.get(0).getEncerts() < jugadors.get(1).getEncerts()) {
            System.out.println("---------------------------");
            System.out.println("  JUGADOR (1): HA GUANYAT");
            System.out.println("---------------------------");
        } else if (jugadors.get(0).getEncerts() == jugadors.get(1).getEncerts()) {
            if (jugadors.get(0).getTemps() < jugadors.get(1).getTemps()) {
                System.out.println("---------------------------");
                System.out.println("  JUGADOR (1): HA GUANYAT");
                System.out.println("---------------------------");
            } else {
                System.out.println("---------------------------");
                System.out.println("  JUGADOR (2): HA GUANYAT");
                System.out.println("---------------------------");
            }
        } else {
            System.out.println("---------------------------");
            System.out.println("  JUGADOR (2): HA GUANYAT");
            System.out.println("---------------------------");
        }
        

    }            

    private boolean segonaFase (ArrayList<Jugador> jugadors) {
        boolean resultat = false;
        encerts = 0;
        tempsTotal = 0;
        int i;
        
        ArrayList<String> significats = new ArrayList<>(NR_REFRANYS);
        
        for (i = 0; i < NR_REFRANYS ; i++) {
            significats.add(refranysSeleccionats.get(i).getSignificat());
        }
        
        Collections.shuffle(significats);
        
        System.out.printf("\nREFRANYS CATALANS\nRelaciona els refranys amb el seu significat corresponent:\n-------------------------------------------------------------------------------------\n");
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-70s | %d - %s %n",
                    refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat(),(i+1), significats.get(i));
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        demanarJugadesSig(significats);
        mostrarResultats();
        
        if (jugadors.size() == 1) {
            if (encerts != 5 && nPartides == 0) {
                resultat = tornarAJugar();
            } else {
                resultat = false;
            }
        }
        nPartides++;
        
        return resultat;
    }
                
    private void mostrarSolucio() {
        String resposta;
        int i;
        
        scanner.nextLine();
        System.out.println("\nPremi \"Enter\" per continuar...");
        resposta = scanner.nextLine();
        System.out.printf("\nSOLUCIONS DELS REFRANYS\n-------------------------------------------------------------------------------------\n");
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%s %n",
                    refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
    
    private boolean tornarAJugar() {
        System.out.println("\nVols jugar una altra partida? (S� / No)");
        return siNo();
    }
    
    private boolean passarASeguentFase() {
        System.out.println("\nVols jugar a emparellar refranys amb el seu significat? (S� / No)");
        return siNo();
    }
    
    private boolean siNo() {
        String resposta;
        resposta = "";
        do {
            resposta = scanner.next().trim().toLowerCase();
            if (!resposta.equals("s�") && !resposta.equals("si") && !resposta.equals("no")) {
                System.out.printf("La resposta introdu�da no �s v�lida. Si us plau, torni a provar: ");
            }
        } while (!resposta.equals("s�") && !resposta.equals("si") && !resposta.equals("no"));
        return resposta.equals("s�") || resposta.equals("si");
    }

    public static void main(String[] args) {
        new ProgrammingTarnished_Refranys().iniciarJoc();
    }
}
