package control.Exceptions;
// Superclase de todas las excepciones que surgen en el juego
public class GameException extends Exception{
    // Se le añaden al mensaje las señales de ERROR al principio y en cada salto de línea.
    public GameException(String text) {
        super("[ERROR] "+text.replace("\n","\n[ERROR]"));
    }
}
