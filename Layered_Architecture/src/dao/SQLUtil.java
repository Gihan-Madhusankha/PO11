package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {

    private static PreparedStatement getPreparedStatement(String sql, Object... param) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }

        return preparedStatement;
    }

    public static boolean executeUpdate(String sql, Object... param) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, param).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... param) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, param).executeQuery();
    }

    /*

    // latest code
    public <T>T execute(String sql, Object...param) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i+1, param[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) executeQuery(sql, param);
        } else {
            return (T)(Boolean)executeUpdate(sql, param);
        }
    }

    */

}
