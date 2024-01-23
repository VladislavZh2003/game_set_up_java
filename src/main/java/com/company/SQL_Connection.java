package com.company;

import java.sql.*;

public class SQL_Connection {

    public static Connection conn;

    public static boolean Connection(){
        try {
            String url = "jdbc:mysql://localhost:3306/games";
            String user = "root";
            String sqlPassword = "hanasparuh681";
            conn = DriverManager.getConnection(url, user, sqlPassword);
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean Create(String player){
        String sql = "INSERT INTO tic_tac_toe(name,wins) VALUES (?,?)";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,player);
            pst.setInt(2,0);
            pst.execute();
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean Delete(){
        String sql = "truncate tic_tac_toe;";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean AddPoints(String plName){
        String sql = "UPDATE tic_tac_toe SET wins = wins + 1 WHERE name = ?";
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,plName);
            pst.execute();
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static int SeeResult(String plName){
        Statement myStmt = null;
        ResultSet myRs = null;

        try {

            myStmt = conn.createStatement();

            myRs = myStmt.executeQuery("select * from tic_tac_toe");

            while (myRs.next()) {
                if(myRs.getString("name").equals(plName)){
                    return myRs.getInt("wins");
                }
            }
            return myRs.getInt("wins");
        }
        catch (Exception exc) {
            return 0;
        }

    }

    public static boolean CloseConnection(){
        try {
            conn.close();
            return true;
        } catch (Exception a) {
            return false;
        }
    }
}
