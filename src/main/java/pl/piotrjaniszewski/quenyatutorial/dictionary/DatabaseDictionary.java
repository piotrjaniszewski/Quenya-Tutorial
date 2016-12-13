package pl.piotrjaniszewski.quenyatutorial.dictionary;

import java.sql.*;

public class DatabaseDictionary implements Dictionary{

    private Connection connection;
    private Statement statement;
    private static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";

    public String translateToQuenya(String word) {
        connect();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dictionary WHERE polski='"+word+"'");
            if(resultSet.next()){
                String answer=resultSet.getString("quenya");
                disconnect();
                return answer;
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
                String answer = resultSet.getString("polski");
                disconnect();
                return answer;
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