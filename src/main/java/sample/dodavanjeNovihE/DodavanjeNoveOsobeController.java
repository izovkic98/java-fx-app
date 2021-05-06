package main.java.sample.dodavanjeNovihE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.*;
import main.java.sample.pretrage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DodavanjeNoveOsobeController {


    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNoveOsobeController.class);

    @FXML
    private TextField imeOsobeTextField;

    @FXML
    private TextField prezimeOsobeTextField;

    @FXML
    private TextField starostOsobeTextField;


    @FXML
    private Label virusUnesenLabel;

    @FXML
    private Label bolestUnesenaLabel;


    @FXML
    private TableView<Osoba> tablicaKontaktOsobaTableView;

    @FXML
    private TableView<Bolest> tablicaBolestTableView;

    @FXML
    private TableView<Virus> tablicaVirusTableView;

    @FXML
    private TableView<Zupanija> tablicaZupanijaTableView;


    @FXML
    private TableColumn<Osoba, String> imeOsobeTableColumn;

    @FXML
    private TableColumn<Osoba, String> prezimeOsobeTableColumn;

    @FXML
    private TableColumn<Bolest, String> nazivBolestiTableColumn;

    @FXML
    private TableColumn<Zupanija, String> nazivZupanijeTableColumn;

    @FXML
    private TableColumn<Virus, String> nazivVirusaTableColumn;


    public void initialize() {


        nazivBolestiTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        nazivZupanijeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        nazivVirusaTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("naziv"));

        imeOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("ime"));

        prezimeOsobeTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("prezime"));

        virusUnesenLabel.setVisible(false);
        bolestUnesenaLabel.setVisible(false);


        ObservableList<Osoba> observableListOsoba =
                FXCollections.observableList(PretragaOsobaController.listaOsoba);

        tablicaKontaktOsobaTableView.setItems(observableListOsoba);

        ObservableList<Bolest> observableListBolesti =
                FXCollections.observableList(PretragaBolestiController.listaBolesti);

        tablicaBolestTableView.setItems(observableListBolesti);

        ObservableList<Zupanija> observableListZupanija =
                FXCollections.observableList(PretragaZupanijaController.listaZupanija);

        tablicaZupanijaTableView.setItems(observableListZupanija);

        ObservableList<Virus> observableListVirusa =
                FXCollections.observableList(PretragaVirusaController.listaVirusa);

        tablicaVirusTableView.setItems(observableListVirusa);


        tablicaZupanijaTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablicaBolestTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablicaVirusTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablicaKontaktOsobaTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }


    @FXML
    public void isTablicaBolestTableViewPressed() {
        tablicaVirusTableView.setVisible(false);
        bolestUnesenaLabel.setVisible(true);
    }

    @FXML
    public void isTablicaVirusaTableViewPressed() {
        tablicaBolestTableView.setVisible(false);
        virusUnesenLabel.setVisible(true);
    }


    @FXML
    public void dodavanjeNoveOsobe() {


        String ime = imeOsobeTextField.getText();
        String prezime = prezimeOsobeTextField.getText();
        int starost = 0;
        try {
            starost = Integer.parseInt(starostOsobeTextField.getText());
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Upozorenje");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();
        }


        Zupanija zupanija = tablicaZupanijaTableView.getSelectionModel().getSelectedItem();

        Bolest bolest = tablicaBolestTableView.getSelectionModel().getSelectedItem();
        Virus virus = tablicaVirusTableView.getSelectionModel().getSelectedItem();

        Bolest odabranaBolestIliVirus;

        ObservableList<Osoba> listaOsoba = tablicaKontaktOsobaTableView.getSelectionModel().getSelectedItems();
        List<Osoba> listaKontaktOsoba = new ArrayList<>(listaOsoba);


        if (!(ime.isBlank() || starost <= 0 || prezime.isBlank() ||
                zupanija == null || (bolest == null && virus == null))) {

            if (bolest == null) {
                odabranaBolestIliVirus = virus;
            } else {
                odabranaBolestIliVirus = bolest;
            }


            if (listaOsoba.isEmpty()) {
                PretragaOsobaController
                        .listaOsoba
                        .add(new Osoba.BuilderOsobe()
                                .setIme(ime)
                                .setPrezime(prezime)
                                .setStarost(starost)
                                .setZarazenBolescu(odabranaBolestIliVirus)
                                .setZupanija(zupanija)
                                .build()
                        );

            } else {

                PretragaOsobaController
                        .listaOsoba
                        .add(new Osoba.BuilderOsobe()
                                .setIme(ime)
                                .setPrezime(prezime)
                                .setStarost(starost)
                                .setZarazenBolescu(odabranaBolestIliVirus)
                                .setZupanija(zupanija)
                                .setKontaktiraneOsobe(listaKontaktOsoba)
                                .build()
                        );
            }

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Obavijest");
            a.setContentText("Osoba uspješno unesena!");
            a.show();

            logger.info("Uspješno je dodana nova osoba !");

        } else {


            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Upozorenje");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();


        }


        imeOsobeTextField.clear();
        prezimeOsobeTextField.clear();
        starostOsobeTextField.clear();
        tablicaBolestTableView.setVisible(true);
        tablicaVirusTableView.setVisible(true);
        bolestUnesenaLabel.setVisible(false);
        virusUnesenLabel.setVisible(false);
        tablicaKontaktOsobaTableView.refresh();

    }


}
