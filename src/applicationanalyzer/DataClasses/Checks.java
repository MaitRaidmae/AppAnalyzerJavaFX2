/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.DataClasses;

import applicationanalyzer.FXControllers.EditChecksController;
import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.ConnectionManager;
import applicationanalyzer.Misc.SQLExecutor;
import applicationanalyzer.MiscFXStuff.CreateObjectLoader;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Checks {

    private final SimpleIntegerProperty chk_code;
    private final SimpleStringProperty chk_mnemo;
    private final SimpleStringProperty chk_type;
    private final SimpleStringProperty chk_comment;
    private final SimpleIntegerProperty chk_active;
    private final SimpleIntegerProperty chk_chs_code;

    public Checks(Integer chk_code,
            String chk_mnemo,
            String chk_type,
            String chk_comment,
            Integer chk_active,
            Integer chk_chs_code) {
        this.chk_code = new SimpleIntegerProperty(chk_code);
        this.chk_mnemo = new SimpleStringProperty(chk_mnemo);
        this.chk_type = new SimpleStringProperty(chk_type);
        this.chk_comment = new SimpleStringProperty(chk_comment);
        this.chk_active = new SimpleIntegerProperty(chk_active);
        this.chk_chs_code = new SimpleIntegerProperty(chk_chs_code);
    }

    public Checks() {
        this.chk_code = new SimpleIntegerProperty();
        this.chk_mnemo = new SimpleStringProperty();
        this.chk_type = new SimpleStringProperty();
        this.chk_comment = new SimpleStringProperty();
        this.chk_active = new SimpleIntegerProperty();
        this.chk_chs_code = new SimpleIntegerProperty();
    }

    public Integer getChkCode() {
        return chk_code.get();
    }

    public void setChkCode(Integer code) {
        chk_code.set(code);
    }

    public String getChkMnemo() {
        return chk_mnemo.get();
    }

    public void setChkMnemo(String mnemo) {
        chk_mnemo.set(mnemo);
    }

    public String getChkType() {
        return chk_type.get();
    }

    public void setChkType(String type) {
        chk_type.set(type);
    }

    public String getChkComment() {
        return chk_comment.get();
    }

    public void setChkComment(String comment) {
        chk_comment.set(comment);
    }

    public Integer getChkActive() {
        return chk_active.get();
    }

    public void setChkActive(Integer active) {
        chk_active.set(active);
        SQLExecutor.updateRowNvalue("P_CHECKS", getChkCode(), "CHK_ACTIVE", getChkActive());
    }

    public Integer getChkChsCode() {
        return chk_chs_code.get();
    }

    public void setChkChsCode(Integer chs_code) {
        chk_chs_code.set(chs_code);
    }

    public GridPane getGrid(Boolean editable) {
        GridPane grid = new GridPane();
        Boolean noData = true; //Assume not to find anything
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_CHECKS", "CHK_CODE", this.getChkCode());
        ResultSet dataValues = callResults.getResultSet();
        ResultSetMetaData metaData;
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                noData = false;
                metaData = dataValues.getMetaData();
                for (int i = 2; i <= metaData.getColumnCount(); i++) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_CHECKS", metaData.getColumnName(i)));
                    grid.add(fieldNameLbl, 0, k);
                    if (!editable) {
                        Label fieldValueLbl = new Label(dataValues.getString(i));
                        grid.add(fieldValueLbl, 1, k);
                    } else {
                        switch (metaData.getColumnName(i)) {

                            case "CHK_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CHK_MNEMO":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CHK_TYPE":
                                comboBox = new ComboBox(SQLExecutor.getLov("B_CHECKS", metaData.getColumnName(i)));
                                comboBox.setValue(dataValues.getString(i));
                                fieldType = "comboBox";
                                break;
                            case "CHK_COMMENT":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CHK_ACTIVE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CHK_CHS_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                        }
                        switch (fieldType) {
                            case "textField":
                                textField.setId(metaData.getColumnName(i));
                                grid.add(textField, 1, k);
                                break;
                            case "comboBox":
                                comboBox.setId(metaData.getColumnName(i));
                                grid.add(comboBox, 1, k);
                                break;
                        }
                    }
                    k++;
                }
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();
        if (noData == false) {
            return grid;
        } else {
            return null;
        }
    }

    public static GridPane getEmptyGrid(List hiddenCols) {
        GridPane grid = new GridPane();
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getColumnData("B_CHECKS");
        ResultSet dataValues = callResults.getResultSet();
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                if (hiddenCols == null || !hiddenCols.contains(dataValues.getString("column_name"))) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_CHECKS", dataValues.getString("column_name")));
                    grid.add(fieldNameLbl, 0, k);
                    switch (dataValues.getInt("is_lov")) {
                        case 1:
                            comboBox = new ComboBox(SQLExecutor.getLov("B_CHECKS", dataValues.getString("column_name")));
                            fieldType = "comboBox";
                            break;
                        default:
                            textField = new TextField();
                            fieldType = "textField";
                    }
                    switch (fieldType) {
                        case "textField":
                            textField.setId(dataValues.getString("column_name"));
                            grid.add(textField, 1, k);
                            break;
                        case "comboBox":
                            comboBox.setId(dataValues.getString("column_name"));
                            grid.add(comboBox, 1, k);
                            break;
                    }
                    k++;
                }
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();
        return grid;
    }

    public void commit() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_CHECKS.UPDATE_ROW(?,?,?,?,?,?) }")) {
            callStmt.setInt("par_chk_code", this.getChkCode());
            callStmt.setString("par_chk_mnemo", this.getChkMnemo());
            callStmt.setString("par_chk_type", this.getChkType());
            callStmt.setString("par_chk_comment", this.getChkComment());
            callStmt.setInt("par_chk_active", this.getChkActive());
            callStmt.setInt("par_chk_chs_code", this.getChkChsCode());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    public boolean showEditDialog(Window owner) {
        try {
//Load Edit FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/applicationanalyzer/FXML/EditChecks.fxml"));
            AnchorPane page = (AnchorPane) fxmlLoader.load();
//Load edit Stage
            Stage editStage = new Stage();
            editStage.setTitle("<<<PUT TITLE HERE>>>");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(owner);
            Scene scene = new Scene(page);
            editStage.setScene(scene);
// Set data object into the contoller
            EditChecksController controller = fxmlLoader.getController();
            controller.initObject(this);
            controller.setStage(editStage);
            editStage.showAndWait();
            return true;
        } catch (IOException e) {
            Alerts.AlertIO(e);
            return false;
        }
    }

    public void setFromGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                switch (textField.getId()) {

                    case "CHK_CODE":
                        this.setChkCode(Integer.parseInt(textField.getText()));
                        break;
                    case "CHK_MNEMO":
                        this.setChkMnemo(textField.getText());
                        break;
                    case "CHK_COMMENT":
                        this.setChkComment(textField.getText());
                        break;
                    case "CHK_ACTIVE":
                        this.setChkActive(Integer.parseInt(textField.getText()));
                        break;
                    case "CHK_CHS_CODE":
                        this.setChkChsCode(Integer.parseInt(textField.getText()));
                        break;
                }
            } else if (node instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) node;
                switch (comboBox.getId()) {
                    case "CHK_TYPE":
                        this.setChkType((String) comboBox.getValue());
                        break;
                }
            }
        }
    }
    
    public void delete(){
       SQLExecutor.deleteRow("B_CHECKS", getChkCode());
    };
    public void create() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_CHECKS.INSERT_ROW(?,?,?,?,?) }")) {
            callStmt.setString("par_chk_mnemo", this.getChkMnemo());
            callStmt.setString("par_chk_type", this.getChkType());
            callStmt.setString("par_chk_comment", this.getChkComment());
            callStmt.setInt("par_chk_active", this.getChkActive());
            callStmt.setInt("par_chk_chs_code", this.getChkChsCode());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }
    

    MinmaxCheckPars   mmPars;
    RpredictCheckPars rpPars;
    LovCheckPars      lovPars;

    public GridPane getParsGrid(Boolean editable) {
        switch (this.getChkType()) {
            case "MIN":
            case "MAX":
                mmPars = new MinmaxCheckPars(getChkCode());
                return mmPars.getGrid(editable);
            case "RPREDICT":
                rpPars = new RpredictCheckPars(getChkCode());
                return rpPars.getGrid(editable);
            case "ALLOWED_LOV":
            case "FORBIDDEN_LOV":
                lovPars = new LovCheckPars(getChkCode());
                return lovPars.getGrid(editable);
        }
        return null;
    }
    
    public GridPane getLovGrid(){
        GridPane grid = new GridPane();
        
        return grid;
    }
    
    public void setParsFromGrid(GridPane grid) {
        switch (this.getChkType()) {
            case "MIN":
            case "MAX":
                mmPars.setFromGrid(grid);
                break;
            case "RPREDICT":
                rpPars.setFromGrid(grid);
                break;
            case "ALLOWED_LOV":
            case "FORBIDDEN_LOV":
                lovPars.setFromGrid(grid);
                break;
        }
    }

    public void initCreatePars(String check_type, Window owner) {
        CreateObjectLoader loader = new CreateObjectLoader();
        switch (check_type) {
            case "MIN":
            case "MAX":
                loader.initCreateWindow(CreateObjectLoader.ObjectType.MINMAX_CHECK_PARS, owner, this);
                break;
            case "RPREDICT":
                loader.initCreateWindow(CreateObjectLoader.ObjectType.RPREDICT_CHECK_PARS, owner, this);
                break;
            case "ALLOWED_LOV":
            case "FORBIDDEN_LOV":
                loader.initCreateWindow(CreateObjectLoader.ObjectType.LOV_CHECK_PARS, owner, this);
                break;
        }
    }
   
    public void commitPars() {
        switch (this.getChkType()) {
            case "MIN":
            case "MAX":
                mmPars.commit();
                break;
            case "RPREDICT":
                rpPars.commit();
                break;
            case "ALLOWED_LOV":
            case "FORBIDDEN_LOV":
                lovPars.commit();
                break;
        }
    }
    

}
