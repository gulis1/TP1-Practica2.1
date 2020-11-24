package logic.GameObjects;

import logic.Game;

public abstract class GameObject implements IAttack {
    protected Game game;
    protected int vida, x, y;
    protected String letra;

    public GameObject(int x, int y, int vida, Game game){
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.game = game;
    }


    public boolean isAlive() { return vida > 0; }
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
    public String getString() { return String.format("%s (%d)", letra, vida); }

    public abstract void move();
    public abstract void attack();
}

