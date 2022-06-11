package it.unibs.fp.game;

import java.util.ArrayList;

public class Livello {
    Mappa mappa;
    Personaggio p;
    ArrayList<Mostro> mostri;
    ArrayList<Chest> forzieri;
    int princessX;
    int princessY;

    public Livello() {
        this.mappa = new Mappa();
    }

    public void creaLivello() {
        for(int i = 0; i < mappa.getHeight(); i++) {
            for(int j = 0; j < mappa.getWidth(); j++) {
                creaElementi(mappa.getCella(i, j));
            }
        }
    }

    private void creaElementi(Cella cella) {
        switch(cella.getC()) {
            case 'O':
                creaGiocatore();
                break;
            case 'M':
                creaMostro();
                break;
            case 'C':
                creaCesta();
                break;
            case :
                break;
            case :
                break;
            case :
                break;



        }
    }


}
