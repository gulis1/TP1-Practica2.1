package logic.GameObjects;

import logic.Game;

public abstract class GameObject implements IAttack {
    protected static Game game;
    protected int vida, x, y;
    protected String letra;

    public GameObject(int x, int y, int vida){
        this.x = x;
        this.y = y;
        this.vida = vida;
    }

    public static void setGame(Game game1) {
        game = game1;
    }

    public boolean isAlive() { return vida > 0; }
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
    public String getString() { return String.format("%s (%d)", letra, vida); }

    public abstract void attack();
    public abstract void move();


}

