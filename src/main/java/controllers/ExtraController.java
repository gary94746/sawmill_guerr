package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import modelo.extra.Extra;
import modelo.resumen.Resumen;
import modelo.tabletas.Tabletas;

import java.net.URL;
import java.util.ResourceBundle;

public class ExtraController implements Initializable {

    @FXML private JFXTreeTableView<Extra> tabla3;
    @FXML private JFXTextField lblTotal;

    private ObservableList<Extra> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        list.add(MaderaTabletasController.subtotales);
        lblTotal.setText(String.valueOf(MaderaTabletasController.totalFinal));
        columnas();
    }

    private void columnas() {
        final TreeItem<Extra> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        tabla3.setRoot(root);

        // Columna de subtotal longitud 2.
        JFXTreeTableColumn<Extra, Double> longitud2 = new JFXTreeTableColumn<>("Longitud 2");
        longitud2.setEditable(false);

        longitud2.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud2.validateValue(param))
                return param.getValue().getValue().longitudColumn2Property().asObject();
            else
                return longitud2.getComputedValue(param);
        });

        // Columna de subtotal longitud 3.
        JFXTreeTableColumn<Extra, Double> longitud3 = new JFXTreeTableColumn<>("Longitud 3");
        longitud3.setEditable(false);

        longitud3.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud3.validateValue(param))
                return param.getValue().getValue().longitudColumn3Property().asObject();
            else
                return longitud3.getComputedValue(param);
        });

        // Columna de subtotal longitud 4.
        JFXTreeTableColumn<Extra, Double> longitud4 = new JFXTreeTableColumn<>("Longitud 4");
        longitud4.setEditable(false);

        longitud4.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud4.validateValue(param))
                return param.getValue().getValue().longitudColumn4Property().asObject();
            else
                return longitud4.getComputedValue(param);
        });

        // Columna de subtotal longitud 5.
        JFXTreeTableColumn<Extra, Double> longitud5 = new JFXTreeTableColumn<>("Longitud 5");
        longitud5.setEditable(false);

        longitud5.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud5.validateValue(param))
                return param.getValue().getValue().longitudColumn5Property().asObject();
            else
                return longitud5.getComputedValue(param);
        });

        // Columna de subtotal longitud 6.
        JFXTreeTableColumn<Extra, Double> longitud6 = new JFXTreeTableColumn<>("Longitud 6");
        longitud6.setEditable(false);

        longitud6.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud6.validateValue(param))
                return param.getValue().getValue().longitudColumn6Property().asObject();
            else
                return longitud6.getComputedValue(param);
        });

        // Columna de subtotal longitud 7.
        JFXTreeTableColumn<Extra, Double> longitud7 = new JFXTreeTableColumn<>("Longitud 7");
        longitud7.setEditable(false);

        longitud7.setCellValueFactory((TreeTableColumn.CellDataFeatures<Extra, Double> param) -> {
            if (longitud7.validateValue(param))
                return param.getValue().getValue().longitudColumn7Property().asObject();
            else
                return longitud7.getComputedValue(param);
        });

        //Operaciones con la tabla
        tabla3.setEditable(false);
        tabla3.setShowRoot(false);
        tabla3.getColumns().setAll(longitud2, longitud3, longitud4, longitud5, longitud6, longitud7);
    }
}
