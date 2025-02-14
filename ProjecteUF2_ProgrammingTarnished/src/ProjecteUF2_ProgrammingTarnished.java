import java.util.*;

class Refrany {
    private final int id;
    private final String primeraMeitat;
    private final String segonaMeitat;
    private final String significat;

    /*
    * Constructor per crear un nou refrany amb un id, la primera i segona meitat
    * del refrany, i el seu significat. Ens ajuda a instanciar objectes de tipus Refrany.
    */
    public Refrany(int id, String primera, String segona, String significat) {
        this.id = id;
        this.primeraMeitat = primera;
        this.segonaMeitat = segona;
        this.significat = significat;
    }

    /*
    * Simple getter que retorna l'identificador del refrany.
    * Ens serveix per saber quin refrany estem manejant.
    */
    public int getId() {
        return id;
    }
    
    /*
    * Torna la primera meitat del refrany. Ens ajuda a obtenir la
    * primera part per mostrar-la i comparar-la després amb la segona meitat.
    */
    public String getPrimeraMeitat() {
        return primeraMeitat;
    }
    
    /*
    * Torna la segona meitat del refrany. Similar a getPrimeraMeitat(), però per la part que falta.
    */
    public String getSegonaMeitat() {
        return segonaMeitat;
    }
    
    /*
    * Torna el significat del refrany. És útil per mostrar el significat quan
    * el jugador ho demana o per verificar les respostes a la segona fase del joc.
    */
    public String getSignificat() {
        return significat;
    }
}

class Jugador {
    private int idJugador;
    private double tempsPuntuacio;
    private int encertsPuntuacio;
    
    /*
    * Constructor per crear un jugador amb un identificador, temps de puntuació
    * i nombre d'encerts. Així gestionem les estadístiques de cada jugador.
    */
    public Jugador(int idJugador, double tempsPuntuacio, int encertsPuntuacio) {
        this.idJugador = idJugador;
        this.tempsPuntuacio = tempsPuntuacio;
        this.encertsPuntuacio = encertsPuntuacio;
    }
    
    /*
    * Retorna l'identificador del jugador. És essencial per saber quin jugador és el que està jugant.
    */
    public int getId() {
        return idJugador;
    }
    
    /*
    * Suma els encerts del jugador. Aquest mètode ens permet actualitzar
    * el nombre d'encerts quan el jugador encerta una resposta.
    */
    public void setEncerts(int encerts) {
        this.encertsPuntuacio += encerts;
    }
    
    /*
    * Suma el temps de puntuació del jugador. Ens ajuda a acumular el
    * temps total que ha passat el jugador jugant.
    */
    public void setTemps(double temps) {
        this.tempsPuntuacio += temps;
    }
    
    /*
    * Torna el nombre total d'encerts d'un jugador. Utilitzat per veure
    * quants encerts ha fet un jugador durant la partida.
    */
    public int getEncerts() {
        return encertsPuntuacio;
    }
    
    /*
    * Torna el temps total de puntuació d'un jugador. Ens diu quant temps
    * ha trigat el jugador en completar la partida.
    */
    public double getTemps() {
        return tempsPuntuacio;
    }
}


public class ProjecteUF2_ProgrammingTarnished {
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
    
