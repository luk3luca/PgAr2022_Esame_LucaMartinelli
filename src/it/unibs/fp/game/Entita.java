package it.unibs.fp.game;

public class Entita {
    String nome;
    int hp;
    int attaccoBase;
    int difesaBase;
    Oggetto ogg;
    int x;
    int y;

    public Entita(String nome, int hp, int attaccoBase, int difesaBase, Oggetto ogg, int x, int y) {
        this.nome = nome;
        this.hp = hp;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
        this.ogg = ogg;
        this.x = x;
        this.y = y;
    }
}
