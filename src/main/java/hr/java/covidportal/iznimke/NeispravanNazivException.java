package main.java.hr.java.covidportal.iznimke;

/**
 * Baca iznimku kada je naziv neispravnog formata.
 *
 * @author Ivan
 */
public class NeispravanNazivException extends Exception {

    /**
     * Inicijalizira poruku iznimke.
     *
     * @param message Poruka iznimke.
     */
    public NeispravanNazivException(String message) {
        super(message);
    }

    /**
     * Inicijalizira poruku i uzrok iznimke.
     *
     * @param message Poruka iznimke.
     * @param cause Uzrok iznimke.
     */

    public NeispravanNazivException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Inicijalizira uzrok iznimke.
     *
     * @param cause Uzrok iznimke.
     */

    public NeispravanNazivException(Throwable cause) {
        super(cause);
    }
}