    /*
    * Mètode que gestiona la selecció del mode de joc, si és a un jugador
    * o a dos. Ens permet obtenir la selecció del jugador, assegurant-nos que la resposta sigui vàlida.
    */
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
                    System.out.println("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
                    scanner.next();
                    error = true;
                }
            } while (error);
                
            if (resposta != 1 && resposta != 2) {
                System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
            }
        } while (resposta != 1 && resposta != 2);
        return resposta == 1;
    }
    
    /*
    * Mètode que inicia el joc. Aquí es defineix si el jugador vol jugar a mode individual
    * o en mode dos jugadors, i es criden les funcions necessàries per començar el joc.
    */
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
        System.out.println("\nGràcies per jugar!");
    }

    /*
    * Gestiona una partida individual, amb la lògica per mostrar les mitges de
    * refranys i obtenir les respostes del jugador. També actualitza els resultats d'encerts i temps.
    */
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
    
    /*
    * Selecciona aleatòriament un conjunt de refranys i els emmagatzema
    * en una llista. Això genera la base de dades de refranys que es faran servir durant el joc.
    */
    private ArrayList<Refrany> seleccionarRefranys() {
        int i;
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
        for (i = 0; i < NR_REFRANYS_TOTALS; i++) {
            refranys.add(new Refrany(i, primeresText[i], segonesText[i], significatsText[i]));
        }
        Collections.shuffle(refranys);
        
        ArrayList<Refrany> refranysPassador = new ArrayList<>(NR_REFRANYS);
        refranysPassador = refranys;
        return refranysPassador;
    }
    
    /*
    * Mostra al jugador les primeres i segones meitats dels refranys per tal que les relacioni correctament.
    */
    private void mostrarRefranys(ArrayList<String> primeres, ArrayList<String> segones) {
        int i;
        
        System.out.printf("\nREFRANYS CATALANS\nRelaciona l'inici d'aquests refranys catalans amb la seva part corresponent:\n-------------------------------------------------------------------------------------\n");
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("%-36s  | %d - %-36s %n",
                    primeres.get(i), (i + 1), segones.get(i));
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }
    
    /*
    * Demana a l'usuari que selecciona la segona meitat del refrany. També valida si la resposta és correcta.
    */
    private void demanarJugades(ArrayList<String> primeres, ArrayList<String> segones, ArrayList<Refrany> refranys) {
        int resposta, i;
        
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona la segona meitat pel refrany '%s' (1 - %d): ", primeres.get(i), NR_REFRANYS);
            resposta = Jugada();
            validarResposta(primeres.get(i), segones.get(resposta), refranys);
        }
    }
    
    /*
    * Similar a demanarJugades(), però en aquesta fase els jugadors han de relacionar el refrany amb el seu significat.
    */
    private void demanarJugadesSig(ArrayList<String> significats) {
        int resposta, i;
        
        for (i = 0; i < NR_REFRANYS; i++) {
            System.out.printf("Selecciona el significat del refrany '%s' (1 - %d): ", refranysSeleccionats.get(i).getPrimeraMeitat() + " " + refranysSeleccionats.get(i).getSegonaMeitat(), NR_REFRANYS);
            resposta = Jugada();
            validarRespostaSig(significats.get(resposta), refranysSeleccionats.get(i).getSignificat());
        }
    }
    
    /*
    * Gestiona la jugada d'un jugador. Llegeix la resposta del jugador, valida la seva entrada i calcula el temps que ha trigat a respondre.
    */
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
            
        return resposta;
    }
    
    /*
    * Comprova si la resposta del jugador és correcta comparant-la amb el refrany seleccionat.
    */
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
    
    /*
    * Comprova si la resposta sobre el significat del refrany és correcta.
    */
    private void validarRespostaSig(String significat, String refrany_significat) {
        if (refrany_significat.equals(significat)) {
            System.out.println("Correcte!");
            encerts++;
        } else {
            System.out.println("Incorrecte!");
        }
    }

    /*
    * Mostra els resultats de la partida: el nombre d'encerts, errors i el temps total que ha trigat el jugador.
    */
    private void mostrarResultats() {
        int errors;
        errors = NR_REFRANYS - encerts;
        System.out.printf("\nEncerts: %d%nErrors: %d%nTemps total: %.2f segons%n", encerts, errors, tempsTotal);
    }
    
    /*
    * Mostra els resultats comparatius entre dos jugadors (en mode multijugador),
    * amb el nombre d'encerts, errors i temps de cada jugador. També decideix qui ha guanyat la partida.
    */
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
    
    /*
    * Gestiona la segona fase del joc, on els jugadors han de relacionar
    * els refranys amb els seus significats. Aquest mètode també actualitza els resultats del jugador.
    */
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
    
    /*
    * Mostra les solucions finals dels refranys. És útil per donar retroalimentació al jugador després de la partida.
    */
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
    
    /*
    * Pregunta al jugador si vol jugar una altra partida. Si la resposta és "sí", torna a començar la partida.
    */
    private boolean tornarAJugar() {
        System.out.println("\nVols jugar una altra partida? (Sí / No)");
        return siNo();
    }
    
    /*
    * Pregunta al jugador si vol passar a la segona fase, on s'han de relacionar els refranys amb els seus significats.
    */
    private boolean passarASeguentFase() {
        System.out.println("\nVols jugar a emparellar refranys amb el seu significat? (Sí / No)");
        return siNo();
    }
    
    /*
    * Funció per obtenir una resposta sí/no del jugador, que és cridada en diversos punts del joc per obtenir respostes de l'usuari.
    */
    private boolean siNo() {
        String resposta;
        resposta = "";
        do {
            resposta = scanner.next().trim().toLowerCase();
            if (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no")) {
                System.out.printf("La resposta introduïda no és vàlida. Si us plau, torni a provar: ");
            }
        } while (!resposta.equals("sí") && !resposta.equals("si") && !resposta.equals("no"));
        return resposta.equals("sí­") || resposta.equals("si");
    }

    /*
    * Mètode principal que inicialitza el joc. Crida a iniciarJoc() per començar la partida.
    */
    public static void main(String[] args) {
        new ProjecteUF2_ProgrammingTarnished().iniciarJoc();
    }
}
