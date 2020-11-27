package logic.GameObjects;

import logic.Game;

import java.util.Random;

public class Vampire extends GameObject{

    private static int onBoard = 0;
    private static int total = 0;
    private static final int HP = 5;
    private boolean shouldMove = false;

    public Vampire(int x, int y, int hp, Game game) {
        super(x,y,hp,game);
        letra = "V";
    }

    public static void resetSpawned() {
        onBoard = 0;
        total = 0;

    }

    public static void summonVampire(Game game) {

        if (total < game.getLevel().numVampirosLv() && game.getRng().nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.getRng().nextInt(game.getLevel().getDimY());

            if (game.getAttackableInPosition(x, y) == null) {
                game.addObject(new Vampire(x, y, HP, game));
                total++;
                onBoard++;
            }
        }
    }

    public void attack() {
        if (isAlive () ) {
            IAttack other = game.getAttackableInPosition(x - 1, y);
            if (other != null )
                other.receiveVampireAttack(1);
        }
    }


    public boolean receiveSlayerAttack(int damage) {

        if (vida > 0) {
            vida -= damage;

            if (vida <= 0) {
                onBoard--;

                if (onBoard == 0 && total == game.getLevel().numVampirosLv()) {
                    game.end();
                    game.setWinnerMsg("Player wins");
                }
            }


            return true;
        }

        else
            return false;
    }

    public void move() {

        if (shouldMove) {
            if (game.isPositionEmpty(x- 1, y))
                x--;
            shouldMove = false;
        }

        else
            shouldMove = true;


        if (x==-1) {
            game.end();
            game.setWinnerMsg("Vampires win!");
        }

    }

    public static int getRemaining(Game game) {
        return game.getLevel().numVampirosLv() - total;
    }

    public static int getOnBoard( ) {
        return onBoard;
    }


}
