package logic.GameObjects;

import logic.Game;

public class BloodBank extends GameObject {

    private int inversion;

    public BloodBank(int x, int y, int inversion, Game game) {
        super(x, y, 1, game);
        letra = "B";
        this.inversion = inversion;
    }

    @Override
    public boolean receiveVampireAttack(int damage) {
        vida = 0;
        return true;
    }

    @Override
    public boolean receiveDraculaAttack() {
        return this.receiveVampireAttack(1);
    }

    @Override
    public void move() {

    }

    @Override
    public void attack() {
        double x = inversion*0.1;
        game.addCoinsToPlayer((int)x);
    }

    public static String AddBloodBank(Game game, int x, int y, int inversion,int monedas) {
        int dimX = game.getLevel().getDimX();
        int dimY = game.getLevel().getDimY();


        if (x >= 0 && x < dimX-1 && y >= 0 && y < dimY && game.isPositionEmpty(x, y)) {
            if (inversion >= 10 || inversion<=monedas)
                game.addObject(new BloodBank(x, y, inversion, game));
            else
                return "not enough coins";

            return null;
        }

        else
            return "Invalid position";


    }
    @Override
    public String getString() { return String.format("%s [%d]", letra, inversion); }

}
