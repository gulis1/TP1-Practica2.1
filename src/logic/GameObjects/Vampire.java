package logic.GameObjects;

import logic.Game;

import java.util.Random;

public class Vampire extends GameObject {

    protected static int onBoard = 0;
    protected static int total = 0;
    protected static final int HP = 5;
    protected boolean shouldMove = false;

    public Vampire(int x, int y, int hp, Game game) {
        super(x,y,hp,game);
        letra = "V";
    }

    // Resetea el numero de vampiros que han salido.
    public static void resetSpawned() {
        onBoard = 0;
        total = 0;
    }

    // Hace aparecer un vampiro en el game (Si el Random decide que debe salir)
    public static void summon(Game game) {

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

    //comprueba si el vampiro esta vivo, luego se verifica si hay en objeto en la posicion proxima al vampiro (x-1) y si hay un slayer(object!=null) recive le daño.
    public void attack() {
        if (isAlive () ) {
            IAttack other = game.getAttackableInPosition(x - 1, y);
            if (other != null )
                other.receiveVampireAttack(1);
        }
    }

    // Recibe un ataque del slayer y mata al vampiro si su vida llega a 0. Aqui tambien se realiza la comprobación de que hayan muerto todos los vampiros
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

    // Mueve el vampiro una casilla si esta se encuentra vacía. También se realiza comprueba si algún vampiro ha llegado al final del tablero.
    @Override
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

    // Devuelve la cantidad de vampiros que quedan por salir.
    public static int getRemaining(Game game) {
        return game.getLevel().numVampirosLv() - total;
    }

    // Devuelve la cantidad de vampiros que hay en el tablero.
    public static int getOnBoard( ) {
        return onBoard;
    }


}
