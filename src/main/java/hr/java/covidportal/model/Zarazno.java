package main.java.hr.java.covidportal.model;

/**
 * Sadrži metodu za širenje zaraze.
 *
 * @author Ivan
 */

public interface Zarazno {

    /**
     * Prebacuje virus na kontaktirane osobe.
     *
     * @param osoba Kontaktirana osoba.
     */

    void prelazakZarazeNaOsobu(Osoba osoba);

}
