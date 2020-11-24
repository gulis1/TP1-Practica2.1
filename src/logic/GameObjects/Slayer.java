package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject {

    public static final int HP = 3;

    public Slayer(int x, int y, int vida, Game game) {
        super(x,y,vida,game);
        letra = "S";
    }

    public void attack() {
        IAttack other = null;
        int i = x+1;

        if (isAlive () ) {

            while(other == null && i < game.getLevel().getDimX()) {

                other = game.getAttackableInPosition(i, y);
                if (other != null)
                    other.receiveSlayerAttack(1);

                i++;

            }
        }
    }

    public boolean receiveVampireAttack(int damage) {

        if (vida > 0) {
            vida -= damage;
            return true;
        }

        else
            return false;
    }

    public static boolean AddSlayer(Game game, int x, int y, int monedas) {
        int dimX = game.getLevel().getDimX();
        int dimY = game.getLevel().getDimY();

        if (monedas >= 50 && x>= 0 && x < dimX && y>=0 && y<dimY &&game.isPositionEmpty(x,y))  {
            game.addObject(new Slayer(x,y,3, game));
            return true;
        }

        else
            return false;

    }

    public void move(){

    };
}
