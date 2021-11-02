package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Controller {
    @FXML
    private Label labelInicio;

    @FXML
    private Label labelFinal;

    @FXML
    private Label labelName;

    @FXML
    private TableView<Record> tableA;

    @FXML
    private Button btnBuscar;

    private final ObservableList<Record> dataList = FXCollections.observableArrayList();

    public class Record {
        //Assume each record have 6 elements, all String

        private SimpleStringProperty f1, f2, f3, f4, f5, f6;

        public String getF1() {
            return f1.get();
        }

        public String getF2() {
            return f2.get();
        }

        public String getF3() {
            return f3.get();
        }

        public String getF4() {
            return f4.get();
        }

        public String getF5() {
            return f5.get();
        }

        public String getF6() {
            return f6.get();
        }

        Record(String f1, String f2, String f3, String f4,
               String f5, String f6) {
            this.f1 = new SimpleStringProperty(f1);
            this.f2 = new SimpleStringProperty(f2);
            this.f3 = new SimpleStringProperty(f3);
            this.f4 = new SimpleStringProperty(f4);
            this.f5 = new SimpleStringProperty(f5);
            this.f6 = new SimpleStringProperty(f6);
        }
        Record(String f1,String f2) {
            this.f1 = new SimpleStringProperty(f1);
            this.f1 = new SimpleStringProperty(f2);
        }
    }

    public void initialize() {
        TableColumn columnF1 = new TableColumn("Nombre Completo");
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("f1"));
        columnF1.setMaxWidth(240);
        columnF1.setMinWidth(240);

        TableColumn columnF2 = new TableColumn("Hora de Entrada");
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("f2"));

        TableColumn columnF3 = new TableColumn("Hora de Salida");
        columnF3.setCellValueFactory(
                new PropertyValueFactory<>("f3"));

        TableColumn columnF4 = new TableColumn("Duracion");
        columnF4.setCellValueFactory(
                new PropertyValueFactory<>("f4"));
        columnF4.setMaxWidth(80);
        columnF4.setMinWidth(80);

        TableColumn columnF5 = new TableColumn("userPrincipalName");
        columnF5.setCellValueFactory(
                new PropertyValueFactory<>("f5"));
        columnF5.setMaxWidth(240);
        columnF5.setMinWidth(240);

        TableColumn columnF6 = new TableColumn("Rol");
        columnF6.setCellValueFactory(
                new PropertyValueFactory<>("f6"));

        tableA.setItems(dataList);
        tableA.getColumns().addAll(columnF1,columnF2,columnF3,columnF4,columnF5,columnF6);
        tableA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    public void pressButton(ActionEvent event) {
    if( tableA.getItems() != null)
          {
             limpiarTable();
          }
          FileChooser d = new FileChooser();
          FileChooser.ExtensionFilter f1 = new FileChooser.ExtensionFilter("Archivos CSV", "*.csv");
          d.getExtensionFilters().add(f1);
          File archivo = d.showOpenDialog(null);
          File file = new File(archivo.toString());
          BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file.toString()));
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                   llenarTabla(i, line);
                    i++;
                }
            }catch (Exception ex){            }
        }
    private void limpiarTable(){
        tableA.getItems().clear();
        labelName.setText("");
        labelInicio.setText("");
        labelFinal.setText("");
        labelName.setVisible(false);
        labelInicio.setVisible(false);
        labelFinal.setVisible(false);
    }
    private void llenarTabla(int i, String line) {
        if (i > 7) {
            String[] fields = line.split("\t");
            Record record = new Record(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
            dataList.add(record);
        } else {
            if (i == 2) {
                String[] fields = line.split("\t");
                Record record = new Record(fields[0], fields[1]);
                labelName.setText(fields[1]);
                labelName.setVisible(true);
            } else if (i == 3) {
                String[] fields = line.split("\t");
                Record record = new Record(fields[0], fields[1]);
                labelInicio.setText(fields[1]);
                labelInicio.setVisible(true);
            } else if (i == 4) {
                String[] fields = line.split("\t");
                Record record = new Record(fields[0], fields[1]);
                labelFinal.setText(fields[1]);
                labelFinal.setVisible(true);
            }
        }
    }
}