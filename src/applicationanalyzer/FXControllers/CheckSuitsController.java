/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.FXControllers;

import applicationanalyzer.DataClasses.CheckSuits;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.CssLoader;
import applicationanalyzer.Misc.Helpers;
import applicationanalyzer.Misc.SQLExecutor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckSuitsController implements Initializable {

    @FXML
    private AnchorPane CheckSuits;
    @FXML
    private AnchorPane checkSuitsPane;
    
    Stage stage;
    
    private CheckSuits checkSuits;
    
    @FXML
    public void searchData(ActionEvent event) {
        showResults(45);
    }

    public void showResults(Integer results) {

        ObservableList<CheckSuits> obsArrayList = FXCollections.observableArrayList();
        CallableStatementResults callResults = SQLExecutor.getTablePage("P_CHECK_SUITS", 1, results);
        ResultSet query_results = callResults.getResultSet();
        TableView<CheckSuits> checkSuitsTable = new TableView();
        AnchorPane.setBottomAnchor(checkSuitsTable, 0.0);
        AnchorPane.setTopAnchor(checkSuitsTable, 0.0);
        AnchorPane.setRightAnchor(checkSuitsTable, 0.0);
        AnchorPane.setLeftAnchor(checkSuitsTable, 0.0);
        checkSuitsPane.getChildren().add(checkSuitsTable);
        checkSuitsTable.getStyleClass().add("checkSuitsTable");
        TableColumn chs_code = Helpers.initTableColumn("B_CHECK_SUITS", "CHS_CODE");
        TableColumn chs_mnemo = Helpers.initTableColumn("B_CHECK_SUITS", "CHS_MNEMO");
        TableColumn chs_comment = Helpers.initTableColumn("B_CHECK_SUITS", "CHS_COMMENT");
        TableColumn chs_datetime = Helpers.initTableColumn("B_CHECK_SUITS", "CHS_DATETIME");
        try {
            while (query_results.next()) {
                CheckSuits dataInstance = new CheckSuits(query_results.getInt(1),
                        query_results.getString(2),
                        query_results.getString(3),
                        query_results.getDate(4));
                obsArrayList.add(dataInstance);
            }
            chs_code.setCellValueFactory(new PropertyValueFactory<>("ChsCode"));
            chs_mnemo.setCellValueFactory(new PropertyValueFactory<>("ChsMnemo"));
            chs_comment.setCellValueFactory(new PropertyValueFactory<>("ChsComment"));
            chs_datetime.setCellValueFactory(new PropertyValueFactory<>("ChsDatetime"));
            checkSuitsTable.setRowFactory(rowfactory -> {
                TableRow<CheckSuits> row = new TableRow<>();
                row.setOnMouseClicked(MouseEventHandler(row));
                return row;
            });
            checkSuitsTable.setItems(obsArrayList);
            checkSuitsTable.getColumns().setAll(chs_code, chs_mnemo, chs_comment, chs_datetime);
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();

    }

    private EventHandler<MouseEvent> MouseEventHandler(TableRow<CheckSuits> row) {
        EventHandler<MouseEvent> mouseEvent
                = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) {
                        CheckSuits currSuite = row.getItem();
                        if (event.getClickCount() >= 2) {
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/applicationanalyzer/FXML/Checks.fxml"));
                                AnchorPane page = (AnchorPane) fxmlLoader.load();
                                Stage newStage = new Stage();
                                newStage.setTitle("Suite " + currSuite.getChsMnemo() + " checks : ");
                                Scene scene = new Scene(page);
                                newStage.setScene(scene);
                                scene.getStylesheets().add("/applicationanalyzer/FXML/CSS/Checks.css");
                                ChecksController controller = fxmlLoader.getController();
                                controller.setCheckSuits(currSuite);
                                controller.showResults();
                                controller.setStage(newStage);
                                newStage.showAndWait();
                            } catch (IOException e) {
                                Alerts.AlertIO(e);
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) {
// Add rightClick Event here (open popupMenu for example
                        }
                    }
                };
        return mouseEvent;
    }

    public void setStage(Stage newStage) {
        stage = newStage;
    }

    public void initObject(CheckSuits newInstance) {

        checkSuits = newInstance;
        GridPane checkSuitsGrid = checkSuits.getGrid(true);
        CssLoader css = new CssLoader("/applicationanalyzer/FXML/CSS/CheckSuits.css", "checkSuitsGrid");
        checkSuitsGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
        checkSuitsGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
        checkSuitsGrid.getStyleClass().add("checkSuitsGrid");
        checkSuitsPane.getChildren().add(checkSuitsGrid);
    }
//Custom part of the controller

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showResults(999999);
    }
}
