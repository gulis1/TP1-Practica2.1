package logic.GameObjects;

import logic.Game;


public class Dracula extends Vampire {

    private static boolean draculaAlive = false;
    private String pito;

    public Dracula(int x, int y, int hp, Game game) {
        super(x, y, hp, game);
        draculaAlive = true;
        letra = "D";
    }

    public static boolean isDraculaAlive() {
        return draculaAlive;
    }

    // Hace aparecer un dracula (Si toca en este turno y no hay otro vivo)
    public static void summon(Game game) {

        if (!draculaAlive && total < game.getLevel().numVampirosLv() && game.getRng().nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.getRng().nextInt(game.getLevel().getDimY());

            if (game.getAttackableInPosition(x, y) == null) {

                game.addObject(new Dracula(x, y, HP, game));
                total++;
                onBoard++;
            }
        }
    }

    /// Comprueba si el vampiro esta vivo, luego se verifica si hay en objeto en la posicion proxima al vampiro (x-1) y si hay un slayer(object!=null) lo mata.
    @Override
    public void attack() {
        if (isAlive()) {
            IAttack other = game.getAttackableInPosition(x - 1, y);
            if (other != null)
                other.receiveDraculaAttack();
        }
    }

    // Recibe un ataque del slayer y mata a Dracula, actualiza la variable booleana que comprueba si hay un dracula en la partida.

    public boolean receiveSlayerAttack(int damage) {
        boolean x = super.receiveSlayerAttack(1);

        if (!isAlive())
            draculaAlive = false;

        return x;
    }

    // Recibe el garlicPush, si muere por el actualiza el draculaAlive a false.
    @Override
    public boolean receiveGarlicPush() {
        boolean x = super.receiveGarlicPush();

        if (!isAlive())
            draculaAlive = false;

        return x;
    }

    // Resetea el draculaAlive a falso.
    public static void resetDraculaAlive(){draculaAlive=false;}

    // A Dracula no le afecta el LightFlash.
    @Override
    public boolean receiveLightFlash() {
        return false;
    }


}
