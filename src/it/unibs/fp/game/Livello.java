package it.unibs.fp.game;

import it.unibs.fp.librerie.ClassMenu;
import it.unibs.fp.librerie.Metodi;
import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class Livello {
    private static final int SPOST = 1;

    private Mappa mappa;
    private Personaggio p;
    private ArrayList<Mostro> mostri = new ArrayList<>();
    private ArrayList<Chest> forzieri = new ArrayList<>();
    private int princessCol;
    private int princessRow;
    private boolean princessSalva;

    public Livello() {
        this.mappa = new Mappa();
        princessSalva = false;
        creaElementiLivello();
    }

    public boolean getPrincessSalva() {
        return princessSalva;
    }

    public Personaggio getP() {
        return p;
    }

    public Mappa getMappa() {
        return mappa;
    }

    public void creaElementiLivello() {
        //AGGIUNTA PRINCIPESSA
        princessCol = 30;
        princessRow = 0;
        mappa.getMappa()[princessRow][princessCol] = new Cella('K');

        for(int i = 0; i < mappa.getHeight(); i++) {
            for(int j = 0; j < mappa.getWidth(); j++) {
                creaElementi(mappa.getCella(i, j), j, i);
            }
        }
    }

    private void creaElementi(Cella cella, int x, int y) {
        switch(cella.getC()) {
            case 'O':
                creaGiocatore(x , y);
                break;
            case 'M':
                mostri.add(creaMostro(x, y));
                break;
            case 'C':
                forzieri.add(creaCesta(x, y));
                break;
            default:
                break;
        }
    }

    public void creaGiocatore(int x, int y) {
        this.p = new Personaggio(InputDati.leggiStringaNonVuota("Inserire nome del giocatore: "), x, y);
    }

    public Mostro creaMostro(int x, int y) {
        String name = Metodi.generatePermutation("dijkstra");

        int hp = Metodi.generateRandom(15, 25);
        int potenza = Metodi.generateRandom(35, 55);
        Oggetto ogg = new Oggetto(Oggetto.ARMA, potenza);

        return new Mostro(name, hp, ogg, x, y);
    }

    public Chest creaCesta(int x, int y) {
        String tipo = oggettoProbabilita();
        int potenza = switch (tipo) {
            case Oggetto.ARMA -> Metodi.generateRandom(35, 55);
            case Oggetto.SCUDO -> 1;
            case Oggetto.POZIONE -> 1;
            default -> 0;
        };

        return new Chest(new Oggetto(tipo, potenza), x, y);

    }

    public String oggettoProbabilita() {
        String[] oggetti = new String[100];
        int countA = 0;
        int countS = 0;
        int countP = 0;

        for(int i = 0; i < 100; i++) {
            if(countA < Chest.PROB_ARMA) {
                oggetti[i] = Oggetto.ARMA;
                countA++;
            }
            else if(countS < Chest.PROB_SCUDO) {
                oggetti[i] = Oggetto.SCUDO;
                countS++;
            }
            else if(countP < Chest.PROB_POZIONE) {
                oggetti[i] = Oggetto.POZIONE;
                countP++;
            }
        }

        int index = Metodi.generateRandom(0,99);
        return oggetti[index];
    }

    public boolean inputComandi() {
        char comando = InputDati.leggiUpperChar("Inserire comando (WASD movimento, E apri forziere, M apri inventario)", "W A S D E M");
        switch (comando) {
            case 'W', 'S', 'A', 'D' -> {
                movimentoPersonaggio(comando);
                return true;
            }
            case 'E' -> {
                apriCesta();
                return true;
            }
            case 'M' -> {
                return mostraMenu();
            }
            default -> {
                return false;
            }
        }
    }

    public int sceltaMenuInventario() {
        String[] objs = new String[p.getnObj()];
        for(int i = 0; i < p.getnObj(); i++)
            objs[i] = p.getInventario().get(i).toString();

        ClassMenu menuObj = new ClassMenu("Scegli oggetto (0 per non modificare): ", objs);
        return menuObj.scegli();
    }

    public boolean controllaTipo(String tipo) {
        for(Oggetto o : p.getInventario())
            if(o.getTipo().equals(tipo))
                return true;
        return false;
    }

    public void apriCesta() {
        Chest oldC = null;
        for(Chest c : forzieri) {
            if(p.getX() == c.getX() && p.getY() == c.getY()) {
                Oggetto newObj = c.getOgg();
                System.out.println(newObj);
                if(newObj.getTipo() == Oggetto.ARMA && controllaTipo(Oggetto.ARMA) || newObj.getTipo() == Oggetto.SCUDO && controllaTipo(Oggetto.SCUDO)) {
                    Oggetto old = null;
                    for(int i = 0; i < p.getnObj(); i++)
                        if (newObj.getTipo().equals(p.getInventario().get(i).getTipo())) {
                            old = p.getInventario().get(i);
                            break;
                        }

                    ClassMenu menuObj = new ClassMenu("Scegli oggetto (0 per non modificare): ", new String[]{"New: " + newObj, "Old: " + old.toString()});
                    int scelta = menuObj.scegliNoZero();

                    if(scelta == 1) {
                        p.aggiungiOggettoInventario(newObj);
                        p.rimuoviOggettoInventario(old);
                    }
                }
                else if(p.getnObj() == Personaggio.MAX_OGGETTI) {
                    System.out.println("Sostituisci oggetto");
                    int scelta = sceltaMenuInventario();

                    if(scelta != 0) {
                        Oggetto oldObj = p.getInventario().get(scelta - 1);
                        p.aggiungiOggettoInventario(newObj);
                        p.rimuoviOggettoInventario(oldObj);
                    }
                }
                else {
                    p.setOgg(newObj);
                    p.aggiungiOggettoInventario(newObj);
                    System.out.println("Scegli oggetto da usare");
                    int scelta = sceltaMenuInventario();

                    if(scelta != 0)
                        p.setOgg(p.getInventario().get(scelta - 1));
                }

                oldC = c;
                break;
            }
        }
        //RIMOZIONE CESTA APERTA
        if(oldC != null)
            this.forzieri.remove(oldC);
        System.out.println("Nessun forziere da aprire");
    }

    private void usaOggettoInventario() {
    }


    private boolean mostraMenu() {
        ClassMenu menu = new ClassMenu("Scelta: ", new String[]{"Inventario", "Statistiche", "Abbandona la partita", "Usa oggetto da Inventario"});
        switch (menu.scegliNoZero()) {
            case 1 -> {
                usaOggettoInventario();
                System.out.println("Inventario:\n" + p.stampaInventario());
                return false;
            }
            case 2 -> {
                System.out.println("Statistiche:\n" + p.stampaStatistiche());
                return false;
            }
            case 3 -> {
                System.out.println("Partita terminata");
                System.exit(0);
                return false;
            }
            case 4 -> {
                int scelta = sceltaMenuInventario();
                p.setOgg(p.getInventario().get(scelta - 1));
                if (p.getOgg().getTipo() == Oggetto.POZIONE) {
                    p.cura(Personaggio.HP / 2);
                    return true;
                }
                return false;
            }
            default -> {
                return false;
            }
        }
    }


    private void movimentoPersonaggio(char comando) {
        int oldX = p.getX();
        int oldY = p.getY();
        int newY;
        int newX;

        switch (comando) {
            case 'W' -> {
                newY = oldY - SPOST;
                if (newY >= 0) {
                    if (mappa.getCella(newY, oldX).getC() != '#') {
                        p.setY(newY);
                        //VECCHIA CELLA
                        mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        mappa.setCella(newY, oldX, 'O');
                    }
                } else {
                    System.out.println("Limite");
                }
            }
            case 'S' -> {
                newY = oldY + SPOST;
                if (newY < mappa.getHeight()) {
                    if (mappa.getCella(newY, oldX).getC() != '#') {
                        p.setY(newY);
                        //VECCHIA CELLA
                        mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        mappa.setCella(newY, oldX, 'O');
                    }
                } else {
                    System.out.println("Limite");
                }
            }
            case 'A' -> {
                newX = oldX - SPOST;
                if (newX >= 0) {
                    if (mappa.getCella(oldY, newX).getC() != '#') {
                        p.setX(newX);
                        //VECCHIA CELLA
                        mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        mappa.setCella(oldY, newX, 'O');
                    }
                } else {
                    System.out.println("Limite");
                }
            }
            case 'D' -> {
                newX = oldX + SPOST;
                if (newX < mappa.getWidth()) {
                    if (mappa.getCella(oldY, newX).getC() != '#') {
                        p.setX(newX);
                        //VECCHIA CELLA
                        mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        mappa.setCella(oldY, newX, 'O');
                    }
                } else {
                    System.out.println("Limite");
                }
            }
            default -> {
            }
        }
    }

    public void isAMostro() {
        boolean esito;
        Mostro oldM = null;

        for(Mostro m : mostri) {
            if(p.getX() == m.getX() && p.getY() == m.getY()) {
                Scontro s = new Scontro(p, m);
                esito = s.battaglia();
                if(esito) {
                    System.out.println("Hai vinto lo scontro con il mostro");
                }
                else {
                    System.out.println("Hai perso lo scontro con il Mostro");
                }

                oldM = m;
                break;
            }
        }
        this.mostri.remove(oldM);
    }

    public boolean isPrincessSalva() {
        if(p.getX() == princessCol && p.getY() == princessRow)
            return true;
        return false;
    }

    public void pathfindingMostri() {
        for(Mostro m : mostri)
            spostamentoMostro(m);
        return;
    }

    public void spostamentoMostro(Mostro m) {
        int direzione = Metodi.generateRandom(1,4);;
        int nSpost = 0;

        int oldX = m.getX();
        int oldY = m.getY();
        int newY;
        int newX;

        switch (direzione) {
            case 1 -> {     //W
                newY = oldY - SPOST;
                if (newY >= 0)
                    if (this.mappa.getCella(newY, oldX).getC() == '.' || this.mappa.getCella(newY, oldX).getC() == 'O') {
                        m.setY(newY);
                        //VECCHIA CELLA
                        this.mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        this.mappa.setCella(newY, oldX, 'M');
                    }
            }
            case 2 -> {     //S
                newY = oldY + SPOST;
                if (newY < this.mappa.getHeight())
                    if (this.mappa.getCella(newY, oldX).getC() == '.' || this.mappa.getCella(newY, oldX).getC() == 'O') {
                        m.setY(newY);
                        //VECCHIA CELLA
                        this.mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        this.mappa.setCella(newY, oldX, 'M');
                    }
            }
            case 3 -> {     //A
                newX = oldX - SPOST;
                if (newX >= 0)
                    if (this.mappa.getCella(oldY, newX).getC() == '.' || this.mappa.getCella(oldY, newX).getC() == 'O') {
                        m.setX(newX);
                        //VECCHIA CELLA
                        this.mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        this.mappa.setCella(oldY, newX, 'M');
                    }
            }
            case 4 -> {     //D
                newX = oldX + SPOST;
                if (newX < this.mappa.getWidth())
                    if (this.mappa.getCella(oldY, newX).getC() == '.' || this.mappa.getCella(oldY, newX).getC() == 'O') {
                        m.setX(newX);
                        //VECCHIA CELLA
                        this.mappa.setCella(oldY, oldX, '.');
                        //NUOVA CELLA
                        this.mappa.setCella(oldY, newX, 'M');
                    }
            }
            default -> {
            }
        }
    }

}


