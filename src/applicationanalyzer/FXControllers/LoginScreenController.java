/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.FXControllers;

import applicationanalyzer.DataClasses.MinmaxCheckPars;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.ConnectionManager;
import applicationanalyzer.Misc.CssLoader;
import applicationanalyzer.Misc.Helpers;
import applicationanalyzer.Misc.SQLExecutor;
import applicationanalyzer.MiscFXStuff.CreateObjectLoader;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.SQLException;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Hundisilm
 */
public class LoginScreenController implements Initializable {

    @FXML
    private TextField lbl_error;
    @FXML
    private TextField fld_username;
    @FXML
    private TextField fld_password;
    @FXML
    private Button btn_login;
    @FXML
    private Button test;

    @FXML
    private void handleLoginButton(ActionEvent event) {
        final String username = fld_username.getText();
        final String password = fld_password.getText();
        ConnectionManager login = new ConnectionManager();
        Connection db_con;
        try {
            db_con = login.getDBConnection(username, password);
            try {
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/TableView.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/applicationanalyzer/FXML/Applications.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Applications");
                stage.show();

                Stage login_stage = (Stage) btn_login.getScene().getWindow();
                login_stage.close();

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Tried to log in as " + username);
                alert.setHeaderText("Error loading new window");
                System.out.println("Error " + e.getMessage());
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    @FXML
    private void handleTest(ActionEvent event) {
        /*
         CssLoader cssLoader = new CssLoader("/applicationanalyzer/FXML/CSS/ApplicationChecks.css","applicationChecksTable");
         System.out.println(cssLoader.TABLE_COL_WIDTHS.get("APL_CODE"));*/
        
        
        //System.out.println(SQLExecutor.getPackageName("V_TEST"));
        /*
         Boolean confirmed = Alerts.confirmCommit("update Checks");
         System.out.println(confirmed);
         */
        /*
         CssLoader cssLoader = new CssLoader("/applicationanalyzer/FXML/CSS/ApplicationChecks.css","applicationsGrid");
         System.out.println(cssLoader.GRID_COL1_WIDTH);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fld_username.setText("Hundisilm");
        fld_password.setText("dummyPassword");
        try {
            ConnectionManager login = new ConnectionManager();
            Connection db_con = login.getDBConnection(fld_username.getText(), fld_password.getText());
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }
}
