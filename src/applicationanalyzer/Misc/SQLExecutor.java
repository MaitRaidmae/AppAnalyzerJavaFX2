/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationanalyzer.Misc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Hundisilm
 */
public class SQLExecutor {

    public static boolean executeProcedure(String procedureName) {

        Connection dbConnection;
        dbConnection = ConnectionManager.cl_conn;
        Statement stmt;
        try {
            stmt = dbConnection.createStatement();
            stmt.execute("begin " + procedureName + "; end;");
            stmt.close();
            return true;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
            return false;
        }
    }

    public static ResultSet executeQuery(String querySQL) {
        ResultSet queryResults;
        Connection dbConnection;
        dbConnection = ConnectionManager.cl_conn;
        Statement stmt;
        try {
            stmt = dbConnection.createStatement();
            queryResults = stmt.executeQuery(querySQL);
            return queryResults;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }

    /**
     * The function uses convention to call a results page procedure in
     * OracleDatabase with the following expected parameters: 1st - page number,
     * 2nd number of pages 3rd cursor with results.
     *
     * @param tableName
     * @param pageNr
     * @param pageResultsNr
     * @return
     */
    public static CallableStatementResults getTablePage(String tableName, Integer pageNr, Integer pageResultsNr) {

        return getTablePage(tableName, pageNr, pageResultsNr, null, null);
    }

    public static CallableStatementResults getTablePage(String tableName, Integer pageNr, Integer pageResultsNr, String findbyField, Integer findbyKey) {
        Connection db_connection = ConnectionManager.cl_conn;
        String packageName = getPackageName(tableName);
        ResultSet queryResults;
        CallableStatement callStmt;
        try {
            callStmt = db_connection.prepareCall("{ call " + packageName + ".GET_RESULTS_PAGE(?,?,?,?,?) }");
            callStmt.registerOutParameter(3, OracleTypes.CURSOR);
            callStmt.setInt(1, pageNr);
            callStmt.setInt(2, pageResultsNr);
            if (findbyField != null) {
                callStmt.setString(4, findbyField);
                callStmt.setInt(5, findbyKey);
            } else {
                callStmt.setNull(4, OracleTypes.VARCHAR);
                callStmt.setNull(5, OracleTypes.INTEGER);
            }
            callStmt.execute();
            queryResults = (ResultSet) callStmt.getObject(3);
            CallableStatementResults callResults = new CallableStatementResults(callStmt, queryResults);
            return callResults;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }

    public static boolean updateRowNvalue(String tableName, Integer rowCode, String fieldName, Number newValue) {
        String packageName = getPackageName(tableName);
        Connection db_connection = ConnectionManager.cl_conn;
        ResultSet queryResults;

        try (CallableStatement callStmt = db_connection.prepareCall("{ call " + packageName + ".UPDATE_ROW_NVALUE(?,?,?) }")) {
            callStmt.setInt(1, rowCode);
            callStmt.setString(2, fieldName);
            callStmt.setDouble(3, newValue.doubleValue());
            callStmt.execute();
            return true;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
            return false;
        }

    }

    /**
     * The function is meant for getting a table row based on a integer key
     * value which would typically be a primary key or a foreign key (it would
     * have to be unique).
     *
     * @param tableName
     * @param columnName
     * @param key
     * @return
     */
    public static CallableStatementResults getTableRow(String tableName, String columnName, Integer key) {
        Connection db_connection = ConnectionManager.cl_conn;
        String packageName = getPackageName(tableName);
        ResultSet queryResults;
        CallableStatement callStmt;
        try {
            callStmt = db_connection.prepareCall("{ call " + packageName + ".GET_ROWS(?,?,?) }");
            callStmt.registerOutParameter(3, OracleTypes.CURSOR);
            callStmt.setString(1, columnName);
            callStmt.setInt(2, key);
            callStmt.execute();
            queryResults = (ResultSet) callStmt.getObject(3);
            CallableStatementResults callResults = new CallableStatementResults(callStmt, queryResults);
            return callResults;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }

    /**
     * The function is meant for getting a table row based on a integer key
     * value which would typically be a primary key or a foreign key (it would
     * have to be unique).
     *
     * @param tableName
     * @param columnName
     * @return
     */
    public static String getPrettyName(String tableName, String columnName) {
        Connection db_connection = ConnectionManager.cl_conn;
        String prettyName;
        try (CallableStatement callStmt = db_connection.prepareCall("{? = call MISC_UTILS.PRETTY_NAME(?,?) }")) {
            callStmt.registerOutParameter(1, Types.VARCHAR);
            callStmt.setString(2, tableName);
            callStmt.setString(3, columnName);
            callStmt.executeUpdate();
            prettyName = (String) callStmt.getObject(1);
            return prettyName;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }

    /**
     * Function gets LOV from table constraints
     *
     * @param tableName
     * @param fieldName
     * @return
     */
    public static ObservableList<String> getLov(String tableName, String fieldName) {
        Connection db_connection = ConnectionManager.cl_conn;
        ObservableList<String> lovList = FXCollections.observableArrayList();

        try (CallableStatement callStmt = db_connection.prepareCall("{ call MISC_UTILS.TABLE_COL_LOV(?,?,?,?) }")) {
            callStmt.registerOutParameter("par_results", OracleTypes.CURSOR);
            callStmt.setString("par_owner", "HUNDISILM");
            callStmt.setString("par_table_name", tableName);
            callStmt.setString("par_field_name", fieldName);
            callStmt.execute();
            ResultSet lovValues = (ResultSet) callStmt.getObject("par_results");
            while (lovValues.next()) {
                lovList.add(lovValues.getString(1));
            }
            return lovList;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }

    public static CallableStatementResults getColumnData(String tableName) {
        Connection db_connection = ConnectionManager.cl_conn;
        ResultSet queryResults;
        CallableStatement callStmt;
        try {
            callStmt = db_connection.prepareCall("{ call MISC_UTILS.TABLE_COLS(?,?) }");
            callStmt.registerOutParameter(2, OracleTypes.CURSOR);
            callStmt.setString(1, tableName);
            callStmt.execute();
            queryResults = (ResultSet) callStmt.getObject(2);
            CallableStatementResults callResults = new CallableStatementResults(callStmt, queryResults);
            return callResults;
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        }
        return null;
    }
    
    public static void deleteRow(String tableName, Integer rowCode) {
       
        try {
            if (rowCode == null || rowCode <= 0) {
                throw new IllegalArgumentException("rowCode: "+rowCode +". Bad value - rowCode must be non-null and greater than zero.");
            }
        } catch (IllegalArgumentException evilArgument){
            Alerts.GeneralAlert(evilArgument);
        }
        
        //Convert tableName to packageName
        String packageName = getPackageName(tableName);
        Connection db_connection = ConnectionManager.cl_conn;
        
        try(CallableStatement callStmt = db_connection.prepareCall("{ call "+packageName+".DELETE_ROW(?) }")) {
            callStmt.setInt("par_code", rowCode);
            callStmt.execute();
        } catch (SQLException sqle) {
            Alerts.AlertSQL(sqle);
        } 

    }

    private static String getPackageName(String tableName) {

        String packageName = tableName.toUpperCase();
        switch (packageName.substring(0, 2)) {
            case "B_":
                packageName = packageName.toUpperCase().replaceFirst("B_", "P_");
                break;
            case "V_":
                packageName = packageName.toUpperCase().replaceFirst("V_", "P_");
                break;
        }
        return packageName;
    }
}
