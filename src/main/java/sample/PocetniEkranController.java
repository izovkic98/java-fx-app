package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import main.java.sample.pretrage.PretragaSimptomaController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetniEkranController implements Initializable {




    @FXML
    public void prikaziEkranZaPretraguZupanija() throws IOException {
        Parent pretragaZupanijaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(pretragaZupanijaScene);
    }

    @FXML
    public void prikaziEkranZaDodavanjeZupanija() throws IOException {
        Parent dodavanjeZupanijaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNoveZupanije.fxml"));
        Scene dodavanjeZupanijaScene = new Scene(dodavanjeZupanijaFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(dodavanjeZupanijaScene);
    }

    @FXML
    public void prikaziEkranZaDodavanjeSimptoma() throws IOException {
        Parent dodavanjeSimptomaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNovogSimptoma.fxml"));
        Scene dodavanjeSimptomaScene = new Scene(dodavanjeSimptomaFrame, 600, 400);
        dodavanjeSimptomaScene.getStylesheets().add(
                getClass().getClassLoader().getResource("error.css").toString());

        PocetniEkranMain.mainStage.setScene(dodavanjeSimptomaScene);
    }

    @FXML
    public void prikaziEkranZaDodavanjeBolesti() throws IOException {
        Parent dodavanjeBolestiFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNoveBolesti.fxml"));
        Scene dodavanjeBolestiScene = new Scene(dodavanjeBolestiFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(dodavanjeBolestiScene);
    }

    @FXML
    public void prikaziEkranZaPretraguBolesti() throws IOException {
        Parent pretragaBolestiFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(pretragaBolestiScene);
    }

    @FXML
    public void prikaziEkranZaPretraguVirusa() throws IOException {
        Parent pretragaVirusaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(pretragaVirusaScene);
    }

    @FXML
    public void prikaziEkranZaDodavanjeVirusa() throws IOException {
        Parent dodavanjeVirusaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNovogVirusa.fxml"));
        Scene dodavanjeVirusaScene = new Scene(dodavanjeVirusaFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(dodavanjeVirusaScene);
    }


    @FXML
    public void prikaziEkranZaPretraguSimptomi() throws IOException {
        Parent pretragaSimptomaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);
        PocetniEkranMain.mainStage.setScene(pretragaSimptomaScene);
    }

    @FXML
    public void prikaziEkranZaPretraguOsoba() throws IOException {
        Parent pretragaOsobaFrame = FXMLLoader.load(getClass().getClassLoader().getResource("pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 883, 544);
        PocetniEkranMain.mainStage.setScene(pretragaOsobaScene);
    }

    @FXML
    public void prikaziEkranZaDodavanjeOsobe() throws IOException {
        Parent dodavanjeOsobeFrame = FXMLLoader.load(getClass().getClassLoader().getResource("dodavanjeNovihOsoba.fxml"));
        Scene dodavanjeOsobeScene = new Scene(dodavanjeOsobeFrame, 740, 530);
        PocetniEkranMain.mainStage.setScene(dodavanjeOsobeScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}