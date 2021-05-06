package main.java.hr.java.covidportal.model;

import java.util.Objects;

/**
 * Predstavlja simptom osobe.
 *
 * @author Ivan
 */


public class Simptom extends ImenovaniEntitet implements Comparable<Simptom> {

    private String vrijednost;
    private String sifra;
    private String opis;

    /**
     * Inicijalizira podatke o simptomu.
     *
     * @param naziv Ime simptoma.
     * @param vrijednost Frekventnost simptoma.
     */
    public Simptom(String naziv, String vrijednost,String sifra, String opis) {
        super(naziv);
        this.vrijednost = vrijednost;
        this.sifra = sifra;
        this.opis=opis;
    }




    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return  naziv;
    }


    /**
     * Služi za usporedbu simptoma.
     *
     * @param simptom Objekt za usporedbu
     * @return Vraca 0 ako su imena simptoma jednaka, inace 1.
     */

    @Override
    public int compareTo(Simptom simptom) {
        if (this.getNaziv().equals(simptom.getNaziv())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simptom simptom = (Simptom) o;
        return Objects.equals(vrijednost, simptom.vrijednost) && Objects.equals(sifra, simptom.sifra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vrijednost, sifra);
    }
}
