package main.java.hr.java.covidportal.model;

/**
 * Pridaje naziv svim elementima u programu.
 *
 * @author Ivan
 */

public abstract class ImenovaniEntitet {
    String naziv;

    /**
     * Inicijalizira nazive elemenata.
     *
     * @param naziv Nazivi bolesti, županija i simptoma.
     */
    public ImenovaniEntitet(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
