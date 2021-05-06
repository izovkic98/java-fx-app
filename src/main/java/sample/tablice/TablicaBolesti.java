package main.java.sample.tablice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Simptom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TablicaBolesti {

    private static final TablicaBolesti singletonObjekt = new TablicaBolesti();
    private static final String datotekaSaBolestima = "Bolesti.txt";
    public ObservableList<Bolest> listaBolesti;


    public TablicaBolesti() {
        listaBolesti = FXCollections.observableArrayList();
    }

    public static TablicaBolesti getInstance() {
        return singletonObjekt;
    }

    public List<Bolest> getListuBolesti() {
        return listaBolesti;
    }


    public void setListaBolesti(ObservableList<Bolest> listaBolesti) {
        this.listaBolesti = listaBolesti;
    }


    public void addBolest(Bolest bolest) {
        listaBolesti.add(bolest);
    }

    public void ucitavanjeBolesti() throws IOException {

        listaBolesti = FXCollections.observableArrayList();
        Path path = Paths.get(datotekaSaBolestima);

        SortedSet<Simptom> simptomSortedSet = new TreeSet<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String unos;
            while ((unos = br.readLine()) != null) {
                String[] podaciBolesti = unos.split("\t");

                String naziv = podaciBolesti[0];
//                Simptom simptomi =  podaciBolesti[1];
//
//                Bolest bolest = new Bolest(naziv, simptomi);
//                listaBolesti.add(bolest);
            }

        }

    }

    public void spremanjeBolesti() throws IOException {

        Path path = Paths.get(datotekaSaBolestima);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Bolest bolest : listaBolesti) {
                bw.write(String.format("%s\t%s",
                        bolest.getNaziv(), bolest.getSimptomi()));
                bw.newLine();
            }

        }

    }



}
