package it.unibs.fp.game;

import it.unibs.fp.librerie.Metodi;

public class Scontro {
    Entita e1;
    Entita e2;

    public Scontro(Entita e1, Entita e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public void battaglia() {
        int turno = 0;
        int danno1 = 0;
        int danno2 = 0;

        while(this.e1.isVivo() && this.e2.isVivo()) {
            System.out.println("Scontro:");
            if(Metodi.isPari(turno)) {
                danno1 = infliggiDanno(e1, e2);
                System.out.println("+  " + e1.getNome() + " ha fatto " + danno1 + " a " + e2.getNome() + ", ora ha " + e2.getHp() + " HP");
            }
            else {
                danno2 = infliggiDanno(e2, e1);
                System.out.println("+  " + e2.getNome() + " ha fatto " + danno2 + " a " + e1.getNome() + ", ora ha " + e1.getHp() + " HP");
            }
            turno++;
        }
    }

    public int infliggiDanno(Entita e1, Entita e2) {
        int danno = calcolaDanno(e1);
        e2.setHp(danno);
        return danno;
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
