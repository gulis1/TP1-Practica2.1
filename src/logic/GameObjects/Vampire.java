package logic.GameObjects;

public class Vampire extends GameObject{

    private static int onBoard = 0;
    private static int total = 0;
    public static final int HP = 5;

    private boolean shouldMove = false;

    public Vampire(int x, int y, int hp) {
        super(x,y,hp);
        letra = "V";
    }

    public static void summonVampire() {

        if (total < game.getLevel().numVampirosLv() && game.nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.nextInt(game.getLevel().getDimY());

            if (game.getObjectInPosition(x, y) == null) {
                game.addObject(new Vampire(x, y, HP));
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

            if (vida <= 0)
                onBoard--;

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


        if (x==0)
            game.end();
    }

    public static int getRemaining( ) {
        return game.getLevel().numVampirosLv() - total;
    }

    public static int getOnBoard( ) {
        return onBoard;
    }


}
