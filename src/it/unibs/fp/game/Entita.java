package it.unibs.fp.game;

public class Entita {
    String nome;
    int hp;
    int attaccoBase;
    int difesaBase;
    Oggetto ogg;
    int x;
    int y;
    boolean isVivo;

    public Entita(String nome, int hp, int attaccoBase, int difesaBase, Oggetto ogg, int x, int y) {
        this.nome = nome;
        this.hp = hp;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
        this.ogg = ogg;
        this.x = x;
        this.y = y;
        this.isVivo = true;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public int getAttaccoBase() {
        return attaccoBase;
    }

    public int getDifesaBase() {
        return difesaBase;
    }

    public Oggetto getOgg() {
        return ogg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHp(int danno) {
        this.hp -= danno;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOgg(Oggetto ogg) {
        this.ogg = ogg;
    }

    public boolean isVivo() {
        return this.hp > 0;
    }


}
