package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import modelo.Control_madera.madera_control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResumenController implements Initializable {

    @FXML
    private JFXTreeTableView<Example> treTable;

    private ObservableList<Example> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        list.add(new Example("Hello"));
        list.add(new Example("Hello"));
        list.add(new Example("Hello"));
        list.add(new Example("Hello"));

        columns();
    }


    //.asObject
    private void columns() {
        final TreeItem<Example> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        list.forEach(x -> System.out.println(x));
        treTable.setRoot(root);

        JFXTreeTableColumn<Example, String> clmX = new JFXTreeTableColumn<>("Example");
        clmX.setEditable(false);

        clmX.setCellValueFactory((TreeTableColumn.CellDataFeatures<Example, String> param) -> {
            if (clmX.validateValue(param))
                return param.getValue().getValue().xProperty();
            else
                return clmX.getComputedValue(param);
        });

        //Operaciones con la tabla
        treTable.setEditable(false);
        treTable.setShowRoot(false);
        treTable.getColumns().setAll(clmX);
    }
}

class Example extends RecursiveTreeObject<Example> {
    private StringProperty x;

    public Example(String x) {
        this.x = new SimpleStringProperty(x);
    }

    public String getX() {
        return x.get();
    }

    public StringProperty xProperty() {
        return x;
    }

    public void setX(String x) {
        this.x.set(x);
    }

    @Override
    public String toString() {
        return "Example{" +
                "x=" + x +
                '}';
    }
}