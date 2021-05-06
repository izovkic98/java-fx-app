package main.java.sample.dodavanjeNovihE;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.hr.java.covidportal.model.Simptom;
import main.java.sample.pretrage.PretragaSimptomaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DodavanjeNovogSimptomaController {

    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNovogSimptomaController.class);


    @FXML
    private TextField nazivSimptomaTextField;

    @FXML
    private TextField vrijedonstSimptomaTextField;

    @FXML
    private TextField sifraSimptomaTextField;

    @FXML
    private TextField opisSimptomaTextField;

    @FXML
    public Button gumbZaSpremanje;


    @FXML
    public void dodavanjeNovogSimptoma() {


        String naziv = nazivSimptomaTextField.getText();
        String vrijednost = vrijedonstSimptomaTextField.getText();
        String sifra = sifraSimptomaTextField.getText();
        String opis = opisSimptomaTextField.getText();


        if (!(naziv.isBlank() || vrijednost.isBlank() || sifra.isBlank() || opis.isBlank())) {

            PretragaSimptomaController.listaSimptoma.add(new Simptom(naziv, vrijednost, sifra, opis));
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Obavijest");
            a.setContentText("Simptom uspješno unesen");
            a.show();

//            DODATNI ZADATAK 2.

            nazivSimptomaTextField.setStyle("-fx-background-color: WHITE");
            vrijedonstSimptomaTextField.setStyle("-fx-background-color: WHITE");
            sifraSimptomaTextField.setStyle("-fx-background-color: WHITE");
            opisSimptomaTextField.setStyle("-fx-background-color: WHITE");
            logger.info("Uspješno je dodan novi simptom !");


        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Obavijest");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();

//            DODATNI ZADATAK 2.

            if (naziv.isBlank()) {
                nazivSimptomaTextField.setStyle("-fx-background-color: RED");
            }

            if (vrijednost.isBlank()) {
                vrijedonstSimptomaTextField.setStyle("-fx-background-color: RED");
            }

            if (sifra.isBlank()) {
                sifraSimptomaTextField.setStyle("-fx-background-color: RED");
            }

            if (opis.isBlank()) {
                opisSimptomaTextField.setStyle("-fx-background-color: RED");
            }
        }

        nazivSimptomaTextField.clear();
        opisSimptomaTextField.clear();
        sifraSimptomaTextField.clear();
        vrijedonstSimptomaTextField.clear();

    }

//    3 ZADATAK

    public void promijeniSimtpom(Simptom simptom) {

        nazivSimptomaTextField.setText(simptom.getNaziv());
        vrijedonstSimptomaTextField.setText(simptom.getVrijednost());
        sifraSimptomaTextField.setText(simptom.getSifra());
        opisSimptomaTextField.setText(simptom.getOpis());


}
    public void azurirajSimptom(Simptom simptom) {

        simptom.setNaziv(nazivSimptomaTextField.getText());
        simptom.setNaziv(vrijedonstSimptomaTextField.getText());
        simptom.setNaziv(sifraSimptomaTextField.getText());
        simptom.setNaziv(opisSimptomaTextField.getText());

        PretragaSimptomaController.listaSimptoma.add(simptom);

    }


}
