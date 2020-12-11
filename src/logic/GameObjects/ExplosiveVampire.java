package logic.GameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire {

    public ExplosiveVampire(int x, int y, int hp, Game game) {
        super(x, y, hp, game);
        letra="EV";
    }

    //hay que buscar una forma de no repetir todo esto.
    public static void summon(Game game) {

        if ( total < game.getLevel().numVampirosLv() && game.getRng().nextDouble() < game.getLevel().getFrecuencia()) {

            int x = game.getLevel().getDimX() - 1;
            int y = game.getRng().nextInt(game.getLevel().getDimY());

            if (game.getAttackableInPosition(x, y) == null) {

                game.addObject(new ExplosiveVampire(x, y, HP, game));
                total++;
                onBoard++;
            }
        }
    }

    @Override
    public boolean receiveSlayerAttack(int damage) {

        boolean s=super.receiveSlayerAttack(damage);

        int posF[] = { 1,1,0,-1,-1,-1,0,1 };
        int posC[] = { 0,1,1,1,0,-1,-1,-1 };

        if(!isAlive()) {
            for(int i=0;i<posF.length;i++) {
                // Aunque la posicion no exista, getAttackableInPosition devolverá null
                IAttack other = game.getAttackableInPosition(this.x + posC[i],this.y + posF[i]);

                if(other != null )
                    other. receiveSlayerAttack(1);

            }
        }

        return s;
    }



}
