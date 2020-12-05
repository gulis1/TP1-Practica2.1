package logic.GameObjects;

import logic.Game;

import java.lang.annotation.Documented;

public class Dracula extends Vampire {

    private static boolean draculaAlive = false;

    public Dracula(int x, int y, int hp, Game game) {
        super(x,y,hp,game);
        letra = "D";
    }

    public static boolean isDraculaAlive() {
        return draculaAlive;
    }



    //Por que no nos deja poner @override?
    public static void summon(Game game) {

        if (!draculaAlive && total < game.getLevel().numVampirosLv() && game.getRng().nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.getRng().nextInt(game.getLevel().getDimY());

            if (game.getAttackableInPosition(x, y) == null) {
                draculaAlive = true;
                game.addObject(new Dracula(x, y, HP, game));
                total++;
                onBoard++;
            }
        }
    }

    @Override
    public void attack() {
        if (isAlive() ) {
            IAttack other = game.getAttackableInPosition(x - 1, y);
            if (other != null )
                other.receiveDraculaAttack();
        }
    }


    public boolean receiveSlayerAttack(int damage) {
        boolean x = super.receiveSlayerAttack(1);

        if (!isAlive())
            draculaAlive = false;

        return x;
    }

    @Override
    public boolean receiveGarlicPush() {
        boolean x = super.receiveGarlicPush();

        if (!isAlive())
            draculaAlive  = false;

        return x;
    }

    @Override
    public boolean receiveLightFlash() { return false; }


}
