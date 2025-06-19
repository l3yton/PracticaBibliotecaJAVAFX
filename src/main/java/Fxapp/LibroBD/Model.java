package Fxapp.LibroBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

    protected String table;

    public ResultSet getAll(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + this.table);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet get(Connection con, int id) {
        try {
            String query = "select * from " + this.table + " where id" + this.table + " = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int delete(Connection con, int id) {
        try {
            String query = "delete from " + this.table + " where id" + this.table + " = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            int resultado = pstmt.executeUpdate();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}