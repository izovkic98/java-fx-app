package main.java.hr.java.covidportal.iznimke;

/**
 * Baca iznimku kada se unesena bolest podudara sa nekom drugom bolesti iz baze podataka.
 *
 * @author Ivan
 */
public class DuplaBolestException extends RuntimeException {

    /**
     * Inicijalizira poruku iznimke.
     *
     * @param message Poruka iznimke.
     */
    public DuplaBolestException(String message) {
        super(message);
    }

    /**
     * Inicijalizira poruku i uzrok iznimke.
     *
     * @param message Poruka iznimke.
     * @param cause Uzrok iznimke.
     */
    public DuplaBolestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Inicijalizira uzrok iznimke.
     *
     * @param cause Uzrok iznimke.
     */
    public DuplaBolestException(Throwable cause) {
        super(cause);
    }
}
