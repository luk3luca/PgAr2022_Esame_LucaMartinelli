package it.unibs.fp.game;

import it.unibs.fp.librerie.Metodi;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

import java.util.ArrayList;

public class Livello {
    private static final int SPOST = 1;

    Mappa mappa;
    Personaggio p;
    ArrayList<Mostro> mostri;
    ArrayList<Chest> forzieri;
    int princessCol;
    int princessRow;

    public Livello() {
        this.mappa = new Mappa();
        creaElementiLivello();
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
        this.p = new Personaggio(InputDati.leggiStringaNonVuota("Inserire nome del giocatore"), x, y);
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
            case Oggetto.SCUDO -> 5;
            case Oggetto.POZIONE -> 0;
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

    public void turno() {

    }

    public boolean inputComandi() {
        char comando = InputDati.leggiUpperChar("Inserire comando (WASD movimento, E apri, M inventario)", "WASDEM");
        switch(comando){
            case 'W': case 'S': case 'A': case 'D':
                movimentoPoersonaggio(comando);
                return true;
                break;
            case 'E':
                apriCesta();
                break;
            case 'M':
                mostraMenu();
                break;
            default:
                break;
        }
        return false;
    }

    private void mostraMenu() {
        MyMenu menu = new MyMenu("Scelta: ", )
    }

    private void movimentoPoersonaggio(char comando) {
        int oldX = p.getX();
        int oldY = p.getY();
        int newY;
        int newX;

        switch (comando) {
            case 'W':
                newY = oldY - SPOST;
                if (newY >= 0 && mappa.getCella(newY, oldX).getC() != '#') {
                    p.setY(newY);
                    //VECCHIA CELLA
                    mappa.setCella(oldY, oldX, '.');
                    //NUOVA CELLA
                    mappa.setCella(newY, oldX, 'O');
                } else {
                    System.out.println("Limite");
                }
                break;
            case 'S':
                newY = oldY + SPOST;
                if (newY <= mappa.getHeight() && mappa.getCella(newY, oldX).getC() != '#') {
                    p.setY(newY);
                    //VECCHIA CELLA
                    mappa.setCella(oldY, oldX, '.');
                    //NUOVA CELLA
                    mappa.setCella(newY, oldX, 'O');
                } else {
                    System.out.println("Limite");
                }
                break;
            case 'A':
                newX = oldX - SPOST;
                if (newX >= 0 && mappa.getCella(oldY, newX).getC() != '#') {
                    p.setX(newX);
                    //VECCHIA CELLA
                    mappa.setCella(oldY, oldX, '.');
                    //NUOVA CELLA
                    mappa.setCella(oldY, newX, 'O');
                } else {
                    System.out.println("Limite");
                }
                break;
            case 'D':
                newX = oldX + SPOST;
                if (newX <= mappa.getWidth() && mappa.getCella(oldY, newX).getC() != '#') {
                    p.setX(newX);
                    //VECCHIA CELLA
                    mappa.setCella(oldY, oldX, '.');
                    //NUOVA CELLA
                    mappa.setCella(oldY, newX, 'O');
                } else {
                    System.out.println("Limite");
                }
                break;

            default:
                System.out.println("NA");
                break;
        }
    }


}


