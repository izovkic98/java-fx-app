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

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class PretragaBolestiController {


    @FXML
    private TextField nazivBolestiTextField;

    @FXML
    private TableView<Bolest> tablicaBolesti;

    @FXML
    private TableColumn<Bolest, String> nazivBolestiTableColumn;

    @FXML
    private TableColumn<Bolest, SortedSet<Simptom>> simptomiTableColumn;

    public static List<Bolest> listaBolesti = new ArrayList<>();


    public void initialize() {

        nazivBolestiTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        simptomiTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("simptomi"));

    }

    @FXML
    public void pretraziBolesti() {

        String uneseniNazivBolesti = nazivBolestiTextField.getText();
        List<Bolest> filtarBolesti =
                new ArrayList<>();
        for (Bolest bolest : listaBolesti) {
            if (bolest.getNaziv().contains(uneseniNazivBolesti)) {
                filtarBolesti.add(bolest);
            }
        }

        ObservableList<Bolest> observableListBolesti =
                FXCollections.observableList(filtarBolesti);

        tablicaBolesti.setItems(observableListBolesti);
    }


}
