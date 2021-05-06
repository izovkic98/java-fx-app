package main.java.sample.dodavanjeNovihE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.sample.pretrage.PretragaBolestiController;
import main.java.sample.pretrage.PretragaSimptomaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DodavanjeNoveBolestiController {

    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNoveBolestiController.class);

    @FXML
    private TableView<Simptom> tablicaSimptomaTableView;

    @FXML
    private TextField nazivBolestiTextField;


    @FXML
    private TableColumn<Simptom, String> nazivSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, String> vrijednostSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, Integer> sifraSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, String> opisSimptomaTableColumn;

    public void initialize() {

        nazivSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        vrijednostSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("vrijednost"));

        sifraSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("sifra"));

        opisSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("opis"));

        ObservableList<Simptom> observableListSimptoma =
                FXCollections.observableList(PretragaSimptomaController.listaSimptoma);

        tablicaSimptomaTableView.setItems(observableListSimptoma);

        tablicaSimptomaTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    @FXML
    public void dodavanjeBolesti() {

        String nazivText = nazivBolestiTextField.getText();

        ObservableList<Simptom> odabraniSimptomi = tablicaSimptomaTableView.getSelectionModel().getSelectedItems();
        List<Simptom> listaSimptoma = new ArrayList<>(odabraniSimptomi);

        if (!(nazivText.isBlank() || odabraniSimptomi.isEmpty())) {

            PretragaBolestiController.listaBolesti.add(new Bolest(nazivText, listaSimptoma));
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Obavijest");
            a.setContentText("Bolest uspješno unesena");
            a.show();

            logger.info("Uspješno je dodana bolest nova bolest !" );

        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Upozorenje");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();
        }

        nazivBolestiTextField.clear();

    }


}
