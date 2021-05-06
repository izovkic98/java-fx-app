package main.java.hr.java.covidportal.model;

import java.util.Objects;

/**
 * Predstalja županiju osobe.
 *
 * @author Ivan
 */

public class Zupanija extends ImenovaniEntitet implements Comparable<Zupanija> {


    private Integer brojStanovnika;
    private Integer brojZarazenihOsoba;

    /**
     * Inicijalizira podatke o nazivu i broju stanovnika.
     *
     * @param naziv          Naziv županije.
     * @param brojStanovnika Broj stanovnika županije.
     */
    public Zupanija(String naziv, int brojStanovnika,int brojZarazenih) {
        super(naziv);
        this.brojStanovnika = brojStanovnika;
        this.brojZarazenihOsoba = brojZarazenih;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public int getBrojZarazenihOsoba() {
        return brojZarazenihOsoba;
    }

    public double getPostotak(){
        return ((double)this.brojZarazenihOsoba)/this.brojStanovnika;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int compareTo(Zupanija zupanija) {
        if (this.getNaziv().equals(zupanija.getNaziv())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zupanija zupanija = (Zupanija) o;
        return brojStanovnika == zupanija.brojStanovnika;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brojStanovnika);
    }

}
