package main.java.sample.pretrage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Zupanija;
import main.java.sample.dodavanjeNovihE.DodavanjeNoveZupanijeController;
import main.java.sample.tablice.TablicaZupanija;

import java.util.*;

public class PretragaZupanijaController {


    @FXML
    private TextField nazivZupanijeTextField;


    @FXML
    public TableView<Zupanija> tablicaZupanija;

    @FXML
    private TableColumn<Zupanija, String> nazivZupanijeTableColumn;

    @FXML
    private TableColumn<Zupanija, Integer> brStanovnikaTableColumn;

    @FXML
    private TableColumn<Zupanija, Integer> brZarazenihTableColumn;

    public static List<Zupanija> listaZupanija = new ArrayList<>();

    public void initialize() {

        nazivZupanijeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        brStanovnikaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("brojStanovnika"));

        brZarazenihTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("brojZarazenihOsoba"));





    }

    @FXML
    public void pretraziZupanije() {


        String uneseniNazivZupanije = nazivZupanijeTextField.getText();
        List<Zupanija> filtarZupanija =
                new ArrayList<>();
        for (Zupanija zupanija : listaZupanija) {
            if (zupanija.getNaziv().contains(uneseniNazivZupanije)) {
                filtarZupanija.add(zupanija);
            }
        }

        ObservableList<Zupanija> observableListZupanija =
                FXCollections.observableList(filtarZupanija);


        tablicaZupanija.setItems(observableListZupanija);
    }


}
