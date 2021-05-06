package main.java.hr.java.covidportal.enumeracija;

/**
 * Sadrži enume o jacini simptoma.
 *
 * @author Ivan
 */

public enum VrijednostSimptoma {

    RIJETKO("RIJETKO"),
    SREDNJE("SREDNJE"),
    ČESTO("ČESTO");

    private String vrijednost;

    /**
     * Inicijalizira podatak o vrijednosti simptoma.
     *
     * @param vrijednost Jacina simptoma.
     */
    VrijednostSimptoma(String vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getVrijednost() {
        return vrijednost;
    }

}
