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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CheckLovs {

    private final SimpleIntegerProperty clv_code;
    private final SimpleStringProperty clv_value;
    private final SimpleIntegerProperty clv_lcp_code;

    public CheckLovs(Integer clv_code,
            String clv_value,
            Integer clv_lcp_code) {
        this.clv_code = new SimpleIntegerProperty(clv_code);
        this.clv_value = new SimpleStringProperty(clv_value);
        this.clv_lcp_code = new SimpleIntegerProperty(clv_lcp_code);
    }

    public CheckLovs() {
        this.clv_code = new SimpleIntegerProperty();
        this.clv_value = new SimpleStringProperty();
        this.clv_lcp_code = new SimpleIntegerProperty();
    }

    public CheckLovs(Integer lcp_code) {
        this();
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_CHECK_LOVS", "CLV_LCP_CODE", lcp_code);
        ResultSet pars = callResults.getResultSet();
        try {
            if (pars.next()) {
                clv_code.set(pars.getInt("clv_code"));
                clv_value.set(pars.getString("clv_value"));
                clv_lcp_code.set(pars.getInt("clv_lcp_code"));
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();
    }

    public Integer getClvCode() {
        return clv_code.get();
    }

    public void setClvCode(Integer code) {
        clv_code.set(code);
    }

    public String getClvValue() {
        return clv_value.get();
    }

    public void setClvValue(String value) {
        clv_value.set(value);
    }

    public Integer getClvLcpCode() {
        return clv_lcp_code.get();
    }

    public void setClvLcpCode(Integer lcp_code) {
        clv_lcp_code.set(lcp_code);
    }

    public GridPane getGrid(Boolean editable) {
        GridPane grid = new GridPane();
        Boolean noData = true; //Assume not to find anything
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_CHECK_LOVS", "CLV_CODE", this.getClvCode());
        ResultSet dataValues = callResults.getResultSet();
        ResultSetMetaData metaData;
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                noData = false;
                metaData = dataValues.getMetaData();
                for (int i = 2; i <= metaData.getColumnCount(); i++) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_CHECK_LOVS", metaData.getColumnName(i)));
                    grid.add(fieldNameLbl, 0, k);
                    if (!editable) {
                        Label fieldValueLbl = new Label(dataValues.getString(i));
                        grid.add(fieldValueLbl, 1, k);
                    } else {
                        switch (metaData.getColumnName(i)) {

                            case "CLV_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CLV_VALUE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "CLV_LCP_CODE":
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
        CallableStatementResults callResults = SQLExecutor.getColumnData("B_CHECK_LOVS");
        ResultSet dataValues = callResults.getResultSet();
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                if (hiddenCols == null || !hiddenCols.contains(dataValues.getString("column_name"))) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_CHECK_LOVS", dataValues.getString("column_name")));
                    grid.add(fieldNameLbl, 0, k);
                    switch (dataValues.getInt("is_lov")) {
                        case 1:
                            comboBox = new ComboBox(SQLExecutor.getLov("B_CHECK_LOVS", dataValues.getString("column_name")));
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
    
    public ListView getListView(Boolean editable){
        ObservableList<String> valuesList = FXCollections.observableArrayList();
        Boolean noData = true; //Assume not to find anything
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_CHECK_LOVS", "CLV_LCP_CODE", this.getClvLcpCode());
        ResultSet dataValues = callResults.getResultSet();
        try {
            while (dataValues.next()) {
                noData = false;
                valuesList.add(dataValues.getString(2));
                }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();
        ListView<String> listView = new ListView<>(valuesList);
        if (noData == false) {
            return listView;
        } else {
            return null;
        }
    }
    
    public void commit() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_CHECK_LOVS.UPDATE_ROW(?,?,?) }")) {
            callStmt.setInt("par_clv_code", this.getClvCode());
            callStmt.setString("par_clv_value", this.getClvValue());
            callStmt.setInt("par_clv_lcp_code", this.getClvLcpCode());
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

                    case "CLV_CODE":
                        this.setClvCode(Integer.parseInt(textField.getText()));
                        break;
                    case "CLV_VALUE":
                        this.setClvValue(textField.getText());
                        break;
                    case "CLV_LCP_CODE":
                        this.setClvLcpCode(Integer.parseInt(textField.getText()));
                        break;
                }
            } else if (node instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) node;
                switch (comboBox.getId()) {

                }
            }
        }
    }

    public void create() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_CHECK_LOVS.INSERT_ROW(?,?) }")) {
            callStmt.setString("par_clv_value", this.getClvValue());
            callStmt.setInt("par_clv_lcp_code", this.getClvLcpCode());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    public void delete() {
        SQLExecutor.deleteRow("B_CHECK_LOVS", getClvCode());
    }

    public static CallableStatementResults getResultSet(Integer LCP_CODE) {
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_CHECK_LOVS", "CLVLCP_CODE", LCP_CODE);
        return callResults;
    }
}
