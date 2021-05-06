package main.java.sample.dodavanjeNovihE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Bolest;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.hr.java.covidportal.model.Virus;
import main.java.sample.pretrage.PretragaBolestiController;
import main.java.sample.pretrage.PretragaSimptomaController;
import main.java.sample.pretrage.PretragaVirusaController;
import main.java.sample.tablice.TablicaBolesti;
import main.java.sample.tablice.TablicaSimptoma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


public class DodavanjeNovogVirusaController {


    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNovogVirusaController.class);


    @FXML
    private TableView<Simptom> tablicaSimptomaTableView;

    @FXML
    private TextField nazivVirusaTextField;


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
    public void dodavanjeVirusa() {

        String nazivText = nazivVirusaTextField.getText();

        ObservableList<Simptom> odabraniSimptomi = tablicaSimptomaTableView.getSelectionModel().getSelectedItems();
        List<Simptom> listaSimptoma = new ArrayList<>(odabraniSimptomi);

        if (!(nazivText.isBlank() || odabraniSimptomi.isEmpty())) {
            PretragaVirusaController.listaVirusa.add(new Virus(nazivText, listaSimptoma));
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Obavijest");
            a.setContentText("Virus uspješno unesen");
            a.show();

            logger.info("Uspješno je dodan novi virus !" );

        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Upozorenje");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();
        }

        nazivVirusaTextField.clear();

    }


}
