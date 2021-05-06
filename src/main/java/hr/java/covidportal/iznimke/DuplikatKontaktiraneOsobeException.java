package main.java.hr.java.covidportal.iznimke;

/**
 * Baca iznimku ako ju je zatražen unos dviju istih kontaktiranih osoba.
 *
 * @author Ivan
 */

public class DuplikatKontaktiraneOsobeException extends RuntimeException {


    /**
     * Inicijalizira poruku iznimke.
     *
     * @param message Poruka iznimke.
     */
    public DuplikatKontaktiraneOsobeException(String message) {
        super(message);
    }

    /**
     * Inicijalizira poruku i uzrok iznimke.
     *
     * @param message Poruka iznimke.
     * @param cause Uzrok iznimke.
     */
    public DuplikatKontaktiraneOsobeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Inicijalizira uzrok iznimke.
     *
     * @param cause Uzrok iznimke.
     */
    public DuplikatKontaktiraneOsobeException(Throwable cause) {
        super(cause);
    }
}
