package main.java.sample.tablice;

import javafx.collections.FXCollections;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Zupanija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TablicaSimptoma {

    private static final TablicaSimptoma singletonObjekt = new TablicaSimptoma();
    private static final String datotekaSaSimptomima = "Simptomi.txt";
    private List<Simptom> listaSimptoma;


    public static TablicaSimptoma getInstance() {
        return singletonObjekt;
    }

    public List<Simptom> getListuSimptoma() {
        return listaSimptoma;
    }

    public void setListaSimptoma(List<Simptom> listaSimptoma) {
        this.listaSimptoma = listaSimptoma;
    }

    public void ucitavanjeSimptoma() throws IOException {

        listaSimptoma = FXCollections.observableArrayList();
        Path path = Paths.get(datotekaSaSimptomima);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String unos;
            while ((unos = br.readLine()) != null) {
                String[] podaciSimptoma = unos.split("\t");

                String naziv = podaciSimptoma[0];
                String vrijednost = podaciSimptoma[1];
                String sifra = podaciSimptoma[2];
                String opis = podaciSimptoma[3];


                Simptom simptom = new Simptom(naziv, vrijednost, sifra,opis);
                listaSimptoma.add(simptom);
            }

        }
    }

    public void spremanjeSimptoma() throws IOException {

        Path path = Paths.get(datotekaSaSimptomima);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Simptom item : listaSimptoma) {
                bw.write(String.format("%s\t%s\t%s\t%s",
                        item.getNaziv(), item.getVrijednost(), item.getSifra(), item.getOpis()));
                bw.newLine();
            }

        }

    }

}
