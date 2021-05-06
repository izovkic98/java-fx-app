package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.hr.java.covidportal.model.*;
import main.java.sample.pretrage.*;
import main.java.sample.tablice.TablicaBolesti;
import main.java.sample.tablice.TablicaSimptoma;
import main.java.sample.tablice.TablicaZupanija;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class PocetniEkranMain extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pocetniEkran.fxml"));
        primaryStage.setTitle("Labos 5");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {

//        Učitavanje simptoma
        Simptom simptom1 =
                new Simptom("Grlobolja", "rijetko", "500", "Grdo boli");
        Simptom simptom2 =
                new Simptom("Svrbez", "cesto", "300", "Jako svrbi");
        Simptom simptom3 =
                new Simptom("Kasalj", "srednje", "150", "Suh kašalj");

        PretragaSimptomaController.listaSimptoma.add(simptom1);
        PretragaSimptomaController.listaSimptoma.add(simptom2);
        PretragaSimptomaController.listaSimptoma.add(simptom3);

//        Učitavanje Bolesti

        List<Simptom> simptomList1 = new ArrayList<>();
        List<Simptom> simptomList2 = new ArrayList<>();
        List<Simptom> simptomList3 = new ArrayList<>();

        simptomList1.add(simptom1);
        simptomList2.add(simptom1);
        simptomList2.add(simptom2);
        simptomList3.add(simptom1);
        simptomList3.add(simptom2);
        simptomList3.add(simptom3);


        Bolest bolest1 =
                new Bolest("Kolera", simptomList1);
        Bolest bolest2 =
                new Bolest("Guba", simptomList2);
        Bolest bolest3 =
                new Bolest("Boginje", simptomList3);


        PretragaBolestiController.listaBolesti.add(bolest1);
        PretragaBolestiController.listaBolesti.add(bolest2);
        PretragaBolestiController.listaBolesti.add(bolest3);

//        Učitavanje županija

        Zupanija zupanija1 =
                new Zupanija("Grad Zagreb", 1000000, 500);
        Zupanija zupanija2 =
                new Zupanija("Osječko-Baranjska", 10450, 300);
        Zupanija zupanija3 =
                new Zupanija("Splitko-Dalmatinska", 300550, 150);
        Zupanija zupanija4 =
                new Zupanija("Vukovarsko-Srijemska", 230022, 244);


        PretragaZupanijaController.listaZupanija.add(zupanija1);
        PretragaZupanijaController.listaZupanija.add(zupanija2);
        PretragaZupanijaController.listaZupanija.add(zupanija3);
        PretragaZupanijaController.listaZupanija.add(zupanija4);

//        Učitavanje virusa

        List<Simptom> simptomLista1 = new ArrayList<>();
        List<Simptom> simptomLista2 = new ArrayList<>();
        List<Simptom> simptomLista3 = new ArrayList<>();


        simptomLista1.add(simptom1);
        simptomLista1.add(simptom2);

        simptomLista2.add(simptom2);
        simptomLista2.add(simptom3);

        simptomLista3.add(simptom2);
        simptomLista3.add(simptom3);

        Virus virus1 =
                new Virus("Covid", simptomLista1);

        Virus virus2 =
                new Virus("SARS", simptomLista2);

        Virus virus3 =
                new Virus("H2N2", simptomLista3);

        PretragaVirusaController.listaVirusa.add(virus1);
        PretragaVirusaController.listaVirusa.add(virus2);
        PretragaVirusaController.listaVirusa.add(virus3);

//        Učitavanje osoba

        List<Osoba> kontaktOsoba1 = new ArrayList<>();
        List<Osoba> kontaktOsoba2 = new ArrayList<>();

        Osoba osoba1 = (new Osoba.BuilderOsobe()
                .setIme("Marko")
                .setPrezime("Štokić")
                .setStarost(44)
                .setZupanija(zupanija1)
                .setZarazenBolescu(bolest1)
                .build());

        kontaktOsoba1.add(osoba1);


        Osoba osoba2 = (new Osoba.BuilderOsobe()
                .setIme("Pero")
                .setPrezime("Perić")
                .setStarost(55)
                .setZupanija(zupanija2)
                .setZarazenBolescu(bolest2)
                .setKontaktiraneOsobe(kontaktOsoba1)
                .build());

        kontaktOsoba2.add(osoba1);
        kontaktOsoba2.add(osoba2);

        Osoba osoba3 = (new Osoba.BuilderOsobe()
                .setIme("Marko")
                .setPrezime("Marić")
                .setStarost(54)
                .setZupanija(zupanija3)
                .setZarazenBolescu(bolest3)
                .setKontaktiraneOsobe(kontaktOsoba2)
                .build());


        PretragaOsobaController.listaOsoba.add(osoba1);
        PretragaOsobaController.listaOsoba.add(osoba2);
        PretragaOsobaController.listaOsoba.add(osoba3);


    }


}
