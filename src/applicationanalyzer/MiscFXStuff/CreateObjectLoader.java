/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.MiscFXStuff;

import applicationanalyzer.DataClasses.MinmaxCheckPars;
import applicationanalyzer.FXControllers.CreateObjectController;
import applicationanalyzer.Misc.Alerts;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Hundisilm
 */
public class CreateObjectLoader {

    private final static MinmaxCheckPars mmPars = new MinmaxCheckPars();

    public static enum ObjectType {
        APPLICATIONS,
        CHECKS,
        CHECK_SUITS,
        MINMAX_CHECK_PARS,
        RPREDICT_CHECK_PARS,
        LOV_CHECK_PARS
    }

    public void initCreateWindow(ObjectType objectType, Window owner, Object object) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mmPars.getClass().getResource("/applicationanalyzer/FXML/CreateObject.fxml"));
            AnchorPane page = (AnchorPane) fxmlLoader.load();

            //Load edit Stage
            Stage newStage = new Stage();
            newStage.setTitle("Create new object of type: " + objectType);
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(owner);
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            scene.getStylesheets().add("/applicationanalyzer/FXML/CSS/CreateObject.css");
            // Set the check into the contoller
            CreateObjectController controller = fxmlLoader.getController();
            controller.initObject(objectType);
            controller.setObject(object);
            controller.setStage(newStage);
            newStage.showAndWait();
        } catch (IOException e) {
            Alerts.AlertIO(e);
        }
    }
}
