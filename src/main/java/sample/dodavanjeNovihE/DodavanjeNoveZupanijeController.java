package main.java.sample.dodavanjeNovihE;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.java.hr.java.covidportal.model.Zupanija;
import main.java.sample.pretrage.PretragaZupanijaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DodavanjeNoveZupanijeController {


    private static final Logger logger = LoggerFactory.getLogger(DodavanjeNoveZupanijeController.class);

    @FXML
    private TextField nazivZupanijeTextField;

    @FXML
    private TextField brStanovnikaTextField;

    @FXML
    private TextField brZarazenihTextField;


    @FXML
    public void dodavanjeNoveZupanije() {


        String naziv = nazivZupanijeTextField.getText();
        int brojStanovnika = Integer.parseInt(brStanovnikaTextField.getText());
        int brojZarazenihOsoba = Integer.parseInt(brZarazenihTextField.getText());

        if (!(naziv.isBlank() || brojStanovnika <= 0)) {
            PretragaZupanijaController.listaZupanija.add(new Zupanija(naziv, brojStanovnika, brojZarazenihOsoba));

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Obavijest");
            a.setContentText("Županija uspješno unesena!");
            a.show();

            logger.info("Uspješno je dodana bolest nova županija !" );


        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Upozorenje");
            a.setContentText("Nisu sva polja unesena! Ponovi.");
            a.show();

        }


        nazivZupanijeTextField.clear();
        brStanovnikaTextField.clear();
        brZarazenihTextField.clear();

    }


}
