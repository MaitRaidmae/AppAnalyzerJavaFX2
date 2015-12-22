/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.FXControllers;

import applicationanalyzer.DataClasses.CheckSuits;
import applicationanalyzer.DataClasses.Checks;
import applicationanalyzer.DataClasses.LovCheckPars;
import applicationanalyzer.DataClasses.MinmaxCheckPars;
import applicationanalyzer.DataClasses.RpredictCheckPars;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CssLoader;
import applicationanalyzer.MiscFXStuff.CreateObjectLoader.ObjectType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hundisilm
 */
public class CreateObjectController implements Initializable {

    /**
     * Initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    AnchorPane createObjectPane;

    GridPane grid;
    ObjectType objectType;
    Object object;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initObject(ObjectType newObject) {
        objectType = newObject;
        CssLoader css = new CssLoader("/applicationanalyzer/FXML/CSS/CreateObject.css", objectType.name().toLowerCase() + "_grid");
        switch (objectType) {
            case MINMAX_CHECK_PARS:
                grid = MinmaxCheckPars.getEmptyGrid();
                break;
            case RPREDICT_CHECK_PARS:
                grid = RpredictCheckPars.getEmptyGrid();
                break;
            case CHECKS:
                grid = Checks.getEmptyGrid(css.GRID_HIDDEN_COLS);
                break;
            case LOV_CHECK_PARS:
                grid = LovCheckPars.getEmptyGrid(css.GRID_HIDDEN_COLS);
                break;
        }
        if (grid != null) {
            grid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
            grid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
            //Try to remove gridRows with hidden cols:
            grid.getStylesheets().add("/applicationanalyzer/FXML/CSS/CreateObject.css");
            grid.getStyleClass().add(objectType.name().toLowerCase() + "_grid");
            createObjectPane.getChildren().add(grid);
        }
    }

    public void setObject(Object newObject) {
        object = newObject;
    }

    public void setStage(Stage newStage) {
        stage = newStage;
    }

    @FXML
    private void createObject(ActionEvent event) {
        boolean confirmed = false;
        switch (objectType) {
            case MINMAX_CHECK_PARS:
                Checks mmChecks = (Checks) object;
                MinmaxCheckPars mmPars = new MinmaxCheckPars();
                mmPars.setMcpChkCode(mmChecks.getChkCode());
                mmPars.setFromGrid(grid);
                confirmed = Alerts.confirmCommit("create new MinMax check parameters.");
                if (confirmed == true) {
                    mmPars.create();
                    Alerts.succesfulCommit("Successfully added new MinMax check Parameters.");
                    stage.close();
                }
                break;

            case RPREDICT_CHECK_PARS:
                Checks rpChecks = (Checks) object;
                RpredictCheckPars rpPars = new RpredictCheckPars();
                rpPars.setRcpChkCode(rpChecks.getChkCode());
                rpPars.setFromGrid(grid);
                confirmed = Alerts.confirmCommit("create new Rpredict check parameters.");
                if (confirmed == true) {
                    rpPars.create();
                    Alerts.succesfulCommit("Successfully added new Rpredict check Parameters.");
                    stage.close();
                }
                break;

            case LOV_CHECK_PARS:
                Checks lovChecks = (Checks) object;
                LovCheckPars lovPars = new LovCheckPars();
                lovPars.setLcpChkCode(lovChecks.getChkCode());
                lovPars.setFromGrid(grid);
                confirmed = Alerts.confirmCommit("create new LOV check parameters.");
                if (confirmed == true) {
                    lovPars.create();
                    Alerts.succesfulCommit("Successfully added new LOV check Parameters.");
                    stage.close();
                }
                break;

            case CHECKS:
                CheckSuits checkSuite = (CheckSuits) object;
                Checks checks = new Checks();
                checks.setChkChsCode(checkSuite.getChsCode());
                checks.setFromGrid(grid);
                confirmed = Alerts.confirmCommit("create new Check.");
                if (confirmed == true) {
                    checks.create();
                    Alerts.succesfulCommit("Successfully added new Check.");
                    stage.close();
                }
                break;

        }
    }

}
