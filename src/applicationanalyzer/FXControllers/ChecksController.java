/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.FXControllers;

import applicationanalyzer.DataClasses.CheckLovs;
import applicationanalyzer.DataClasses.CheckSuits;
import applicationanalyzer.DataClasses.Checks;
import applicationanalyzer.DataClasses.LovCheckPars;
import applicationanalyzer.DataClasses.MinmaxCheckPars;
import applicationanalyzer.DataClasses.RpredictCheckPars;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.CssLoader;
import applicationanalyzer.Misc.Helpers;
import applicationanalyzer.Misc.SQLExecutor;
import applicationanalyzer.MiscFXStuff.CreateObjectLoader;
import applicationanalyzer.MiscFXStuff.CustomButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ChecksController implements Initializable {

    @FXML
    private AnchorPane ChecksForm;
    @FXML
    private AnchorPane checksPane;
    @FXML
    private AnchorPane checkLovPane;
    
    private TableView<Checks> checksTable;
    Checks checks;
    CheckSuits checkSuits;
    LovCheckPars lovCheckPars;
    Stage stage;

    @FXML
    private void searchData(ActionEvent event) {
        showResults();
    }

    public void showResults() {
        showResults(99999999);
    }

    public void showResults(Integer results) {

        ObservableList<Checks> obsArrayList = FXCollections.observableArrayList();
        CallableStatementResults callResults = SQLExecutor.getTablePage("P_CHECKS", 1, results,"CHK_CHS_CODE",checkSuits.getChsCode());
        ResultSet query_results = callResults.getResultSet();
        checksTable = new TableView(); //Move this to controller declaration in synth
        checksTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCheckParsData(newValue));
        AnchorPane.setBottomAnchor(checksTable, 0.0);
        AnchorPane.setTopAnchor(checksTable, 0.0);
        AnchorPane.setRightAnchor(checksTable, 0.0);
        AnchorPane.setLeftAnchor(checksTable, 0.0);
        TableColumn chk_code = Helpers.initTableColumn("B_CHECKS", "CHK_CODE");
        TableColumn chk_mnemo = Helpers.initTableColumn("B_CHECKS", "CHK_MNEMO");
        TableColumn chk_type = Helpers.initTableColumn("B_CHECKS", "CHK_TYPE");
        TableColumn chk_comment = Helpers.initTableColumn("B_CHECKS", "CHK_COMMENT");
        TableColumn chk_active = Helpers.initTableColumn("B_CHECKS", "CHK_ACTIVE");
        TableColumn chk_chs_code = Helpers.initTableColumn("B_CHECKS", "CHK_CHS_CODE");
        checksPane.getChildren().add(checksTable);
        checksTable.getColumns().setAll(chk_code, chk_mnemo, chk_type, chk_comment, chk_active, chk_chs_code);
        try {
            while (query_results.next()) {
                Checks dataInstance = new Checks(query_results.getInt(1),
                        query_results.getString(2),
                        query_results.getString(3),
                        query_results.getString(4),
                        query_results.getInt(5),
                        query_results.getInt(6));
                obsArrayList.add(dataInstance);
            }
            chk_code.setCellValueFactory(new PropertyValueFactory<>("ChkCode"));
            chk_mnemo.setCellValueFactory(new PropertyValueFactory<>("ChkMnemo"));
            chk_type.setCellValueFactory(new PropertyValueFactory<>("ChkType"));
            chk_comment.setCellValueFactory(new PropertyValueFactory<>("ChkComment"));
            chk_active.setCellValueFactory(new PropertyValueFactory<>("ChkActive"));
            chk_active.setCellFactory(new ChecksCheckBoxFactory());
            chk_chs_code.setCellValueFactory(new PropertyValueFactory<>("ChkChsCode"));
            checksTable.setRowFactory(rowfactory -> {
                TableRow<Checks> row = new TableRow<>();
                row.setOnMouseClicked(MouseEventHandler(row));
                return row;
            });
            checksTable.setItems(obsArrayList);
            checksTable.getColumns().setAll(chk_code, chk_mnemo, chk_type, chk_comment, chk_active, chk_chs_code);
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();

    }

    private EventHandler<MouseEvent> MouseEventHandler(TableRow<Checks> row) {
        EventHandler<MouseEvent> mouseEvent
                = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent event) {
                        if (event.getClickCount() >= 2 && event.getButton() == MouseButton.PRIMARY) {
//Add DoubleClick Event here                        
                        } else if (event.getButton() == MouseButton.SECONDARY) {
// Add rightClick Event here (open popupMenu for example                            
                            ContextMenu contextMenu = new ContextMenu();
                            Checks check = row.getItem();
                            MenuItem editItem = new MenuItem("Edit");
                            MenuItem deleteItem = new MenuItem("Delete");
                            editItem.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    check.showEditDialog(checksPane.getScene().getWindow());
                                    initialize(null, null);
                                }
                            });
                            deleteItem.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    check.delete();
                                    initialize(null, null);
                                }
                            });
                            contextMenu.getItems().addAll(editItem,deleteItem);
                            contextMenu.show(row, event.getScreenX(), event.getScreenY());
                        }
                    }
                };
        return mouseEvent;
    }

    public class ChecksCheckBoxFactory<T, Boolean> implements Callback {

        @Override
        public TableCell call(Object param) {
            CheckBox checkBox = new CheckBox();
            TableCell<Checks, Boolean> checkBoxCell = new TableCell() {
                @Override
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        checkBox.setAlignment(Pos.CENTER);
                        setGraphic(checkBox);
                        Checks data = (Checks) getTableRow().getItem();
                        if (data != null && data.getChkActive() == 1) {
                            checkBox.setSelected(true);
                        } else {
                            checkBox.setSelected(false);
                        }
                    }
                }
            };
            checkBoxCell.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    (MouseEvent event) -> {
                        TableCell c = (TableCell) event.getSource();
                        CheckBox chkBox = (CheckBox) checkBoxCell.getChildrenUnmodifiable().get(0);
                        Checks data = (Checks) c.getTableRow().getItem();
                        if (chkBox.isSelected()) {
                            data.setChkActive(1);
                        } else {
                            data.setChkActive(0);
                        }
                    }
            );
            return checkBoxCell;
        }
    }

    public void setStage(Stage newStage) {
        stage = newStage;
    }

    public void initObject(Checks newInstance) {

        checks = newInstance;
        GridPane checksGrid = checks.getGrid(true);
        CssLoader css = new CssLoader("/applicationanalyzer/FXML/CSS/Checks.css", "checksGrid");
        checksGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
        checksGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
        checksGrid.getStyleClass().add("checksGrid");
        checksPane.getChildren().add(checksGrid);
    }

    public void setCheckSuits(CheckSuits newCheckSuits) {
        checkSuits = newCheckSuits;
    }
