package main.java.hr.java.covidportal.model;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

/**
 * Predstavlja bolest osobe.
 *
 * @author Ivan
 */

public class Bolest extends ImenovaniEntitet implements Comparable<Bolest>{

    private List<Simptom> simptomi;
    private String simptoma;

    /**
     * Inicijalizira podatke o nazivu i simptomima bolesti.
     *
     * @param naziv Naziv bolesti.
     * @param simptom Popis simptoma.
     */
    public Bolest(String naziv, List<Simptom> simptom) {
        super(naziv);
        this.simptomi = simptom;

    }

    public Bolest(String naziv, String simptoma) {
        super(naziv);
        this.simptoma = simptoma;
    }

    public String getSimptoma() {
        return simptoma;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Simptom>  getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(List<Simptom> simptomi) {
        this.simptomi = simptomi;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int compareTo(Bolest bolest) {
        if (this.getNaziv().equals(bolest.getNaziv())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bolest bolest = (Bolest) o;
        return Objects.equals(simptomi, bolest.simptomi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simptomi);
    }
}
