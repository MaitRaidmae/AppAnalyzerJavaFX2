/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.FXControllers;

import applicationanalyzer.DataClasses.Checks;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CssLoader;
import applicationanalyzer.MiscFXStuff.CustomButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Hundisilm
 */
public class EditChecksController implements Initializable {

    @FXML
    AnchorPane editBox;

    @FXML
    AnchorPane editParsBox;

    @FXML
    MenuBar menuBar;

    private GridPane checkGrid;
    private GridPane parsGrid;
    private Boolean commited = false;
    private Checks check;
    private Stage editStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initObject(Checks newCheck) {
                //Load CSS and set 

        this.check = newCheck;
        checkGrid = check.getGrid(true);
        CssLoader css = new CssLoader("/applicationanalyzer/FXML/CSS/EditChecks.css", "checkGrid");
        checkGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
        checkGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
        checkGrid.getStyleClass().add("checkGrid");
        editBox.getChildren().add(checkGrid);

        parsGrid = check.getParsGrid(true);
        if (parsGrid != null) {
            parsGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL1_WIDTH));
            parsGrid.getColumnConstraints().add(new ColumnConstraints(css.GRID_COL2_WIDTH));
            parsGrid.getStyleClass().add("checkGrid");
            editParsBox.getChildren().add(parsGrid);
        }
    }

    public void setStage(Stage stage) {
        editStage = stage;
    }

    public boolean isCommited() {
        return commited;
    }

    public void handleCommit(ActionEvent event) {
        Boolean confirmed = Alerts.confirmCommit("update Check");
        if (confirmed == true) {
            check.setFromGrid(checkGrid);
            check.commit();
            if (parsGrid != null) {
                check.setParsFromGrid(parsGrid);
                check.commitPars();
            } else {
                editParsBox.getChildren().add(CustomButton.createButton("Parameters"));
            }
            commited = true;
            editStage.close();
        }
    }

}