//Custom part of the controller
    @FXML
    MenuBar menuBar;
    @FXML
    AnchorPane checkParsPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

// Menu Handlers    
    @FXML
    private void handleQuit(ActionEvent event) {
        Stage application_stage = (Stage) menuBar.getScene().getWindow();
        application_stage.close();
    }

    @FXML
    private void handleViewCheckSuits(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/applicationanalyzer/FXML/CheckSuits.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root1));
            newStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errror!!! AAAaaaaerrgggh!!");
            alert.setHeaderText("Error loading new window");
            System.out.println("Error " + e.getMessage());
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRunSuiteChecks(ActionEvent event) {
        Boolean confirmed = Alerts.confirmRunSuite(checkSuits.getChsMnemo());
        if (confirmed == true) {
            boolean execution = SQLExecutor.executeProcedure("CHECKS.RUN_CHECKS(" + checkSuits.getChsCode() + ", 'Placeholder')");
        }
    }
    
    MinmaxCheckPars mmPars  = new MinmaxCheckPars();
    RpredictCheckPars rpPars  = new RpredictCheckPars();
    LovCheckPars lovPars = new LovCheckPars();
    CheckLovs checkLovs = new CheckLovs();
    GridPane grid = new GridPane();
    ListView list = null;
    
    private void showCheckParsData(Checks check) {
        checks = check;
        checkParsPane.getChildren().clear();
        checkLovPane.getChildren().clear();
        list = null;
        
        switch (check.getChkType()) {
            case "MIN":
            case "MAX":
                mmPars  = new MinmaxCheckPars(check.getChkCode());
                grid    = mmPars.getGrid(false);
                break;
            case "RPREDICT":
                rpPars  = new RpredictCheckPars(check.getChkCode());
                grid    = rpPars.getGrid(false);
                break;
            case "ALLOWED_LOV":
            case "FORBIDDEN_LOV":
                lovPars   = new LovCheckPars(check.getChkCode());
                checkLovs = new CheckLovs(lovPars.getLcpCode());
                grid      = lovPars.getGrid(false);
                list      = checkLovs.getListView(false);
                break;
        }

        CssLoader css = new CssLoader("/applicationanalyzer/FXML/CSS/Checks.css", "checkParsGrid");
        if (grid != null) {
            grid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
            grid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
            checkParsPane.getChildren().add(grid);
            if ( list != null) {
                checkLovPane.getChildren().add(list);
            }
        } else {
            Button createButton = CustomButton.createButton("Parameters");
            createButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    check.initCreatePars(check.getChkType(),ChecksForm.getScene().getWindow());
                }
            });
            checkParsPane.getChildren().add(createButton);
        }
    }
    
    @FXML
    private void createCheck(ActionEvent event) {
        Checks newCheck = new Checks();
        CreateObjectLoader loader = new CreateObjectLoader();
        loader.initCreateWindow(CreateObjectLoader.ObjectType.CHECKS, ChecksForm.getScene().getWindow(), checkSuits);
    }
}
