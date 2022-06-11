package it.unibs.fp.game;

import it.unibs.fp.librerie.Metodi;

public class Scontro {
    Personaggio p;
    Mostro m;

    public Scontro(Personaggio p, Mostro m) {
        this.p = p;
        this.m = m;
    }
    
    public boolean battaglia() {
        int turno = 0;

        while(this.p.isVivo() && this.m.isVivo()) {
            if(Metodi.isPari(turno)) {
                infliggiDanno(p, m);
            }
            else {
                infliggiDanno(m, p);
            }
            turno++;
        }

        if(p.isVivo())
            return true;
        else
            return false;
    }

    public void infliggiDanno(Entita e1, Entita e2) {
        int danno = calcolaDanno(e1);
        e2.setHp(danno);
    }
    
    public int calcolaDanno(Entita e) {
        return (int) ((double)(((2 * e.getOgg().getPotenza() * e.getAttaccoBase()) / (25 * e.getDifesaBase())) + 2 ) * calcolaModificatore());
    }

    private double calcolaModificatore() {
        double[] prob = new double[1000];
        for(int i = 0; i < 1000; i++) {
            if(i < 75)
                prob[i] = 1.5;
            else
                prob[i] = 1;
        }
        return prob[Metodi.generateRandom(0, 999)];
    }
}
