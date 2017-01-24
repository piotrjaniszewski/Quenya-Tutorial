package pl.piotrjaniszewski.quenyatutorial;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AddTasks {
    public Connection connection;
    public Statement statement;
    public static final String DB_ADDRESS = "jdbc:sqlite:src/main/resources/mydb.db";

    public static void main(String[] args) throws IOException, SQLException {
        AddTasks addToDB = new AddTasks();
        addToDB.connect();
        int licznik =355;

        for (int i = 1; i <= 1; i++) {
            FileReader fileReader = new FileReader("C:\\Users\\Piotr\\Downloads\\Quenya\\lekcja"+i+"\\zadania\\tytuly.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> titles = new LinkedList<String>();

            String text = bufferedReader.readLine();
            while(text!=null){
                titles.add(text);
                text = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
            System.out.println("Lekcja "+i);
            for (int j = 1; j < 3; j++) {
            System.out.println(licznik +" "+titles.get(j));
                fileReader = new FileReader("C:\\Users\\Piotr\\Downloads\\Quenya\\lekcja"+i+"\\zadania\\"+j+".txt");

                bufferedReader = new BufferedReader(fileReader);
                List<String> list = new LinkedList<String>();
                text= bufferedReader.readLine();
                while(text!=null){
                    list.add(text);
                    text= bufferedReader.readLine();
                }
                for (int k = 1; k < list.size(); k++) {
                    if (list.get(k).split("split").length==2) {
                        String q = list.get(k).split("split")[0].replaceAll("\'", "\'\'");
                        String a = list.get(k).split("split")[1].replaceAll("\'", "\'\'");
                        licznik++;
                        addToDB.statement.execute("INSERT INTO `Exercises` (`lessonNr`, `ID`,`question`,`answer`,`exerciseNr`,`title`) VALUES ('"+i+"','"+licznik+"','"+q+"','"+a+"','"+j+"','"+titles.get(j)+"')");
                    }
                }
                bufferedReader.close();

                fileReader.close();
            }
        }

//        addToDB.statement.execute("INSERT INTO `Dictionary` (`quenya`, `polski`) VALUES ('"+q+"', '"+p+"')");
        addToDB.disconnect();
    }

    private void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_ADDRESS);
            statement = connection.createStatement();
            System.out.println("polączono");
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
