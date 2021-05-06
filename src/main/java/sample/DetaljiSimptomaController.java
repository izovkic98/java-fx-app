package main.java.sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.hr.java.covidportal.model.Simptom;


public class DetaljiSimptomaController {


    @FXML
    private TableView<Simptom> tablicaDetaljaSimptoma;

    @FXML
    private TableColumn<Simptom,String> sifraTableColumn;

    @FXML
    private TableColumn<Simptom, String> vrijednostTableColumn;

    @FXML
    private TableColumn<Simptom, String> opisTableColumn;





    public void initialize() {

        sifraTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("sifra"));

        vrijednostTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("vrijednost"));

        opisTableColumn.setCellValueFactory(
                new PropertyValueFactory<>("opis")
        );

    }

}
