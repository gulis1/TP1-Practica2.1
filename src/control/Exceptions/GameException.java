package control.Exceptions;
// Superclase de todas las excepciones que surgen en el juego
public class GameException extends Exception{
    public GameException(String text) {
        super(text);
    }
}
