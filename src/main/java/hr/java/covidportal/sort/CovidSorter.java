package main.java.hr.java.covidportal.sort;

import main.java.hr.java.covidportal.model.Zupanija;
import java.util.Comparator;

/**
 * Sortira zupanije po postotku zarazenih od najvece prema najmanjoj.
 *
 * @author Ivan
 */

public class CovidSorter implements Comparator<Zupanija> {

    /**
     * Usporedjuje dvije zupanije i sortira ih po posototku zarazenih osoba.
     *
     * @param zupanija1 Prva zupanija.
     * @param zupanija2 Druga zupanija.
     * @return Vraca 1 ako je postotak zarazenih u prvoj manji od postotka zarazenih u drugoj.
     * Vraca -1 ako je postotak zarazenih u drugoj manji od postotka zarazenih u prvoj. Inace vraca 0.
     */

    @Override
    public int compare(Zupanija zupanija1, Zupanija zupanija2) {

        double postotakZarazenihPrveZupanije = ((double) zupanija1.getBrojZarazenihOsoba()) / zupanija1.getBrojStanovnika();
        double postotakZarazenihDrugeZupanije = ((double) zupanija2.getBrojZarazenihOsoba()) / zupanija2.getBrojStanovnika();

        if (postotakZarazenihPrveZupanije < postotakZarazenihDrugeZupanije) {
            return 1;
        } else if (postotakZarazenihPrveZupanije > postotakZarazenihDrugeZupanije) {
            return -1;
        } else {
            return 0;
        }

    }
}
