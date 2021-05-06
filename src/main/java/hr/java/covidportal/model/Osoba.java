package main.java.hr.java.covidportal.model;

import java.util.List;
import java.util.Objects;

/**
 * Generira osobu pomoću svih potrebnih elemenata.
 *
 * @author Ivan
 */

public class Osoba implements Comparable {

    private String ime;
    private String prezime;
    private int starost;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private List<Osoba> kontaktiraneOsobe;

    /**
     * Inicijalizira osobu.
     *
     * @param builderOsobe Kreira objekt osobe.
     */
    private Osoba(BuilderOsobe builderOsobe) {
        this.ime = builderOsobe.ime;
        this.prezime = builderOsobe.prezime;
        this.starost = builderOsobe.starost;
        this.zupanija = builderOsobe.zupanija;
        this.zarazenBolescu = builderOsobe.zarazenBolescu;
        this.kontaktiraneOsobe = builderOsobe.kontaktiraneOsobe;

        if(this.kontaktiraneOsobe != null)
        for (Osoba osoba : kontaktiraneOsobe) {
            if (zarazenBolescu instanceof Virus virus) {
                virus.prelazakZarazeNaOsobu(osoba);
            }
        }

    }


    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getStarost() {
        return starost;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }


    public List<Osoba> getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }


    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }


    /**
     * Služi za usporedbu osoba.
     *
     * @param o Objekt za usporedbu.
     * @return Rezultat usporedbe.
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * Pomaže klasi Osoba u generiranju osobe.
     *
     * @author Ivan
     */
    public static class BuilderOsobe {

        private String ime;
        private String prezime;
        private int starost;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private List<Osoba> kontaktiraneOsobe;

        /**
         * Inicijalizira builder osobe.
         */
        public BuilderOsobe() {
        }

        public BuilderOsobe setIme(String ime) {
            this.ime = ime;
            return this;
        }

        public BuilderOsobe setPrezime(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public BuilderOsobe setStarost(int starost) {
            this.starost = starost;
            return this;
        }

        public BuilderOsobe setZupanija(Zupanija zupanija) {
            this.zupanija = zupanija;
            return this;
        }

        public BuilderOsobe setZarazenBolescu(Bolest zarazenBolescu) {
            this.zarazenBolescu = zarazenBolescu;
            return this;
        }

        public BuilderOsobe setKontaktiraneOsobe(List<Osoba> kontaktiraneOsobe) {
            this.kontaktiraneOsobe = kontaktiraneOsobe;
            return this;
        }

        /**
         * Koristi se za kreiranje (buildanje) osobe.
         *
         * @return Vraća generiranu osobu.
         */

        public Osoba build() {
            Osoba osoba = new Osoba(this);
            return osoba;
        }
    }

    @Override
    public String toString() {
        return  ime + " " + prezime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return starost == osoba.starost &&
                Objects.equals(ime, osoba.ime) &&
                Objects.equals(prezime, osoba.prezime) &&
                Objects.equals(zupanija, osoba.zupanija) &&
                Objects.equals(zarazenBolescu, osoba.zarazenBolescu) &&
                Objects.equals(kontaktiraneOsobe, osoba.kontaktiraneOsobe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, starost, zupanija, zarazenBolescu, kontaktiraneOsobe);
    }
}
