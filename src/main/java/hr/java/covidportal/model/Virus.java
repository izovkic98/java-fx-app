package main.java.hr.java.covidportal.model;

import java.util.List;
import java.util.SortedSet;

/**
 * Predstavlja Virus osobe.
 *
 * @author Ivan
 */

public class Virus extends Bolest implements Zarazno, Comparable<Bolest> {

    /**
     * Inicijalizira podatke o virusu.
     *
     * @param naziv Ime virusa.
     * @param simptom Popis simptoma virusa.
     */
    public Virus(String naziv, List<Simptom> simptom) {
        super(naziv, simptom);
    }

    /**
     * Prebacuje virus na kontaktirane osobe.
     *
     * @param osoba Kontaktirana osoba.
     */

    public void prelazakZarazeNaOsobu(Osoba osoba) {
        osoba.setZarazenBolescu(this);
    }

}
