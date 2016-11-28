package pl.piotrjaniszewski.quenyatutorial.dictionary;

import java.sql.*;

public class DatabaseDictionary implements Dictionary{

    private Connection connection;
    private Statement statement;
    private static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";

    public DatabaseDictionary() {

    }

    public static void main(String[] args) {
        DatabaseDictionary dbd = new DatabaseDictionary();

        System.out.println(dbd.translateToQuenya("dwa"));
    }

    public String translateToQuenya(String word) {
        connect();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dictionary WHERE polski='"+word+"'");
            if(resultSet.next()){
                disconnect();
                return resultSet.getString("quenya");
            }
            else {
                disconnect();
                return "Brak tłumaczenia";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return null;
    }
    public String translateToPolish(String word) {
        connect();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dictionary WHERE quenya='"+word+"'");
            if(resultSet.next()){
                disconnect();
                return resultSet.getString("polski");
            }
            else {
                disconnect();
                return "Brak tłumaczenia";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return null;
    }

    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_ADDRESS);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}