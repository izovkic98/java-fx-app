package main.java.sample.pretrage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Virus;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PretragaVirusaController{

    @FXML
    private TextField nazivVirusaTextField;

    @FXML
    private TableView<Virus> tablicaVirusa;

    @FXML
    private TableColumn<Virus, String> nazivVirusaTableColumn;

    @FXML
    private TableColumn<Virus, SortedSet<Simptom>> simptomiVirusaTableColumn;

    public static List<Virus> listaVirusa = new ArrayList<>();

    public void initialize() {

        nazivVirusaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        simptomiVirusaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("simptomi"));





    }

    public void pretraziViruse() {

        String uneseniNazivVirusa = nazivVirusaTextField.getText();
        List<Virus> filtarVirusa =
                new ArrayList<>();
        for (Virus virus : listaVirusa) {
            if (virus.getNaziv().contains(uneseniNazivVirusa)) {
                filtarVirusa.add(virus);
            }
        }

        ObservableList<Virus> observableListVirusa =
                FXCollections.observableList(filtarVirusa);

        tablicaVirusa.setItems(observableListVirusa);
    }



}
