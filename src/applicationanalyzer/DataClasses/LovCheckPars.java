/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.DataClasses;

import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.ConnectionManager;
import applicationanalyzer.Misc.SQLExecutor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LovCheckPars {

    private final SimpleIntegerProperty lcp_code;
    private final SimpleIntegerProperty lcp_chk_code;
    private final SimpleStringProperty lcp_check_field;

    public LovCheckPars(Integer lcp_code,
            Integer lcp_chk_code,
            String lcp_check_field) {
        this.lcp_code = new SimpleIntegerProperty(lcp_code);
        this.lcp_chk_code = new SimpleIntegerProperty(lcp_chk_code);
        this.lcp_check_field = new SimpleStringProperty(lcp_check_field);
    }

    public LovCheckPars() {
        this.lcp_code = new SimpleIntegerProperty();
        this.lcp_chk_code = new SimpleIntegerProperty();
        this.lcp_check_field = new SimpleStringProperty();
    }

    public LovCheckPars(Integer chk_code) {
        this();
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_LOV_CHECK_PARS", "LCP_CHK_CODE", chk_code);
        ResultSet pars = callResults.getResultSet();
        try {
            if (pars.next()) {
                lcp_code.set(pars.getInt("lcp_code"));
                lcp_chk_code.set(pars.getInt("lcp_chk_code"));
                lcp_check_field.set(pars.getString("lcp_check_field"));
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();
    }

    public Integer getLcpCode() {
        return lcp_code.get();
    }

    public void setLcpCode(Integer code) {
        lcp_code.set(code);
    }

    public Integer getLcpChkCode() {
        return lcp_chk_code.get();
    }

    public void setLcpChkCode(Integer chk_code) {
        lcp_chk_code.set(chk_code);
    }

    public String getLcpCheckField() {
        return lcp_check_field.get();
    }

    public void setLcpCheckField(String check_field) {
        lcp_check_field.set(check_field);
    }

    public GridPane getGrid(Boolean editable) {
        GridPane grid = new GridPane();
        Boolean noData = true; //Assume not to find anything
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getTableRow("b_lov_check_pars", "LCP_CODE", this.getLcpCode());
        ResultSet dataValues = callResults.getResultSet();
        ResultSetMetaData metaData;
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                noData = false;
                metaData = dataValues.getMetaData();
                for (int i = 2; i <= metaData.getColumnCount(); i++) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("b_lov_check_pars", metaData.getColumnName(i)));
                    grid.add(fieldNameLbl, 0, k);
                    if (!editable) {
                        Label fieldValueLbl = new Label(dataValues.getString(i));
                        grid.add(fieldValueLbl, 1, k);
                    } else {
                        switch (metaData.getColumnName(i)) {

                            case "LCP_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "LCP_CHK_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "LCP_CHECK_FIELD":
                                comboBox = new ComboBox(SQLExecutor.getLov("b_lov_check_pars", metaData.getColumnName(i)));
                                comboBox.setValue(dataValues.getString(i));
                                fieldType = "comboBox";
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
        CallableStatementResults callResults = SQLExecutor.getColumnData("B_LOV_CHECK_PARS");
        ResultSet dataValues = callResults.getResultSet();
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                if (hiddenCols == null || !hiddenCols.contains(dataValues.getString("column_name"))) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_LOV_CHECK_PARS", dataValues.getString("column_name")));
                    grid.add(fieldNameLbl, 0, k);
                    switch (dataValues.getInt("is_lov")) {
                        case 1:
                            comboBox = new ComboBox(SQLExecutor.getLov("B_LOV_CHECK_PARS", dataValues.getString("column_name")));
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
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_LOV_CHECK_PARS.UPDATE_ROW(?,?,?) }")) {
            callStmt.setInt("par_lcp_code", this.getLcpCode());
            callStmt.setInt("par_lcp_chk_code", this.getLcpChkCode());
            callStmt.setString("par_lcp_check_field", this.getLcpCheckField());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    public void setFromGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                switch (textField.getId()) {

                    case "LCP_CODE":
                        this.setLcpCode(Integer.parseInt(textField.getText()));
                        break;
                    case "LCP_CHK_CODE":
                        this.setLcpChkCode(Integer.parseInt(textField.getText()));
                        break;
                }
            } else if (node instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) node;
                switch (comboBox.getId()) {

                    case "LCP_CHECK_FIELD":
                        this.setLcpCheckField((String) comboBox.getValue());
                        break;
                }
            }
        }
    }

    public void create() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_LOV_CHECK_PARS.INSERT_ROW(?,?) }")) {
            callStmt.setInt("par_lcp_chk_code", this.getLcpChkCode());
            callStmt.setString("par_lcp_check_field", this.getLcpCheckField());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    public void delete() {
        SQLExecutor.deleteRow("B_LOV_CHECK_PARS", getLcpCode());
    }

    public static CallableStatementResults getResultSet(Integer CHK_CODE) {
        CallableStatementResults callResults = SQLExecutor.getTableRow("B_LOV_CHECK_PARS", "LCPCHK_CODE", CHK_CODE);
        return callResults;
    }
}
