package main.java.sample.tablice;

import javafx.collections.FXCollections;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TablicaZupanija {

    private static final TablicaZupanija singletonObjekt = new TablicaZupanija();
    private static final String datotekaSaZupanijama = "Zupanije.txt";
    private List<Zupanija> listaZupanija;


    public static TablicaZupanija getInstance() {
        return singletonObjekt;
    }

    public List<Zupanija> getListuZupanija() {
        return listaZupanija;
    }

    public void ucitavanjeZupanija() throws IOException {

        listaZupanija = FXCollections.observableArrayList();
        Path path = Paths.get(datotekaSaZupanijama);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String unos;
            while ((unos = br.readLine()) != null) {
                String[] podaciZupanije = unos.split("\t");

                String naziv = podaciZupanije[0];
                String brStan = podaciZupanije[1];
                String brZarazenih = podaciZupanije[2];

                int brojStanovnika = Integer.parseInt(brStan);
                int brojZarazenih = Integer.parseInt(brZarazenih);

                Zupanija zupanija = new Zupanija(naziv, brojStanovnika, brojZarazenih);
                listaZupanija.add(zupanija);
            }

        }
    }

    public void spremanjeZupanija() throws IOException {

        Path path = Paths.get(datotekaSaZupanijama);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Zupanija item : listaZupanija) {
                bw.write(String.format("%s\t%s\t%s",
                        item.getNaziv(), item.getBrojStanovnika(), item.getBrojZarazenihOsoba()));
                bw.newLine();
            }

        }

    }

}
