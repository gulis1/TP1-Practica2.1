package logic.GameObjects;

import control.Exceptions.CommandExecuteException;
import control.Exceptions.InvalidPositionException;
import control.Exceptions.NotEnoughCoinsException;
import logic.Game;

import java.util.concurrent.ExecutionException;

public class BloodBank extends GameObject {

    private int inversion;

    // Constructor
    public BloodBank(int x, int y, int inversion, Game game) {
        super(x, y, 1, game);
        letra = "B";
        this.inversion = inversion;
    }

    //Recibe el daño de culaquier tipo de vampiro y lo mata.
    @Override
    public boolean receiveVampireAttack(int damage) {
        vida = 0;
        return true;
    }

    // Se llama al método de arriba
    @Override
    public boolean receiveDraculaAttack() {
        return this.receiveVampireAttack(1);
    }

    // Vacío porque los bloodbanks no se mueven.
    @Override
    public void move() {

    }

    // Se aprovecha el metodo attack para que este hago la funcion de generar el 10% de la inversion del jugador.
    @Override
    public void attack() {
        double x = inversion*0.1;
        game.addCoinsToPlayer((int)x);
    }

    // Se añade un bloodbank de x monedas en la posicion x. Se devuelve un mensaje de error en caso de que ocurra alguno.
    public static String AddBloodBank(Game game, int x, int y, int inversion, int monedas) throws CommandExecuteException {
        int dimX = game.getLevel().getDimX();
        int dimY = game.getLevel().getDimY();


        if (x >= 0 && x < dimX-1 && y >= 0 && y < dimY && game.isPositionEmpty(x, y)) {

            // Hemos puesto que por lo menos se tengan que poner 10 monedas en el bloodbank.
            if (inversion >= 10 && inversion<=monedas)
                game.addObject(new BloodBank(x, y, inversion, game));
            else
                throw new NotEnoughCoinsException("Defender",  inversion ," add bank");

            return null;
        }

        else
            throw new InvalidPositionException(x, y, "bank");


    }

    // Se utiliza este metodo para que en ves de mostrar la vida del BlooBank, muestre la inversion que le han impuesto.
    @Override
    public String getString() { return String.format("%s [%d]", letra, inversion); }

    @Override
    public String serialize() {
        String x= super.serialize();
        x+=";"+Integer.toString(inversion);

        return x;

    }
}
