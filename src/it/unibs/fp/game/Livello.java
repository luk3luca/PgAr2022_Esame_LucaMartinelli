package it.unibs.fp.game;

import it.unibs.fp.librerie.Metodi;
import it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;

public class Livello {
    Mappa mappa;
    Personaggio p;
    ArrayList<Mostro> mostri;
    ArrayList<Chest> forzieri;
    int princessCol;
    int princessRow;

    public Livello() {
        this.mappa = new Mappa();
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
                forzieri.add(creaCesta());
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

    public Chest creaCesta() {
        String tipo = oggettoProbabilita();
        int potenza = switch (tipo) {
            case Oggetto.ARMA -> Metodi.generateRandom(35, 55);
            case Oggetto.SCUDO -> 5;
            case Oggetto.POZIONE -> 0;
            default -> 0;
        };

        return new Chest(new Oggetto(tipo, potenza));

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

}
