package main.java.sample.pretrage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Zupanija;
import main.java.hr.java.covidportal.model.Osoba;


import java.util.*;

public class PretragaOsobaController {


    @FXML
    private TextField imeIPrezimeOsobeTextField;

    @FXML
    private TableView<Osoba> tablicaOsoba;

    @FXML
    private TableColumn<Osoba, String> imeOsobeTableColumn;

    @FXML
    private TableColumn<Osoba, String> prezimeOsobeTableColumn;

    @FXML
    private TableColumn<Osoba, Integer> starostOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, Zupanija> zupanijaOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, Bolest> bolestOsobeTableColumn;
    @FXML
    private TableColumn<Osoba, List<Osoba>> kontaktOsobeTableColumn;

    public static List<Osoba> listaOsoba = new ArrayList<>();

    public void initialize() {

        imeOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("ime"));

        prezimeOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("prezime"));

        starostOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("starost"));

        zupanijaOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("zupanija"));

        bolestOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("zarazenBolescu"));

        kontaktOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("kontaktiraneOsobe"));

    }

    public void pretraziOsobe() {

        String unesenoImeIPrezimeOsobe = imeIPrezimeOsobeTextField.getText();
        List<Osoba> filtarOsoba =
                new ArrayList<>();
        for (Osoba osoba : listaOsoba) {
            if (osoba.getIme().contains(unesenoImeIPrezimeOsobe) || osoba.getPrezime().contains(unesenoImeIPrezimeOsobe)) {
                filtarOsoba.add(osoba);
            }
        }

        ObservableList<Osoba> observableListOsoba =
                FXCollections.observableList(filtarOsoba);

        tablicaOsoba.setItems(observableListOsoba);
    }



}
