package main.java.hr.java.covidportal.iznimke;


/**
 * Baca iznimku ako su je zatražen unos dviju bolesti s istim simptomima.
 *
 * @author Ivan
 */

public class BolestIstihSimptomaException extends RuntimeException{

    /**
     * Inicijalizira poruku iznimke.
     *
     * @param message Poruka iznimke.
     */
    public BolestIstihSimptomaException(String message) {
        super(message);
    }

    /**
     * Inicijalizira poruku i uzrok iznimke.
     *
     * @param message Poruka iznimke.
     * @param cause Uzrok iznimke.
     */

    public BolestIstihSimptomaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Inicijalizira Uzrok iznimke.
     *
     * @param cause Uzrok iznimke.
     */

    public BolestIstihSimptomaException(Throwable cause) {
        super(cause);
    }
}
