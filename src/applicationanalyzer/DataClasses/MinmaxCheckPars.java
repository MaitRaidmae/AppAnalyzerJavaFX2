package applicationanalyzer.DataClasses;

import applicationanalyzer.Misc.Alerts;
import applicationanalyzer.Misc.CallableStatementResults;
import applicationanalyzer.Misc.ConnectionManager;
import applicationanalyzer.Misc.SQLExecutor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MinmaxCheckPars {

    private final SimpleIntegerProperty mcp_code;
    private final SimpleIntegerProperty mcp_chk_code;
    private final SimpleDoubleProperty mcp_threshold;
    private final SimpleStringProperty mcp_check_field;

    public MinmaxCheckPars(Integer mcp_code,
            Integer mcp_chk_code,
            Double mcp_threshold,
            String mcp_check_field) {
        this.mcp_code = new SimpleIntegerProperty(mcp_code);
        this.mcp_chk_code = new SimpleIntegerProperty(mcp_chk_code);
        this.mcp_threshold = new SimpleDoubleProperty(mcp_threshold);
        this.mcp_check_field = new SimpleStringProperty(mcp_check_field);
    }

    public MinmaxCheckPars() {
        this.mcp_code = new SimpleIntegerProperty();
        this.mcp_chk_code = new SimpleIntegerProperty();
        this.mcp_threshold = new SimpleDoubleProperty();
        this.mcp_check_field = new SimpleStringProperty();
    }

    public MinmaxCheckPars(Integer new_mcp_chk_code) {
        this();
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_MINMAX_CHECK_PARS", "MCP_CHK_CODE", new_mcp_chk_code);
        ResultSet pars = callResults.getResultSet();
        try {
            if (pars.next()) {
                mcp_code.set(pars.getInt(1));
                mcp_chk_code.set(pars.getInt(2));
                mcp_threshold.set(pars.getDouble(3));
                mcp_check_field.set(pars.getString(4));
            }
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        callResults.close();

    }

    public Integer getMcpCode() {
        return mcp_code.get();
    }

    public void setMcpCode(Integer code) {
        mcp_code.set(code);
    }

    public Integer getMcpChkCode() {
        return mcp_chk_code.get();
    }

    public void setMcpChkCode(Integer chk_code) {
        mcp_chk_code.set(chk_code);
    }

    public Double getMcpThreshold() {
        return mcp_threshold.get();
    }

    public void setMcpThreshold(Double threshold) {
        mcp_threshold.set(threshold);
    }

    public String getMcpCheckField() {
        return mcp_check_field.get();
    }

    public void setMcpCheckField(String check_field) {
        mcp_check_field.set(check_field);
    }

    public GridPane getGrid(Boolean editable) {
        GridPane grid = new GridPane();
        Boolean noData = true; //Assume not to find anything
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_MINMAX_CHECK_PARS", "MCP_CODE", this.getMcpCode());
        ResultSet dataValues = callResults.getResultSet();
        ResultSetMetaData metaData;
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                noData = false;
                metaData = dataValues.getMetaData();
                for (int i = 2; i <= metaData.getColumnCount(); i++) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_MINMAX_CHECK_PARS", metaData.getColumnName(i)));
                    grid.add(fieldNameLbl, 0, k);
                    if (!editable) {
                        Label fieldValueLbl = new Label(dataValues.getString(i));
                        grid.add(fieldValueLbl, 1, k);
                    } else {
                        switch (metaData.getColumnName(i)) {

                            case "MCP_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "MCP_CHK_CODE":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "MCP_THRESHOLD":
                                textField = new TextField(dataValues.getString(i));
                                fieldType = "textField";
                                break;
                            case "MCP_CHECK_FIELD":
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

    public static GridPane getEmptyGrid() {
        GridPane grid = new GridPane();
        int k = 0;
        String fieldType = "";
        CallableStatementResults callResults = SQLExecutor.getColumnData("B_MINMAX_CHECK_PARS");
        ResultSet dataValues = callResults.getResultSet();
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        try {
            while (dataValues.next()) {
                if (!dataValues.getString("column_name").equals("MCP_CODE") && !dataValues.getString("column_name").equals("MCP_CHK_CODE")) {
                    Label fieldNameLbl = new Label(SQLExecutor.getPrettyName("B_MINMAX_CHECK_PARS", dataValues.getString("column_name")));
                    grid.add(fieldNameLbl, 0, k);
                    switch (dataValues.getInt("is_lov")) {
                        case 1:
                            comboBox = new ComboBox(SQLExecutor.getLov("B_MINMAX_CHECK_PARS", dataValues.getString("column_name")));
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
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_MINMAX_CHECK_PARS.UPDATE_ROW(?,?,?,?) }")) {
            callStmt.setInt(1, this.getMcpCode());
            callStmt.setInt(2, this.getMcpChkCode());
            callStmt.setDouble(3, this.getMcpThreshold());
            callStmt.setString(4, this.getMcpCheckField());
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
                    case "MCP_CODE":
                        this.setMcpCode(Integer.parseInt(textField.getText()));
                        break;
                    case "MCP_CHK_CODE":
                        this.setMcpChkCode(Integer.parseInt(textField.getText()));
                        break;
                    case "MCP_THRESHOLD":
                        this.setMcpThreshold(Double.parseDouble(textField.getText()));
                        break;
                    
                }
            } else if (node instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) node;
                switch (comboBox.getId()) {
                    case "MCP_CHECK_FIELD":
                        this.setMcpCheckField((String) comboBox.getValue());
                        break;
                }
            }
        }
    }

    public void create() {
        Connection db_connection = ConnectionManager.cl_conn;
        try (CallableStatement callStmt = db_connection.prepareCall("{ call P_MINMAX_CHECK_PARS.INSERT_ROW(?,?,?) }")) {
            callStmt.setInt("par_mcp_chk_code", this.getMcpChkCode());
            callStmt.setDouble("par_mcp_threshold", this.getMcpThreshold());
            callStmt.setString("par_mcp_check_field", this.getMcpCheckField());
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
    }

    public static CallableStatementResults getResultSet(Integer MCP_CHK_CODE) {
        CallableStatementResults callResults = SQLExecutor.getTableRow("P_MINMAX_CHECK_PARS", "MCPMCP_CHK_CODE", MCP_CHK_CODE);
        return callResults;
    }
}
