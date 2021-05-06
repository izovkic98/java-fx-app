package main.java.sample.pretrage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.sample.PocetniEkranMain;
import main.java.sample.dodavanjeNovihE.DodavanjeNovogSimptomaController;
import main.java.sample.tablice.TablicaSimptoma;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PretragaSimptomaController {


    @FXML
    private TextField nazivSimptomaTextField;

    @FXML
    private TableView<Simptom> tablicaSimptoma;

    @FXML
    private TableColumn<Simptom, String> nazivSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, String> vrijednostSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, Integer> sifraSimptomaTableColumn;

    @FXML
    private TableColumn<Simptom, String> opisSimptomaTableColumn;

    public static List<Simptom> listaSimptoma = new ArrayList<>();

    public void initialize() {

        nazivSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        vrijednostSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("vrijednost"));

        sifraSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("sifra"));

        opisSimptomaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("opis"));

        tablicaSimptoma.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }

    public void pretraziSimptome() {

        String uneseniNazivSimptomaIliOpis = nazivSimptomaTextField.getText();
        List<Simptom> filtarSimptoma =
                new ArrayList<>();

        List<String> pohranaOpisa = new ArrayList<>();

        for (Simptom simptom : listaSimptoma) {
            if (simptom.getNaziv().contains(uneseniNazivSimptomaIliOpis) || simptom.getOpis().contains(uneseniNazivSimptomaIliOpis)) {
                filtarSimptoma.add(simptom);

            }
        }


//        Spremanje u listu (DB)

        for (Simptom simptom : filtarSimptoma) {
            pohranaOpisa.add(simptom.getOpis());
        }

//        ====


        ObservableList<Simptom> observableListSimptoma =
                FXCollections.observableList(filtarSimptoma);

        tablicaSimptoma.setItems(observableListSimptoma);
    }


    @FXML
    public void prikaziEkranZaPromjenu() throws IOException {


//        3.zad ne radi

        Simptom simptom = tablicaSimptoma.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dodavanjeNovogSimptoma.fxml"));


//        DodavanjeNovogSimptomaController scene2Controller = loader.getController();
//        scene2Controller.promijeniSimtpom(simptom);
//
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();



    }

}
