package logic.GameObjects;

import logic.Game;

import java.util.Random;

public abstract class GameObject implements IAttack {
    protected Game game;
    protected int vida, x, y;
    protected String letra;

 // constructor
    public GameObject(int x, int y, int vida, Game game){
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.game = game;
    }

    // metodo que devuele true si la vida del objeto es mayor que 0.
    public boolean isAlive() { return vida > 0; }

    //metodo que devuleve true si el objeto esta enesa posicion.
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }


    //metodo que debuelve la vida y el idnetificador del objeto.
    public String getString() { return String.format("%s [%d]", letra, vida); }


    //metodos abstractos
    public abstract void move();
    public abstract void attack();
}

