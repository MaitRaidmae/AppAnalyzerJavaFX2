/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.Misc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Hundisilm
 */
public class Alerts {

    public static void AlertSQL(SQLException sqlError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The following error occured");
        alert.setContentText(sqlError.getMessage());
        alert.showAndWait();
    }

    public static void AlertWindow(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Window opening error");
        alert.setHeaderText("Error loading new window");
        System.out.println("Error loading new window " + exception.getMessage());
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }

    public static void AlertIO(IOException exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IO Error");
        alert.setHeaderText("IO Error occurred");
        System.out.println("IO Error: " + exception.getMessage());
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }
    
    public static void GeneralAlert(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("General Error");
        alert.setHeaderText("An error of general type has occurreth.");
        System.out.println("Error: " + exception.getMessage());
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
    }
    
    public static boolean confirmCommit(String message) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm commit");
        confirm.setHeaderText("Confirm commit of " + message + ".");
        Optional<ButtonType> result = confirm.showAndWait();
        return result.get() == ButtonType.OK; //Return true if OK pressed, else false.
    }
    
    public static boolean confirmRunSuite(String suite) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Suite Run");
        confirm.setHeaderText("Confirm running suite " + suite + ".");
        Optional<ButtonType> result = confirm.showAndWait();
        return result.get() == ButtonType.OK; //Return true if OK pressed, else false.
    }
    
    //TODO: This got lost in some git magic - try to revive
    public static void succesfulCommit(String input){
    }
    
}
